package com.yuan.service.impl;

import com.yuan.mapper.UserMapper;
import com.yuan.model.User;
import com.yuan.service.UserService;
import com.yuan.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuan
 * @date 2019/10/13 6:51 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            String salt = EncryptUtil.generateSalt();
            String encryptPwd = EncryptUtil.md5(user.getPassword(), salt, 2);
            user.setSalt(salt);
            user.setPassword(encryptPwd);
            userMapper.insert(user);
        } else {
            userMapper.updateByPrimaryKey(user);
        }
        return user;
    }
}
