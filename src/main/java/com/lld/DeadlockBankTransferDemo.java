package com.lld;

import com.lld.bank.BankAccount;

public class DeadlockBankTransferDemo {
    public static void main(String[] args) throws InterruptedException {
        BankAccount b1 = new BankAccount(1);
        b1.deposit(1000);

        BankAccount b2 = new BankAccount(2);
        b2.deposit(1000);

        // in general, these threads are created by the spring/whatever application that's taking in the http/grpc requests
        Thread transferOperation1 = new Thread(new Runnable() {
            @Override
            public void run() {
                BankAccount.transfer(b1, b2, 300);
            }
        });

        Thread transferOperation2 = new Thread(new Runnable() {
            @Override
            public void run() {
                BankAccount.transfer(b2, b1, 100);
            }
        });

        transferOperation1.start();
        transferOperation2.start();

        transferOperation1.join();
        transferOperation2.join();
        System.out.println(b1.getBalance());
        System.out.println(b2.getBalance());
    }
}
