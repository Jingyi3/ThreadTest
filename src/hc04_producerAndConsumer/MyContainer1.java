package hc04_producerAndConsumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/7.
 * 面试题，写一个固定容量同步器，拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程，以及10个消费者线程的阻塞调用
 * 生产者--消费者模型
 * notify 和 condition
 */
public class MyContainer1<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;//最多10个元素
    private int count = 0;

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();
        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }
    }

    public synchronized void put(T t) {
        while (lists.size() == MAX) {
            //这里用while不用if
            //因为wait的时候这个线程就停在wait这一步，不往下执行，一旦被唤醒，就从wait的下一步开始执行
            //因此while比if会多执行一步
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lists.add(t);
        ++count;
        this.notifyAll();//通知消费者进程进行消费

    }

    public synchronized T get() {
        T t = null;
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count--;
        this.notifyAll();//通知生产者进程进行生产
        return t;
    }

}