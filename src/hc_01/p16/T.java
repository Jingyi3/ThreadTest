package hc_01.p16;

import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/7.
 * 锁定某个对象o，如果o的属性发生变化，不影响锁的使用
 * 但是如果o变成另外一个对象，则锁定的对象发生变化
 * 应该避免将锁定的应用变成另外的对象
 */
public class T {

    Object o = new Object();

    void m() {

        synchronized (o) {//锁在堆内存里new出来的对象上而不是锁在栈内存里o的引用

            while (true) {//死循环，再死循环里不停地打印Thread的名字
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        //启动第一个线程
        new Thread(t::m,"t1").start();


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //创建第二个线程
        Thread t2 = new Thread(t::m,"t2");
        t.o = new Object();
        //锁的对象发生该变，所以t2线程得意执行，如果注释掉上面这句话，线程2 将永远不会得到执行
        t2.start();
    }
}
