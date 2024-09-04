package com.lld.bank;

public class BankAccount {
    private int balance;
    private final int acNumber;

    public BankAccount(int acNumber) {
        this.acNumber = acNumber;
    }

    public int getBalance() {
        return balance;
    }

    public synchronized void withdraw(int amount) {
        balance -= amount;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public static void transfer(BankAccount fromAccount, BankAccount toAccount, int amountToTransfer) {
        synchronized (fromAccount) {
            System.out.format("%s : Setting up stuff for transfer from : %s to : %s\n", Thread.currentThread().getName(), fromAccount.acNumber, toAccount.acNumber);
            try { Thread.sleep(6000); } catch(InterruptedException e) {}
            System.out.format("%s : acquired lock on %s\n", Thread.currentThread().getName(), fromAccount.acNumber);


            System.out.format("%s : waiting to acquire lock on %s\n", Thread.currentThread().getName(), toAccount.acNumber);
            synchronized (toAccount) {
                System.out.format("%s : acquired lock on %s\n", Thread.currentThread().getName(), toAccount.acNumber);

                System.out.format("%s : starting the transfer process from : %s to : %s\n", Thread.currentThread().getName(), fromAccount.acNumber, toAccount.acNumber);
                fromAccount.withdraw(amountToTransfer);
                toAccount.deposit(amountToTransfer);
            }
        }
    }
}
