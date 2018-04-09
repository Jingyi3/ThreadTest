package hc08_concurrentContainer;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by ZJYYY on 2018/4/8.
 * 写时复制 copy on write
 * 多线程环境下 写时效率低 读时效率高
 * 适合写少 读多多多多的环境
 * <p>
 * 读的时候不需要加锁
 * 事件监听器 读得多，加新的监听器少
 */
public class T02_CopyOnWriteList {

    public static void main(String[] args) {
        List<String> lists = new CopyOnWriteArrayList<>();
        //List<String> lists2 = new ArrayList<>();//这个会出现并发问题
        //List<String> lists3 = new Vector<>();
        Random random = new Random();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++)
                        lists.add("a" + random.nextInt(10000));
                }
            };
            threads[i] = new Thread(task);
        }

        runAndComputeTime(threads);

        System.out.println(lists.size());
    }

    static void runAndComputeTime(Thread[] ths) {
        Long s1 = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t -> t.start());
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }

}
