package hc09_threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/8.
 * 线程池就是一堆线程，装在某个容器里，等着你来运行的
 * java的所有线程池都实现了ExecutorService的接口
 * 可以执行任务
 * 线程池中的线程在执行完任务之后不会消失，一直在这个池子里，
 * 这个线程应停下来了，如果有新的任务来直接用这个线程不需要新启动线程
 * 效率高 并发性比较好
 * 启动和关闭线程会消耗系统资源的
 *
 */
public class T05_ThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        //用Executors的工厂方法new出来一个线程池，这个线程池是fixed固定个数的，个数为5的线程池，需要的时候启动
        //newFixedThreadPool返回值，一定是实现了ExecutorService的接口
        //ExecutorService的接口 是可以往里面扔任务的，有两种方法：execute-runnable没有返回值 和 submit--两者

        for(int i=0;i<6;i++) {
            //第六个任务 在队列里排队
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                //执行的任务是：睡500ms然后打印当前线程的名称
            });
        }
        System.out.println(service);

        //shutdown是正常关闭 等所有任务执行完才关闭
        //shutdownnow二话不说就关了
        service.shutdown();
        System.out.println(service.isTerminated());
        //所有任务是否执行完
        System.out.println(service.isShutdown());
        //是否关闭了
        System.out.println(service);
        //

        TimeUnit.SECONDS.sleep(5);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);

    }
}
