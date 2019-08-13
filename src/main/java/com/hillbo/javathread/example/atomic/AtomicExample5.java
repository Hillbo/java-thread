package com.hillbo.javathread.example.atomic;

import com.hillbo.javathread.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicExample5 {

    // 字段必须使用volatile修饰，且不能为static
    @Getter
    public volatile int count = 100;

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    public static void main(String[] args) {

        AtomicExample5 atomicExample5 = new AtomicExample5();

        if(updater.compareAndSet(atomicExample5, 100, 120)){
            log.info("update success1, {}", atomicExample5.getCount());
        }

        if(updater.compareAndSet(atomicExample5, 100, 120)){
            log.info("update success2, {}", atomicExample5.getCount());
        }else {
            log.info("update failed, {}", atomicExample5.getCount());
        }

    }

}
