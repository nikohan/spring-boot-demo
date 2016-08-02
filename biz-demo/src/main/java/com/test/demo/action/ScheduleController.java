package com.test.demo.action;

import com.alibaba.fastjson.JSONObject;
import com.test.demo.job.QuartzTest;
import com.test.demo.job.TimerTest;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * a simple example for schedule
 * Created by nikohan on 2016/7/24.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @ResponseBody
    @RequestMapping("/timer")
    public Map<String, Object> execWithTimer(HttpServletRequest request) {

        JSONObject jsonObject = new JSONObject();
        TimerTest.start();
        jsonObject.put("timer", "the task is end");
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping("/quartz")
    public Map<String, Object> execWithQuartz(HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();
        try {
            QuartzTest.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
            jsonObject.put("msg", "error");
            return jsonObject;
        }
        jsonObject.put("timer", "the task is end");
        return jsonObject;
    }
}



