package hc01_basement.p13;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZJYYY on 2018/4/7.
 */
public class T {
    int count = 0;

    synchronized void m(){
        for (int i= 0; i < 10000; i++) {
            count++;
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
