package com.liaoxin.service.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: liaoxin
 * @Date: 2023/2/7
 **/
public class CompareAndSwap {

    //值
    private int value;

    /**
     * 同步获取值
     */
    public synchronized int getValue() {
        return value;
    }

    public CompareAndSwap(int value){
        this.value = value;
    }

    /**
     * 比较预期值，相同则交换更新值
     *
     * @param expect
     * @param update
     */
    public synchronized int compareAndSwap(int expect, int update) {
        if (value == expect) {
            value = update;
            return update;
        } else {
            return value;
        }
    }

    /**
     * 比较预期值和更新值是否一直，返回预期值
     *
     * @param expect
     * @param update
     */
    public synchronized boolean compareAndSet(int expect, int update) {
        return expect == compareAndSwap(expect, update);
    }

}
