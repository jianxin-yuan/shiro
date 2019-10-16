package service;

import entity.User;

/**
 * @author yuan
 * @date 2019/10/6 11:14 下午
 * 用户信息接口
 */
public interface UserService {
    User getUserByName(String userName);
}
