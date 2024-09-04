package com.lld.bank;

public class Wallet {
    private int balance;

    public Wallet(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public int withdraw(int amount) throws IllegalStateException {
        if (amount > balance) {
            throw new IllegalStateException("trying to withdraw more amount than balance");
        }

        synchronized (this) {
            balance -= amount;
        }
        return balance;
    }


}
