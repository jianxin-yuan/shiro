package service;

import entity.User;

/**
 * @author yuan
 * @date 2019/10/6 11:33 下午
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

        return new User(id, userName, "d5d2e4a0c1dd8c7d666ea65aed5c6a1c", "上海", User.Gender.MALE);
    }
}
