package service;

import entity.User;

/**
 * @author yuan
 * @date 2019/10/6 11:33 下午
 * 模拟数据库查询用户
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUserByName(String userName) {
        int id = 0;
        switch (userName) {
            case "yuan":
                id = 1;
                break;
            case "zhangsan":
                id = 2;
                break;
            case "lisi":
                id = 3;
                break;
            default:
                break;
        }

        return new User(id, userName, "302fb51e693fac0af0c92f2ea16373f1", "上海", User.Gender.MALE);
    }
}
