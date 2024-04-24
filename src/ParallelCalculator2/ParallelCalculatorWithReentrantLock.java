/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParallelCalculator2;

import java.util.concurrent.locks.ReentrantLock;

public class ParallelCalculatorWithReentrantLock {
    public static void main(String[] args) {
        int numThreads = 10;
        Thread[] threads = new Thread[numThreads];
        int[][] results = new int[numThreads][10];
        ReentrantLock lock = new ReentrantLock();

        for (int i = 0; i < numThreads; i++) {
            final int threadNum = i;
            threads[i] = new Thread(() -> {
                lock.lock();
                try {
                    for (int j = 1; j <= 10; j++) {
                        results[threadNum][j-1] = threadNum * j;
                    }
                } finally {
                    lock.unlock();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < numThreads; i++) {
            System.out.print("Thread " + i + " results: ");
            for (int j = 0; j < 10; j++) {
                System.out.print(results[i][j] + " ");
            }
            System.out.println();
        }
    }
}

