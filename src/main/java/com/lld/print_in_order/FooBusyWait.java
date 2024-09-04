package com.lld.print_in_order;

class FooBusyWait implements IFoo {
     boolean firstCalled;
     boolean secondCalled;

    public FooBusyWait() {
        this.firstCalled = false;
        this.secondCalled = false;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        this.firstCalled = true;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (!this.firstCalled) {}
        printSecond.run();
        this.secondCalled = true;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (!(this.firstCalled && this.secondCalled)) {}
        printThird.run();
    }
}
