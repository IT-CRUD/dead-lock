package com.zdydoit.lock.deadlock.dynamic;

public class TransferMain {

    public static class DynamicRun implements Runnable {

        private Account a;
        private Account b;
        private TransferMachine t;
        private Integer amount;

        DynamicRun(TransferMachine t, Account a, Account b, Integer amount) {
            this.t = t;
            this.a = a;
            this.b = b;
            this.amount = amount;
        }

        public void run() {
            t.transfer(a, b, amount);//转账操作
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //初始化两个账户
        Account firstAccount = new Account("zhangsan", 20000);
        Account secondAccount = new Account("lisi", 20000);

        //张三给李四转账1000
        new Thread(new DynamicRun(new TransferMachineImpl(), firstAccount, secondAccount, 1000)).start();
        //李四给张三转账2000
        new Thread(new DynamicRun(new TransferMachineImpl(), secondAccount, firstAccount, 2000)).start();
    }
}
