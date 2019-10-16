package entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yuan
 * @date 2019/10/6 11:15 下午
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int id;
    private String userName;
    private String password;
    private String address;
    private Gender gender;

   public enum Gender {
        MALE(0, "男"),
        FEMALE(1, "女");

        private int value;
        private String message;

        Gender(int value, String message) {
            this.value = value;
            this.message = message;
        }
    }
}
