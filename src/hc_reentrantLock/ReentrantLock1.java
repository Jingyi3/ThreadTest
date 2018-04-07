package hc_reentrantLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/7.
 * 【重入锁】--手动人工锁
 * reentrantlock用于替代synchronized
 * 本例中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 这里是复习synchronized最原始的语义
 */
public class ReentrantLock1 {

    synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    synchronized void m2() {
        System.out.println("m2......");
    }

    public static void main(String[] args) {
        ReentrantLock1 reentrantLock1 = new ReentrantLock1();

        new Thread(reentrantLock1::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(reentrantLock1::m2).start();
    }
}
