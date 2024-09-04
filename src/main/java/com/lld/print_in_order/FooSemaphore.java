package com.lld.print_in_order;

import java.util.concurrent.*;

class FooSemaphore implements IFoo {
    Semaphore run2, run3;

    public FooSemaphore() {
        run2 = new Semaphore(0);
        run3 = new Semaphore(0);
    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        System.out.println("trying acquire semaphore for first");
        printFirst.run();
        run2.release();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        System.out.println("trying acquire semaphore for second");
        run2.acquire();
        printSecond.run();
        run3.release();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        System.out.println("trying acquire semaphore for third");
        run3.acquire();
        printThird.run();
    }
}
