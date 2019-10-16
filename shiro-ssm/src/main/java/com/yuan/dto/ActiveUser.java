package com.yuan.dto;

import com.yuan.model.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuan
 * @date 2019/10/13 7:22 下午
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ActiveUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private User user;
    private List<String> roles;
    private List<String> permissions;

}
