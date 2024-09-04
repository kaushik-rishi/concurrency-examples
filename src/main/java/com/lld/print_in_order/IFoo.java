package com.lld.print_in_order;

public interface IFoo {
    void first(Runnable printFirst) throws InterruptedException;
    void second(Runnable printSecond) throws InterruptedException;
    void third(Runnable printThird) throws InterruptedException;
}
