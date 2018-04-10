package hc08_concurrentContainer;

import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by ZJYYY on 2018/4/8.
 *
 * 队列+线程池
 * 做游戏客户端的发消息
 * 很多异步方案，队列解耦合
 */
public class T04_BlockingQueue {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();
        //LinkedQueue是无界队列，什么时候把内存耗完了，什么时候抛异常，否则可以一直往里加

        for(int i= 0; i<10;i++) {
            strs.offer("a" +i);
            //add
        }
        System.out.println(strs);
        System.out.println(strs.size());
        System.out.println(strs.poll());
        //拿出来并删掉
        System.out.println(strs.size());
        System.out.println(strs.peek());
        //拿出来但是不删除
        System.out.println(strs.size());

        //双向队列Queue
        Deque<String> strs2 = new ConcurrentLinkedDeque<>();
        //addFirst/addLast/pollFirst/pollLast

    }


}
