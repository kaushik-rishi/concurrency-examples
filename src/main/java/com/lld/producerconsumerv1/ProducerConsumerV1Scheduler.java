package com.lld.producerconsumerv1;

import com.lld.producerconsumerv1.models.SharedResource;

public class ProducerConsumerV1Scheduler {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producerThread = new Thread(new ProduceItem(resource));
        Thread consumerThread = new Thread(new ConsumeItem(resource));

        producerThread.start();
        consumerThread.start();
    }
}
