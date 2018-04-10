package hc09_threadPool;

import java.util.concurrent.*;

/**
 * Created by ZJYYY on 2018/4/8.
 * 未来的结果
 * <T> Future <T> submit(Callable<T> task)
 * 未来的收么时间点上这个任务执行完会返回某个结果，future是Callable的返回值
 */
public class T06_Future {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureTask<Integer> task = new FutureTask<>(() -> {
            //Callable  -- 返回值是一个integer类型
            //futuretask是和runnabletask作区分的
            //因为run方法不返回 是void， future是可以包装一个callable类型
            //callable中的call方法返回一个泛型，必须制定反省是一个什么类型，我们用FutureTask来指定是什么类型，
            // 所以我们的call方法必须返回的是一个ing类型
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });
        //上面的代码相当于
        //创建一个匿名类，
        //new Callable<>(){ int call();}

        new Thread(task).start();//start在500ms后结束
        System.out.println(task.get());//futuretask的get阻塞方法 一直等着任务执行完，设么时候执行完什么时候使用get方法等结果

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f = service.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });
//        System.out.println(f.get());
        System.out.println(f.isDone());
        System.out.println(f.get());
        System.out.println(f.isDone());

    }
}
