package com.lld.producerconsumerv2;

public class ProduceConsumerScheduler {
    public static void main(String[] args) {
        SharedMessageQueue smq = new SharedMessageQueue(4);

        Thread producerThread = new Thread(new Producer(smq));
        Thread consumerThread = new Thread(new Consumer(smq));

        producerThread.start();
        consumerThread.start();
    }
}
