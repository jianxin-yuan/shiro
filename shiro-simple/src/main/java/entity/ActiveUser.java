package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author yuan
 * @date 2019/10/7 12:01 上午
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActiveUser {
    private User user;
    private List<String> roles;
    private List<String> permissions;
}
