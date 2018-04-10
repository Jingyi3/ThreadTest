package hc08_concurrentContainer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/8.
 * 有界队列
 * 线程池中装的都是一个个任务
 */
public class T06_ArrayBlockingQueue {

    static BlockingQueue<String> strs = new ArrayBlockingQueue<String>(10);
    static Random r = new Random();

    public static void main(String[] args) throws  InterruptedException {
        for(int i =0 ; i <10; i++) {
            strs.put("a" +i);
        }
        //strs.put("aaa");
        //put方法满了会阻塞，无线式阻塞,阻塞就是程序停在这里不停地在等待
        //strs.add("aaa");
        //会抛异常
        //strs.offer("aaa");
        //返回boolean
        strs.offer("aaa",1, TimeUnit.SECONDS);
        //一秒钟后加不进去就不往里加了

        System.out.println(strs);
    }
}
