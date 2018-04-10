package hc09_threadPool;

import java.util.concurrent.Executor;

/**
 * Created by ZJYYY on 2018/4/8.
 * 认识Executor
 * 执行器：只有一个方法，执行runnable这个任务
 * Executor 是一个 interface ，没有具体实现，需要自己实现
 * 实现的函授只有一个
 * void execute(Runnable command)
 *
 * class DirectExecutor implements Executor {
 *     public void executor(Rinnable r){
 *         r.run();
 *         //调用runnable的run方法，相当于方法调用
 *     }
 * }
 *
 * class ThreadPerTaskExecutor implements Executor {
 *     public void execute(Runnable r) {
 *         new Thread(r).start();
 *         //让一个线程来调用这个任务
 *     }
 * }
 *
 */
public class T01_MyExecutor implements Executor{

    public static void main(String[] args) {
        new T01_MyExecutor().execute(()-> System.out.println("hello executor"));
    }

    @Override
    public void execute(Runnable command) {
        //new Thread(command).run();//建立一个线程来，来调用这个任务

        command.run();//方法的直接调用

    }
}
