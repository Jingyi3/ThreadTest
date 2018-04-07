package hc_reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ZJYYY on 2018/4/7.
 * <p>
 * 需要注意的是----必须要手动释放锁
 * 使用syn锁定当遇到异常，jvm会自动释放锁，但是咯查看必须手动释放，因此常在finally中把锁释放
 *
 * 两个方法实现互斥锁
 */
public class ReentrantLock2 {
    Lock lock = new ReentrantLock();

    void m1() {
        lock.lock();//相当于synchronized（this）
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        lock.lock();
        System.out.println("m2......");
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLock2 reentrantLock2 = new ReentrantLock2();

        new Thread(reentrantLock2::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(reentrantLock2::m2).start();
    }


}
