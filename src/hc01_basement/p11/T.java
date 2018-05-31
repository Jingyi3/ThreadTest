package hc01_basement.p11;

import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/6.
 * 【【【volatile】】】
 * volatile关键字，使一个变量在多个线程间可见
 * A，B线程都用到一个变量，java默认是A线程中阿伯刘一份copy，这样如果B线程修改了该变脸，则线程A未必知道
 * 使用volatile关键字，会让所有线程都读到变量的修改至
 * <p>
 * 在下面的diamante中，running是存在于堆内存的t对象中
 * 当线程t1 开始运行的时候，会把running值从内存中读到t1 线程的工作区，在运行的过程中直接使用中合格copy，
 * 并不会每次都会去读堆内存，这样当主线程修改running的值之后，t1线程感知不到，搜易不会停止运行
 * <p>
 * 使用volatile，将会强制所有的线程都去堆内存中堆区running的zhi
 * <p>
 * 可以阅读
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 * <p>
 * volatile并不能够保重多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能够代替synchronized
 */
public class T {

    volatile boolean running = true;

    void m() {
        System.out.println("m start");
        while (running) {//死循环

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {//主函数也是一个线程
        T t = new T();
        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running = false;
    }
}
