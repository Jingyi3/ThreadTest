package p14;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ZJYYY on 2018/4/7.
 */
public class T {
    AtomicInteger count = new AtomicInteger(0);

    /*synchronized*/ void m(){
        for (int i= 0; i < 10000; i++) {
            //if count.get() <1000;
            //在这里，两个操作之间，还是有可能有其他线程插入进来
            //
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        T t = new T();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m,"threads - "+ i));

        }

        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);

    }

}
