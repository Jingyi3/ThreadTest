package hc08_concurrentContainer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ZJYYY on 2018/4/9.
 * !!!
 * https://blog.csdn.net/sunxianghuang/article/details/52221913
 */
public class T01_ConcurrentMap {

    public static void main(String[] args) {

        //有map就有set
        Map<String,String> map = new Hashtable<>();
        //效率低 用的少 所有方法都加了锁 对整个table加锁
        Map<String,String> map1 = new ConcurrentHashMap<>();
        //将table 分成16段，然后每一次往里插入的时候只锁定一个部分lock 把锁细化了
        //可以同时并发的往里插 不是锁定的整个对象

        Map<String,String> map2 = new ConcurrentSkipListMap<>();
        //插入效率高，并且排好序了 g高并发 跳表Map

        Map<String,String> map3 = new HashMap<>();
        //Collections.synchronizedXXX 实现锁
        //TreeMap--用树来实现--非并发


        Random r = new Random();
        Thread[] threads = new Thread[100];
        //100个线程数组
        CountDownLatch latch =new CountDownLatch(threads.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i<threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j <10000;j++) {
                    map.put("a" + r.nextInt(100000),"a"+r.nextInt(100000));
                    //往map里面装了一万个随机字符串
                    latch.countDown();
                    //门闩数减一
                }
            });
        }
        Arrays.asList(threads).forEach(t->t.start());
        //所有线程都启动
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //主线程在这里等着，等到所有线程结束，计算一下时间
        //查看再多线程并发的情况下的效率问题

        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
