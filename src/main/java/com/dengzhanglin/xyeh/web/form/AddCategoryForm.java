package com.dengzhanglin.xyeh.web.form;

import javax.validation.constraints.*;

public class AddCategoryForm {

    @NotNull
    @Size(min = 1, max = 10)
    private String name;

    @Min(value = 0, message = "无效的父级分类")
    private Integer pid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "AddCategoryForm{" +
                "name='" + name + '\'' +
                ", pid=" + pid +
                '}';
    }
}
