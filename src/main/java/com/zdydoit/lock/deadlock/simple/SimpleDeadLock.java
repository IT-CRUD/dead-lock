package com.zdydoit.lock.deadlock.simple;


public class SimpleDeadLock {

    //定义两把锁
    private static final Object firstLock = new Object();
    private static final Object secondLock = new Object();

    public static class DeadRun implements Runnable {

        private final Object firstLock;
        private final Object secondLock;

        DeadRun(Object firstLock, Object secondLock) {
            this.firstLock = firstLock;
            this.secondLock = secondLock;
        }

        public void run() {
            String name = Thread.currentThread().getName();
            synchronized (firstLock) {
                System.out.println(name + ">>>get firstLock");
                synchronized (secondLock) {
                    System.out.println(name + ">>>get secondLock");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(new DeadRun(firstLock, secondLock)).start();//创建线程并执行

        String name = Thread.currentThread().getName();
        synchronized (secondLock) {
            System.out.println(name + ">>>get secondLock");
            synchronized (firstLock) {
                System.out.println(name + "get firstLock");
            }
        }
    }
}

