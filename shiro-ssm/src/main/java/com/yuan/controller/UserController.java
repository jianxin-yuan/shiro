package com.yuan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuan
 * @date 2019/10/13 6:52 下午
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/query")
    public Map<String, Object> query() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("result", "query");
        return map;
    }

    @RequestMapping("/add")
    public Map<String, Object> add() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("result", "add");
        return map;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("result", "delete");
        return map;
    }

    @RequestMapping("/update")
    public Map<String, Object> update() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("result", "update");
        return map;
    }

    @RequestMapping("/export")
    public Map<String, Object> export() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("result", "export");
        return map;
    }
}
