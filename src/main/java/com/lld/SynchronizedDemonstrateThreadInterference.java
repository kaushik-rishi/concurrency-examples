package com.lld;

class SynchronizedCounter {
    int value = 0;

    synchronized void increment() throws InterruptedException {
        System.out.format("%s : %s \n", Thread.currentThread().getName(), "increment-start");
        int incrementedValue = value;
        Thread.sleep(1000);
        incrementedValue += 1;
        Thread.sleep(1000);
        value = incrementedValue;
        System.out.format("%s : %s \n", Thread.currentThread().getName(), "increment-end");
    }
}

class SynchronizedIncrementInterleaveThread implements Runnable {
    SynchronizedCounter c;

    public SynchronizedIncrementInterleaveThread(SynchronizedCounter c) {
        this.c = c;
    }

    public void run() {
        try {
            this.c.increment();
        } catch (InterruptedException e) {
            System.out.println("i've been interrupted");
        }
    }
}

public class SynchronizedDemonstrateThreadInterference {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedCounter c = new SynchronizedCounter();
        System.out.println(c.value);

        Thread t1 = new Thread(new SynchronizedIncrementInterleaveThread(c));
        Thread t2 = new Thread(new SynchronizedIncrementInterleaveThread(c));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(c.value);
    }
}
