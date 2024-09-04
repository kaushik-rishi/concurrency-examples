package com.lld.print_in_order;

/**
 * TODO
 * make it a test
 * - multiple test cases with assertEquals output
 * - test for busywait cases, program needs to be alive and running for more than `x` seconds
 */

public class PrintInOrder {
    private Thread createThread(IFoo foo, int index) {
        return new Thread(() -> {
            try {
                switch (index) {
                    case 1:
                        foo.first(() -> {
                            System.out.println("first: run started");
                            try {
                                Thread.sleep(5000);
                                System.out.println("first operation:" + Thread.currentThread().getName());
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("first: run completed");
                        });
                        break;
                    case 2:
                        foo.second(() -> {
                            System.out.println("second: run started");
                            try {
                                Thread.sleep(5000);
                                System.out.println("second operation: " + Thread.currentThread().getName());
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("second: run completed");
                        });
                        break;
                    case 3:
                        foo.third(() -> {
                            System.out.println("third: run started");
                            try {
                                Thread.sleep(5000);
                                System.out.println("third operation: " + Thread.currentThread().getName());
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("third: run completed");
                        });
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-" + index);
    }

    public void test0() throws InterruptedException {
        IFoo foo = new FooSemaphore();
        int[] input = {3, 1, 2};
        for (int i : input) {
            Thread thread = createThread(foo, i);
            thread.start();
        }
    }

    public void test1() throws InterruptedException {
        IFoo foo = new FooBusyWait();
        int[] input = {1, 3, 2};
        for (int i : input) {
            Thread thread = createThread(foo, i);
            thread.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PrintInOrder pio = new PrintInOrder();
        pio.test0();
    }
}
