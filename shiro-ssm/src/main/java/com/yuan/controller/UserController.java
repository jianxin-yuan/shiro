package com.yuan.controller;

import com.yuan.model.User;
import com.yuan.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuan
 * @date 2019/10/13 6:52 下午
 * 启用shiro 注解 检查访问权限
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequiresPermissions(("user:query"))
    @RequestMapping("/query")
    public Map<String, Object> query() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("result", "query");
        return map;
    }

    @RequiresPermissions(("user:add"))
    @RequestMapping("/add")
    public Map<String, Object> add() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("result", "add");
        return map;
    }

    @RequiresPermissions(("user:delete"))
    @RequestMapping("/delete")
    public Map<String, Object> delete() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("result", "delete");
        return map;
    }

    @RequiresPermissions(("user:update"))
    @RequestMapping("/update")
    public Map<String, Object> update() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("result", "update");
        return map;
    }

    @RequiresPermissions(("user:export"))
    @RequestMapping("/export")
    public Map<String, Object> export() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("result", "export");
        return map;
    }


    @PostMapping("/register")
    public Map<String, String> register(String userName, String password, String gender, String address) {
        Map<String, String> map = new HashMap<>();
        User user = User.builder().username(userName).password(password)
                .address(address).gender(gender).build();
        userService.save(user);
        map.put("code", "0000");
        map.put("message", "success");
        return map;
    }
}
