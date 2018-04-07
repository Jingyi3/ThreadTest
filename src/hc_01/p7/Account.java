package hc_01.p7;

import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/6.
 * 对业务写方法枷锁
 * 对业务读方法不加锁
 * 容易长生脏读（dirty Read）
 */

public class Account {
    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;//!!

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;//!!
    }

    public /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }


    public static void main(String[] args) {
        Account a = new Account();
        new Thread(() -> a.set("zhangsan", 100.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("zhangsan"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("zhangsan"));

    }
}
