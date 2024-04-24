/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParallelCalculator2;

public class ParallelCalculatorWithSynchronized {
    public static void main(String[] args) {
        int numThreads = 10;
        Thread[] threads = new Thread[numThreads];
        int[][] results = new int[numThreads][10];

        for (int i = 0; i < numThreads; i++) {
            final int threadNum = i;
            threads[i] = new Thread(() -> {
                synchronized (results) {
                    for (int j = 1; j <= 10; j++) {
                        results[threadNum][j-1] = threadNum * j;
                    }
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
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

