package hc01_basement.p1;

/**
 * Created by ZJYYY on 2018/4/6.
 */
public class T {

    private int count = 10;
    private Object o = new Object();

    public void m() {
        synchronized (o) {//任何线程此昂要执行线面的代码，必须拿到o的锁
            count--;
            System.out.println(Thread.currentThread().getName() +
                    " count = " + count);
        }
    }
}
