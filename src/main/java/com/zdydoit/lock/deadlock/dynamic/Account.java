package com.zdydoit.lock.deadlock.dynamic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private String name;
    private Integer amount;

    private final Lock lock = new ReentrantLock();

    public Lock getLock() {
        return this.lock;
    }

    Account(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    public void addMoney(Integer amount) {
        this.amount += amount;
    }

    public void flyMoney(Integer amount) {
        this.amount -= amount;
    }
}
