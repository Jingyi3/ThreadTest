package hc08_concurrentContainer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/8.
 *
 * DelayQueue
 * 默认排好顺序：默认等待时间最长的可以在最前面被往外拿走
 * 每一个元素自己记载着我还有多上时间可以被消费者拿走
 */
public class T07_DelayQueue {
    static BlockingQueue<MyTask> tasks = new DelayQueue();

    static Random random = new Random();

    static class MyTask implements Delayed {
        //内部类要继承Delayed接口，从comparable接口继承，所以实现两个方法
        long runningTime;
        MyTask(long rt) {
            this.runningTime = rt;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS)<o.getDelay(TimeUnit.MICROSECONDS)){
                return -1;
            } else if(this.getDelay(TimeUnit.MICROSECONDS.MILLISECONDS)>o.getDelay(TimeUnit.MICROSECONDS)){
                return 1;
            }
            return 0;
        }

        public String toString() {
            return "" +runningTime;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask(now + 1000);
        MyTask t2 = new MyTask(now + 2000);
        MyTask t3 = new MyTask(now + 1500);
        MyTask t4 = new MyTask(now + 2500);
        MyTask t5 = new MyTask(now + 500);

        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);

        System.out.println(tasks);

        for (int i = 0; i<5; i++){
            System.out.println(tasks.take());
        }

    }


}
