package com.yuan.service;

import com.yuan.model.User;

/**
 * @author yuan
 * @date 2019/10/13 6:48 下午
 */
public interface UserService {

    /**
     * @param username 用户名
     * @return
     */
    User getUserByName(String username);

    User save(User user);

}
