package com.hillbo.javathread.test;

import com.hillbo.javathread.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class ConcurrencyTest {

    // 请求总户数
    private static int clientTotal = 5000;

    // 同时并发请求数
    private static int threadTotal = 200;

    private static int count = 0;

    // 计数方法
    private static void add (){
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        // 定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量，给出允许并发的数量
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 定义计数器闭锁，传入请求总数，在请求总数之后进行统计
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0;i < clientTotal; i++){
            // 把请求全部放入到线程池里面
            executorService.execute(() -> {
                try{
                    // 引入信号量,判断当前线程是否允许被执行，如果达到一定并发量，add方法可能临时被阻塞掉，acquire方法能返回值之后，add方法才会执行
                    semaphore.acquire();
                    add();
                    // 释放当前一个线程
                    semaphore.release();
                } catch (Exception e){
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }

        // 在所有线程执行完之后，打印当前计数的值。在之前需调用CountDownLatch的await方法，保证countDownLatch必须减为0
        countDownLatch.await();
        // 关闭线程池
        executorService.shutdown();
        log.info("count:{}", count);
    }

}
