package hc08_concurrentContainer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/8.
 *
 * LinkedBolckingQueue
 * 做一个生产者消费者的解耦合队列
 * 无界队列
 */
public class T05_LinkedBlockingQueue {

    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();

    static Random random = new Random();

    public static void main(String[] args) {
        new Thread(()->{
            //一个生产者线程
            for (int i= 0; i<100;i++)
            {
                try {
                    strs.put("a"+i);
                    //put方法如果满了就会一直等待
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"p1").start();

        for (int i = 0; i <5 ;i++) {
            //五个消费者线程不停地消费
            new Thread(()-> {
                for(;;){
                    try {
                        System.out.println(Thread.currentThread().getName() + "take - " + strs.take());
                        //如果空了就会一直等待
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c" + i).start();
        }
    }

}
