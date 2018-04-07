package hc_01.p6;

/**
 * Created by ZJYYY on 2018/4/6.
 * 同步方法和非同步方法是否可以同时调用
 * 在一个syn方法运行的时候才需要申请这把锁，而别的方法不需要申请这把锁
 */
public class T {

    public synchronized void m1( ){
        System.out.println(Thread.currentThread().getName() + " m1 start ......");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }

    public void m2(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 ");
    }

    public static void main (String [] args) {
        T t = new T();

        new Thread(()->t.m1(),"t1").start();
        new Thread(()->t.m2(),"t2").start();
        //这个写法就相当于 new 一个Thread 然后new一个runnable对象  然后在runnable对象里面t执行了m1 的方法


//        new Thread(t::m1,"t1").start(); //第一个线程执行的是t这个对象里面的m1方法
//        new Thread(t::m2,"t2").start(); //第二个线程执行的是t这个对象里面m2方法

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                t.m1();
//            }
//        });

    }
}
