package com.lld.producerconsumerv1;

import com.lld.producerconsumerv1.models.SharedResource;

public class ConsumeItem implements Runnable {
    SharedResource resource;

    public ConsumeItem(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        System.out.println("ConsumeItem: run - start");
        resource.consumeItem();
        System.out.println("ConsumeItem: run - end");
    }
}
