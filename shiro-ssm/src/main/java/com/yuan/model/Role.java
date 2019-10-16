package com.yuan.model;

/**
 * 角色
 *
 * @author yuan
 * @date   2019-10-13
 */
public class Role {
    /**
     * 角色ID
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}