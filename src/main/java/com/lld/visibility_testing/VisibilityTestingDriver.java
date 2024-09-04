package com.lld.visibility_testing;

public class VisibilityTestingDriver {
    public static void main(String[] args) {
        Foo foo = new Foo();

        // First thread that will run the run() method of Foo
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                foo.run();
            }
        });

        // Second thread that will set exitLoop to true after 5 seconds
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                foo.stopLoop();
                System.out.println("exitLoop set to true.");
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // Wait for threads to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
