package com.test.demo.job;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * Created by zhaohan on 2016/8/1.
 */
public class TimerTest extends TimerTask {

    private Timer timer;

    public TimerTest(Timer timer) {
        this.timer = timer;
    }

    public static void start() {
        TimerTest timerTest = new TimerTest(new Timer());
        //立刻开始执行timerTest任务，只执行一次
//        timerTest.getTimer().schedule(timerTest,new Date());
        //立刻开始执行timerTest任务，执行完本次任务后，隔2秒再执行一次
//        timerTest.getTimer().schedule(timerTest,new Date(),2000);
        //一秒钟后开始执行timerTest任务，只执行一次
        //timerTest.getTimer().schedule(timerTest,1000);
        //一秒钟后开始执行timerTest任务，执行完本次任务后，隔2秒再执行一次
        //timerTest.getTimer().schedule(timerTest,1000,2000);
        //立刻开始执行timerTest任务，每隔2秒执行一次
        timerTest.getTimer().scheduleAtFixedRate(timerTest,new Date(),2000);
        //一秒钟后开始执行timerTest任务，每隔2秒执行一次
        //timerTest.getTimer().scheduleAtFixedRate(timerTest,1000,2000);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timerTest.getTimer().cancel();
    }

    @Override
    public void run() {
        System.out.println("[" + new Date() + "] Timer task is running");
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
