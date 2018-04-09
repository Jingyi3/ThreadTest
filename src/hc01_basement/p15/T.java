package hc01_basement.p15;

import java.util.concurrent.TimeUnit;

/**
 * Created by ZJYYY on 2018/4/7.
 */
public class T {

    int count = 0;

    synchronized void m1() {
        // do sth not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //业务逻辑中只有下面这句需要sync，这时候就不应该给整个方法上锁
        count++;

        // do sth not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    void m2(){
        // do sth not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //业务逻辑中只有下面这句需要sync，这时候就不应该给整个方法上锁
        //采用细颗粒度的锁，可以使线程争用时间变短，从而提高效率

        synchronized (this) {
            count++;
        }

        // do sth not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
