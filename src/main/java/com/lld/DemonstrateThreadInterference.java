package com.lld;

class Counter {
    int value = 0;

    void increment() throws InterruptedException {
        System.out.format("%s : %s \n", Thread.currentThread().getName(), "increment-start");
        int incrementedValue = value;
        Thread.sleep(1000);
        incrementedValue += 1;
        Thread.sleep(1000);
        value = incrementedValue;
        System.out.format("%s : %s\n", Thread.currentThread().getName(), "increment-end");
    }
}

class IncrementInterleaveThread implements Runnable {
    Counter c;

    public IncrementInterleaveThread(Counter c) {
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

public class DemonstrateThreadInterference {
    public static void main(String[] args) throws InterruptedException {
        /*
        Counter c = new Counter();
        System.out.println(c.value);

        Thread t1 = new Thread(new IncrementInterleaveThread(c));
        Thread t2 = new Thread(new IncrementInterleaveThread(c));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(c.value);
         */
    }
}
