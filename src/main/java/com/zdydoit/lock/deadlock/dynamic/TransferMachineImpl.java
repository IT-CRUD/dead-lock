package com.zdydoit.lock.deadlock.dynamic;


public class TransferMachineImpl implements TransferMachine {


    /**
     * 转账操作
     *
     * @param from   转出账户
     * @param to     转入账户
     * @param amount 交易金额
     */
    public void transfer(Account from, Account to, Integer amount) {
        String name = Thread.currentThread().getName();
        while (true) {
            if (from.getLock().tryLock()) {
                try {
                    System.out.println(name + ">>>lock from success");
                    if (to.getLock().tryLock()) {
                        try {
                            System.out.println(name + ">>>lock to success");
                            // 转账操作
                            from.flyMoney(amount);
                            to.addMoney(amount);
                            break;
                        } finally {
                            to.getLock().unlock();
                        }
                    }
                } finally {
                    from.getLock().unlock();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //private final Object fairLock = new Object();//公平竞争锁

    /**
     * 转账操作
     *
     * @param from   转出账户
     * @param to     转入账户
     * @param amount 交易金额
     */
    /*public void transfer(Account from, Account to, Integer amount) {
        String name = Thread.currentThread().getName();
        final Integer fromHash = System.identityHashCode(from);
        final Integer toHash = System.identityHashCode(to);
        if (fromHash < toHash) {
            synchronized (fromHash) {
                System.out.println(name + ">>>lock from success");
                synchronized (toHash) {
                    System.out.println(name + ">>>lock to success");
                    from.flyMoney(amount);
                    to.addMoney(amount);
                }
            }
        } else if (toHash < fromHash) {
            synchronized (toHash) {
                System.out.println(name + ">>>lock from success");
                synchronized (fromHash) {
                    System.out.println(name + ">>>lock to success");
                    from.flyMoney(amount);
                    to.addMoney(amount);
                }
            }
        } else {
            synchronized (fairLock) {
                synchronized (from) {
                    System.out.println(name + ">>>lock from success");
                    synchronized (to) {
                        System.out.println(name + ">>>lock to success");
                        from.flyMoney(amount);
                        to.addMoney(amount);
                    }
                }
            }
        }
    }*/
}
