package hc07_ticketseller;

import java.util.Vector;

/**
 * Created by ZJYYY on 2018/4/7.
 */
public class TicketSeller2 {

    static Vector<String> tickets = new Vector<>();
    //同步容器，所有方法本身加过锁
    //Vector方法size原子性，remove原子性，但是两个操作之间有可能被打断

    static {
        for (int i = 0; i < 10000; i++) tickets.add("票编号： " + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i <10; i++) {
            new Thread(()->{
                while(tickets.size()>0) {
                    //在这里可以手动让他睡一段时间
                    //看出这段代码的问题
                    /*try {
                        TimeUnit.MICROSECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/

                    System.out.println("销售了--"+tickets.remove(0));
                }
            }).start();
        }
    }
}
