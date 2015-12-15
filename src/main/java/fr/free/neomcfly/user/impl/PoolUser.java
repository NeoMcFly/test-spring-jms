package fr.free.neomcfly.user.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.free.neomcfly.calcul.impl.CalcukTaskProvider;

@Slf4j
@Service("poolUser")
public class PoolUser {

    @Autowired
    CalcukTaskProvider provider;

    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(50);
        for (int i = 1; i <= 50; i++) {
            Runnable worker = new WorkerThread(provider, "user:" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        log.info("Finished all threads");
    }

    public static class WorkerThread implements Runnable {

        private String username;
        private CalcukTaskProvider provider;

        public WorkerThread(CalcukTaskProvider provider, String username) {
            this.provider = provider;
            this.username = username;
        }

        @Override
        public void run() {
            processCommand();
        }

        private void processCommand() {
            try {
                for (int i = 1; i <= 3; i++) {
                    log.info("User '{}' lance son calcul '{}'", this.username,
                            i);
                    provider.sendMessage(this.username + "|calcul:" + i);
                    Thread.sleep(30000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return this.username;
        }
    }
}
