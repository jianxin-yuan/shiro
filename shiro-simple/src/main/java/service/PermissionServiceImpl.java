package service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yuan
 * @date 2019/10/6 11:57 下午
 */
public class PermissionServiceImpl implements PermissionService {
    @Override
    public List<String> findPermissionByUser(Integer userId) {
        switch (userId) {
            case 1:
            case 3:
                return Arrays.asList("user:query", "user:add", "user:update", "user:delete", "user:export");
            case 2:
                return Arrays.asList("user:query", "user:add", "user:update", "user:delete");
            default:
                return Collections.emptyList();
        }
    }
}
