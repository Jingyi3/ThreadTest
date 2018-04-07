package p19;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ZJYYY on 2018/4/7.
 *
 * reentrantLock可以指定为公平锁
 */
public class ReentrantLock5 extends Thread{

    private static ReentrantLock lock = new ReentrantLock(true);
    //true是公平锁 效率低；不公平锁 效率高

    public void run() {
        for(int i = 0; i<1000; i++) {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 reentrantLock5 = new ReentrantLock5();
        Thread thread1 = new Thread(reentrantLock5);
        Thread thread2 = new Thread(reentrantLock5);
        thread1.start();
        thread2.start();
    }
}
