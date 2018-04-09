package hc02_stopAtFive;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/7.
 */
public class MyContainer4 {

    volatile List lists = new ArrayList();


    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer4 c = new MyContainer4();
        final Object lock = new Object();
        new Thread(() -> {
            System.out.println("t2 启动");

            synchronized (lock) {
                if (c.size() != 5) {

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println("t2 结束");
                //通知t1继续执行
                lock.notify();
            }

        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1启动");

            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add" + i);
                    if (c.size() == 5) {
                        lock.notify();

                        //释放锁，t2得以继续执行
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();


    }

}
