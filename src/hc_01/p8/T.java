package hc_01.p8;

import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/6.
 * 一个同步方法可以调用另外一个同步方法
 * 一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁
 * 也就是说synchronized获得的锁是可重入的(获得了这把锁之后还可以获得)
 * 死锁：同一线程 同一把锁
 */
public class T {
    synchronized void m1() {
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        m2();
    }

    synchronized void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }
}
