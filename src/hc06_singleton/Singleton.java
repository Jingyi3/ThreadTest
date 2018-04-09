package hc06_singleton;

/**
 * Created by ZJYYY on 2018/4/7.
 * 【线程安全的单例模式】
 * 使用内部类的单例模式，既不用加锁，也能实现懒加载
 * <p>
 * 其他实现单例模式的方法
 * 阅读文章：
 * http://www.cnblogs.com/xudong-bupt/p/3433643.html
 * <p>
 * 单例模式
 * 在内存之中永远只能有一个对象
 */
public class Singleton {

    private Singleton() {
        System.out.println("single");
    }

    private static class Inner {
        //使用内部类来实现单例
        //当getSingle的受实际上返回的 是Inner的监听对象
        //不用加锁，并且时间兰家在，只有在我们需要的时候才把它new出来
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getSingle() {
        //无论有多少个线程在调用getsingle的时候拿到的都是同一个对象
        return Inner.singleton;
    }

    public static void main(String[] args) {

    }
}
