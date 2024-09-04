package com.lld.producerconsumerv2;

import java.util.ArrayDeque;
import java.util.Deque;

public class SharedMessageQueue {
    Deque<String> q;
    int maxBufferSize;

    public SharedMessageQueue(int maxBufferSize) {
        q = new ArrayDeque<>();
        this.maxBufferSize = maxBufferSize;
    }

    public synchronized void addMessage(String message) {
        if (q.size() == maxBufferSize) {
            System.out.format("Scheduling wait on thread - %s - trying to insert - %s\n", Thread.currentThread().getName(), message);
            try {
                wait();
            } catch (Exception e) {
                System.out.println("handling exception");
            }
        }
        q.add(message);
        notifyAll();
    }

    public synchronized String consumeMesssage() {
        if (q.isEmpty()) {
            try {
                System.out.println("queue empty - asking consumer to wait");
                wait();
            } catch(Exception e) {
                System.out.println("handling exception");
            }
        }
        return q.pop();
    }
}
