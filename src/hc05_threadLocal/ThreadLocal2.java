package hc05_threadLocal;

import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/7.
 *
 * ThreadLocal 局部变量
 * 每个线程在自己的空间都有自己的对象，相当于都拷贝了一份
 * 在效率上更高
 * 如果自己改变 自己维护 不需要通知其他线程那么就是用ThreadLocal
 *
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如框架中hibernate中session就存在于与ThreadLocal中，避免synchronized的使用
 *
 */
public class ThreadLocal2 {

    //volatile static Person person = new Person();//不写volatile有可能发生问题
    static ThreadLocal<Person> personThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(personThreadLocal.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            personThreadLocal.set(new Person());
        }).start();

        //自己的线程自己用，别的线程不能得到我的线程里面的东西进行修改
    }

    static class Person {
        String name = "zhangsan";
    }

}
