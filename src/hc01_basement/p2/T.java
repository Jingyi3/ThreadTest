package hc01_basement.p2;

/**
 * Created by ZJYYY on 2018/4/6.
 */
public class T {

    private int count = 10;

    public void m () {
        synchronized (this) { //必须先拿到this的锁  锁定的是一个对象 而不是这段代码块
            count--;
            System.out.println(Thread.currentThread().getName() + " count =  " + count);
        }
    }
}
