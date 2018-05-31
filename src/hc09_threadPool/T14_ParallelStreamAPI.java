package hc09_threadPool;

import java.util.*;

/**
 * Created by ZJYYY on 2018/4/26.
 */
public class T14_ParallelStreamAPI {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
        for (int i= 0;i<10000;i++) {
            nums.add(1000000 + r.nextInt(1000000));
        }

        long start = System.currentTimeMillis();
        nums.forEach(v->isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        nums.parallelStream().forEach(T14_ParallelStreamAPI::isPrime);
        end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    static boolean isPrime(int num) {
        for (int i=2; i<=num/2;i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
