package hc07_ticketseller;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by ZJYYY on 2018/4/7.
 * <p>
 * 【并发容器】
 */
public class TicketSeller4 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++)
            tickets.add("票编号：" + i);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String s = tickets.poll();
                    //poll是同步的--conparentset--不是加锁的实现--所以他的效率会高很多
                    //并且上面这句话和下面这句话被打断也没关系，因为顶天了，这个线程再循环判断一次
                    //拿到空指针null然后break了
                    if (s == null) break;
                    else System.out.println("销售了--" + s);
                }
            }).start();
        }
    }
}
