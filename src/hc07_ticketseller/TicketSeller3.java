package hc07_ticketseller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/7.
 */
public class TicketSeller3 {
    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) tickets.add("票编号： " + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i <10; i++) {
            new Thread(()->{
                while(true) {
                    synchronized (tickets) {
                        //每次都上锁，把判断和操作一起放到锁里，但是效率并不高
                        if (tickets.size()>0) {

                            try {
                                TimeUnit.MILLISECONDS.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("销售了--"+tickets.remove(0));
                        }
                    }
                }
            }).start();
        }
    }
}
