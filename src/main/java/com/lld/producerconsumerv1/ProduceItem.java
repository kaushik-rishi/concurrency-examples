package com.lld.producerconsumerv1;

import com.lld.producerconsumerv1.models.SharedResource;

public class ProduceItem implements Runnable {
    SharedResource resource;
    public ProduceItem(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            System.out.println("going to do some business logic for 5 seconds before publishing something here !");
            Thread.sleep(5000);
            resource.addItem();
        } catch (InterruptedException e) {
            System.out.println("producer thread's sleep(business logic) interrupted when trying to addItem");
        }
    }
}
