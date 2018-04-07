package hc_01.p4;

/**
 * Created by ZJYYY on 2018/4/6.
 */
public class T {

    private static int count = 10;

    public synchronized static void m() {//这里等同于synchronized(hc_01.p1.T.class)
        count--;
        System.out.println(Thread.currentThread().getName() + " count " + count);

    }

    public static void mm(){
        //静态的额对象和方法不需要new出一个对象就可以直接访问
        //所以这里不可以synchronized（this）
        //当锁定一个静态方法的时候，相当于锁定的是这个类的class对象
        synchronized (T.class) {
            count--;
        }
    }
}
