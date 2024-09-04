package com.lld.producerconsumerv2;

public class Consumer implements Runnable {
    SharedMessageQueue mq;

    public Consumer(SharedMessageQueue mq) {
        this.mq = mq;
    }

    @Override
    public void run() {
        System.out.println("start - Consumer.run");

        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // handle exception
            }
            System.out.format("(%s) thread - Consuming message - %s\n", Thread.currentThread().getName(), mq.consumeMesssage());;
        }
    }
}
