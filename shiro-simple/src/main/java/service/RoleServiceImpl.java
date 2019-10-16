package service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yuan
 * @date 2019/10/6 11:53 下午
 */
public class RoleServiceImpl implements RoleService {
    @Override
    public List<String> findRolesByUser(Integer userId) {
        switch (userId) {
            case 1:
                return Arrays.asList("role1");
            case 2:
                return Arrays.asList("role2");
            case 3:
                return Arrays.asList("role2", "role3");
            default:
                return Collections.emptyList();
        }
    }
}
