package com.zdydoit.lock.deadlock.dynamic;

public interface TransferMachine {

    /**
     * 转账操作
     *
     * @param from   转出账户
     * @param to     转入账户
     * @param amount 交易金额
     */
    void transfer(Account from, Account to, Integer amount);
}
