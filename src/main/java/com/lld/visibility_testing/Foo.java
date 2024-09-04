package com.lld.visibility_testing;

public class Foo {
    private volatile boolean exitLoop;

    public Foo() {
        this.exitLoop = false;
    }

    public void run() {
        while (!exitLoop) {
            // Empty loop body
        }
        System.out.println("Run completed.");
    }

    public void stopLoop() {
        this.exitLoop = true;
    }
}
