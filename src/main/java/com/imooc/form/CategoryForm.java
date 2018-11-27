package com.imooc.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoryForm {
    private Integer categoryId;
    @NotEmpty(message = "类目名称不能为空")
    private String categoryName;
    @NotNull(message = "类目类型不能为空")
    private Integer categoryType;
}
