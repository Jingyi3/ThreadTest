package hc02_stopAtFive;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/7.
 * 给lists添加volatile之后，t2能够的道童子，但是，t2线程的四训华很浪费cpu，
 * 如果不用死循环，该怎么做呢？
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证t2限制性，也就是首先t2监听才可以
 *
 * 阅读下面的程序，并分析结果
 * 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才接收通知而突出
 * 想想这是为什么？
 *
 * 答：因为notify只是通知不用wait了，但是并不释放锁，只有等t1结束了之后，t2才能继续执行
 *
 */
public class MyContainer3 {

    //添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {

        MyContainer3 c = new MyContainer3();
        final Object lock = new Object();

        new Thread (() -> {
            synchronized (lock) {
                System.out.println("t2 启动");

                if (c.size() != 5) {
                    try {
                        lock.wait();//生产者消费者模型 需要while 这里不需要用while 直接if 就可以
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
            }
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1 启动");
            synchronized (lock) {
                for (int i= 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);

                    if (c.size() == 5) {
                        lock.notify();
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t1").start();
    }
}
