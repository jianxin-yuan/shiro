package com.yuan.model;

/**
 * 权限
 *
 * @author yuan
 * @date   2019-10-13
 */
public class Permission {
    /**
     * 权限ID
     */
    private Integer id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限编码
     */
    private String code;

    public Permission(Integer id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Permission() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}