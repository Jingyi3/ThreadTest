package hc08_concurrentContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ZJYYY on 2018/4/8.
 */
public class T03_SynchronizedList {
    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();//没有锁
        List<String> strsyn = Collections.synchronizedList(strs);//给上面这个加锁
    }
}
