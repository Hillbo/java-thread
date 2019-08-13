package com.hillbo.javathread.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 如果子类继承父类，是不会继承synchronized关键字的，因为synchronized不属于方法的一部分，子类需显示声明synchronized关键字
 */
@Slf4j
public class SynchronizedExample1 {

    /**
     * 修饰代码块，大括号括起来的代码，作用于调用的对象
     */
    public void test1(int j){
        synchronized (this){
            for(int i=0; i<10;i++){
                log.info("test1--{}-->i={}", j, i);
            }
        }
    }

    /**
     * 修饰一个方法，称之为同步方法，范围是整个方法，作用于调用的对象
     */
    public synchronized void test2(){
        for(int i=0; i<10;i++){
            log.info("test2-->i={}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        SynchronizedExample1 synchronizedExample2 = new SynchronizedExample1();
        // 声明一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.test1(1);
        });

        executorService.execute(() -> {
            synchronizedExample2.test1(2);
        });
    }

}
