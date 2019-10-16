package com.yuan;

import com.yuan.model.Role;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuan
 * @date 2019/10/13 7:46 下午
 */
public class App {
    public static void main(String[] args) {
        List<Role> list = null;//new ArrayList<>();

        List<String> collect = list != null ? list.stream().map(Role::getName).collect(Collectors.toList()) : null;
        for (String s : collect) {
            System.out.println(s);
        }
    }
}
