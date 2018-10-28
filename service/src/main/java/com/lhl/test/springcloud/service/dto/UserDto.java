package com.lhl.test.springcloud.service.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull(message = "用户ID不能为空", groups = {Update.class})
    @Min(value = 1, message = "用户ID错误", groups = {Update.class})
    private Long id;

    @NotBlank(message = "", groups = {Add.class})
    private String name;

    @Min(value = 1, message = "", groups = {Update.class, Add.class})
    private int age;

    private String gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public static interface Update{}
    public static interface Add{}
}
