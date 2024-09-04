package com.lld.producerconsumerv1.models;

public class SharedResource {
    private boolean itemAvailable;

    public SharedResource() {
        itemAvailable = false;
    }

    public synchronized void addItem() {
        System.out.format("(%s) Thread acquiring lock to add item\n", Thread.currentThread().getName());
        if (!itemAvailable) {
            itemAvailable = true;
            System.out.println("item added, notifying all waiting threads");
            notifyAll();
        }
    }

    public synchronized void consumeItem() {
        System.out.printf("(%s) Thread trying to consume item\n", Thread.currentThread().getName());
        if (!itemAvailable) {
            try {
                System.out.println("waiting");
                wait(); // release all monitor locks
            } catch (IllegalMonitorStateException e) {
                System.out.println("current object is not the owner of monitor lock");
            }
            catch (InterruptedException e) {
                System.out.println("consumeItem waiting has been interrupted");
            }
        }
        System.out.println("item consumed");
        itemAvailable = false;
    }
}
