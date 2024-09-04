package com.lld;

class LongRunningOperations implements Runnable {
    public void run() {
        for (int i = 0; i < 10; ++i) {
            longRunningTask(i);
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("detected thread interrupt");
                return;
            }
        }
    }

    public void longRunningTask(int i) {
        System.out.println("longRunningTask(" + i + ")");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("interrupted due to interruption to Thread.sleep");
            System.out.println(Thread.currentThread().getName());
            Thread.currentThread().interrupt();
            return;
        }
    }
}

public class ManualInterruptExample {
    public static void main() throws InterruptedException {
        Thread t = new Thread(new LongRunningOperations());
        t.start();
        t.join(5000);

        Thread.sleep(3000);
        System.out.println(t.isAlive());
        t.interrupt();
    }
}
