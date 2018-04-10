package hc08_concurrentContainer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by ZJYYY on 2018/4/8.
 * 容量为零的队列
 * 来的任何东西，消费者必须马上消费掉，不然的话就会出问题
 * 同步队列是一种特殊的transferqueue
 */
public class T09_SynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        //首先启了一个消费者
        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa");
        //put阻塞等待消费者 put里使用特殊的transferqueue
        strs.add("aaa");
        //add报错
        System.out.println(strs.size());
    }
}
