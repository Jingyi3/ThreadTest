package hc05_threadLocal;

import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/7.
 * <p>
 * ThreadLocal线程局部变量
 */
public class ThreadLocal1 {

    volatile static Person person = new Person();//不写volatile有可能发生问题

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(person.name);
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            person.name = "lisi";
        }).start();
    }
}

class Person {
    String name = "zhangsan";
}