package com.hillbo.javathread.example.atomic;

import com.hillbo.javathread.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample6 {

    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    // 请求总户数
    private static int clientTotal = 5000;

    // 同时并发请求数
    private static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {
        // 定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量，给出允许并发的数量，即申请一个许可，许可池-1，若许可池为0则申请许可失败，阻塞线程
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 定义计数器闭锁，传入请求总数，在请求总数之后进行统计
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            // 把请求全部放入到线程池里面
            executorService.execute(() -> {
                try {
                    // 引入信号量,判断当前线程是否允许被执行，如果达到一定并发量，add方法可能临时被阻塞掉，acquire方法能返回值之后，add方法才会执行
                    semaphore.acquire();
                    test();
                    //释放一个许可，许可池+1
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }

        // 在所有线程执行完之后，打印当前计数的值。在之前需调用CountDownLatch的await方法，保证countDownLatch必须减为0
        countDownLatch.await();
        // 关闭线程池
        executorService.shutdown();
        log.info("isHappened:{}", isHappened.get());
    }

    private static void test() {
        if(isHappened.compareAndSet(false,true)){
            log.info("execute");

        }
    }

}
