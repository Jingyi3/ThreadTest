package hc_01.p5;

/**
 * Created by ZJYYY on 2018/4/6.
 */
public class T implements Runnable{
    private int count = 10;

    @Override
    public synchronized void run() {//一个synchronized代码块是一个原子操作，原子操作是不可分割的
        count--;
        System.out.println(Thread.currentThread().getName() + "count = " + count);
    }

    public static void main(String [] args) {
        T t = new T(); //只new了一个对象 好多线程共同访问这个对象
        for(int i = 0; i<5; i++) {
            new Thread(t,"Thread" + i).start();
        }
    }
}
