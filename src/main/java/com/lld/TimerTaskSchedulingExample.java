package com.lld;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class TimerTaskSchedulingExample {
    private static int counter = 0;

    public static void main(String[] args) {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                synchronized (TimerTaskSchedulingExample.class) {
                    // Increment the counter
                    counter++;
                    System.out.println("Counter: " + counter);
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);

        // Simulate other work in the main thread
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Read the counter value
            System.out.println("Main Thread - Counter: " + counter);
        }

        timer.cancel();
    }
}
