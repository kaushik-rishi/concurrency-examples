package com.lld.producerconsumerv2;

public class Producer implements Runnable {
    SharedMessageQueue mq;

    public Producer(SharedMessageQueue mq) {
        this.mq = mq;
    }

    @Override
    public void run() {
        int msgIndex = 0;

        while(true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // handle exception
            }
            System.out.println("producing a message");
            mq.addMessage("Message number " + msgIndex);
            msgIndex++;
        }
    }
}
