package com.lld;

class Holder {
    private int value;

    public Holder(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Holder[value=" + this.value + "]";
    }
}

class UnsafePublishHolderDriver {
    Holder holder;

    public UnsafePublishHolderDriver() {}

    public void initialize() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.holder = new Holder(100);
    }
}

public class ImproperPublishImmutableExample {
    public static void main(String[] args) {
        UnsafePublishHolderDriver usfHolderDriver = new UnsafePublishHolderDriver();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                usfHolderDriver.initialize();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;
                while (usfHolderDriver.holder == null) {
                    // busy-wait till holder initialization is completed
                    System.out.format("busy-waiting %s\n", counter++);
                }
                System.out.println(usfHolderDriver.holder);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
