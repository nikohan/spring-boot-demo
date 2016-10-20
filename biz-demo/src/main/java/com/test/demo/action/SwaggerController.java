package com.test.demo.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by nikohan on 2016/7/24.
 */
@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    @RequestMapping(value = "/demo", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> view(@RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return map;
    }
}
