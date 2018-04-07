package stopAtFive;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by ZJYYY on 2018/4/7.
 * <p>
 * 实现一个容器，提供两个方法，add size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5的时候，线程2给出提示并结束
 * <p>
 * 分析下面这个程序，能完成这个功能嘛？
 */
public class MyContainer2 {

    //添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer2 c = new MyContainer2();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(new Object());

                System.out.println("add " + i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(() -> {//此处还不够精确，如果再来一个线程，给i++了呢么t2 等到6的时候才break
            while(true) {
                //t2线程很浪费cpu因为t2是一个死循环一直检测着
                if(c.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束");
        },"t2").start();

    }
}

//为什么t2结束的时候 整个程序都结束了