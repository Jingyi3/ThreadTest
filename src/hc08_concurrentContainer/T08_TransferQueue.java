package hc08_concurrentContainer;

import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by ZJYYY on 2018/4/8.
 *
 *
 * 使用在更高的并发的状态
 *
 * 消费者先启动
 * 生产者消费的时候，不是直接往里扔，而是先看有没有消费者，直接给消费者
 * 如果没有消费者的话，这个线程就会阻塞这
 *
 * 举例：
 * 坦克大战游戏，有三个玩家，开了三个坦克
 * 第一个 发射了一个子弹
 * 发消息给服务器，服务器转发这个消息给其他两个玩家
 * 一般是发到消息队列里，然后一个个线程再继续转发
 * 但是有了LinkedTransferQueue的话，这个消息队列上面本来就有好多消费者线程等着
 * 一点生产者的消息过来，不进队列直接被消费者拿走，效率高
 * 则这个游戏服务器能够支撑的同时的并发的客户端就会多一些
 */
public class T08_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa");
        //都不会阻塞strs.add/offer

        /*new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/

    }
}
