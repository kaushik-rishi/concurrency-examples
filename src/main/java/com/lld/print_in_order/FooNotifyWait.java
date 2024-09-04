package com.lld.print_in_order;

class FooNotifyWait implements IFoo {
    boolean firstCalled;
    boolean secondCalled;

    public FooNotifyWait() {
        this.firstCalled = false;
        this.secondCalled = false;
    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        this.firstCalled = true;
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        while (!this.firstCalled) {
            wait();
        }
        printSecond.run();
        this.secondCalled = true;
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while (!(this.firstCalled && this.secondCalled)) {
            wait();
        }
        printThird.run();
    }
}
