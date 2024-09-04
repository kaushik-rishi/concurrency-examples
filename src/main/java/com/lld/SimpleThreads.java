package com.lld;

public class SimpleThreads {
    static void threadLog(String message) {
        System.out.format("%s: %s\n", Thread.currentThread().getName(), message);
    }

    public static class MessageLoop implements Runnable {
        public void run() {
            String[] messages = {
                    "Eat more protein!",
                    "Eat more oats!",
                    "Eat more healthy carbs",
                    "Stop eating samosa",
                    "Stop eating pani puri"
            };

            /*
            try {
                for (String message : messages) {
                    Thread.sleep(4000);
                    threadLog(message);
                }
            } catch (InterruptedException e) {
                System.out.println("interrupted exception received");
                threadLog("I wasn't done yet");
            }
            */

            /*
            // interesting case
            // since we do not stop the loop in case of exception, it keeps executing
            // so when using this case using a t.join() becomes necessary for a graceful shutdown
            for (String message : messages) {
                try {
                    Thread.sleep(4000);
                    threadLog(message);
                } catch (InterruptedException e) {
                    System.out.println("interrupted exception received");
                    threadLog("I wasn't done yet");
                }
            }
             */

            for (String message : messages) {
                try {
                    threadLog(Thread.currentThread().isInterrupted() ? "True" : "False");
                     if (Thread.currentThread().isInterrupted()) return;
                    Thread.sleep(4000);
                    threadLog(message);
                } catch (InterruptedException e) {
                    System.out.println("interrupted exception received");
                    threadLog("I wasn't done yet");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int patienceInSeconds = 10;
        int patienceInMillis = patienceInSeconds * 1000;

        Thread msgLoopThread = new Thread(new MessageLoop());
        msgLoopThread.start();

        threadLog("waiting");
        msgLoopThread.join(patienceInMillis);
        threadLog("done waiting");
        msgLoopThread.interrupt();

        msgLoopThread.join(); // to ensure graceful shut down
        threadLog("completely shutting down the java progam");
    }
}
