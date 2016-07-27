package com.test.demo.effective.builde;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * Created by zhaohan on 2016/7/24.
 */
@RestController
@RequestMapping("/builder")
public class BuilderDemoController {

    @ResponseBody
    @RequestMapping(value = "/demo.do", produces = "application/json; charset=utf-8")
    public Map<String, Object> buildDemo(HttpServletRequest request) {

        Settings settings =
                new Settings.SettingBuilder("1", "2")
                .setting3("3")
                .setting4("4")
                .build();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", settings);

        return jsonObject;
    }
}
