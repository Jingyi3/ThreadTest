package hc01_basement.p17;

/**
 * Created by ZJYYY on 2018/4/7.
 *
 * 【不要以字符串常量作为锁定的对象】
 * 在下面的例子中，m1和m2其实锁定的是用一个对象
 * 这种情况还会发生比较诡异的现象，比如你用到了一个类库，在该类库中代码锁定了字符喜欢"hello"
 * 但是你读不到源码，所以你在自己的代码里也锁定了"hello"，这个时候就有可能发生非常诡异的死锁阻塞
 * 因为你的程序和你用到的类库不经意间使用了同一把锁
 */
public class T {

    String s1 = "Hello" ;
    String s2 = "Hello" ;
    //两个完全相同的字符串 实际上是一个对象
    //下面两个函数实际上是锁定的同一个对象


    void m1() {
        synchronized (s1) {


        }
    }


    void m2() {
        synchronized (s2) {


        }
    }
}
