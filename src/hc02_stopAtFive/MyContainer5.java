package hc02_stopAtFive;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/7.
 *
 * 上一个方法比较复杂
 * 可以使用门闩 Latch 代替 wait notify来进行通知
 * 好处是通信方式简单，同时也可以指定等待时间
 * 使用await和countdown方法代替wait 和notify
 * CountDownLatch不涉及锁定，当count的值为零的时候，当前线程继续运行
 * 当不实际同步，只涉及线程通信的时候，用synchronized + wait/notify就显得太重了
 * 这个时候就应该考虑CountDownLatch/cyclibarrier/semaphore
 *
 */
public class MyContainer5 {

    //添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer5 c = new MyContainer5();

        CountDownLatch latch = new CountDownLatch(1);
        //往下数的门闩，当前CountDownLatch里面是1，当里面==0时候，这个门闩就开了

        new Thread(()-> {
            System.out.println("t2启动");
            if (c.size()!=5) {
                try {
                    latch.await();
                    //门闩的等待不需要锁定任何对象
                    //也可以指定等待时间
                    //latch.await(5000, TimeUnit.MILLISECONDS);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2结束");
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add" +i);
                if (c.size() == 5) {
                    //打开门闩让t2运行
                    latch.countDown();
                    //并且t1还继续往下运行
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();
    }
}
