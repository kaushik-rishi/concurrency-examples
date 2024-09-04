package com.lld;

public class SampleThread extends Thread {
    @Override
    public void run() {
        Thread.currentThread().setName("nob");
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("hello from a thread");
    }
}
