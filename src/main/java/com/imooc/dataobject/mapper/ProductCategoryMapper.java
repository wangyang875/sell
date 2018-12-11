package com.imooc.dataobject.mapper;

import com.imooc.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.Map;

public interface ProductCategoryMapper {
    /*
    增
     */
    @Insert("insert into product_category(category_name,category_type) values (#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);
    @Insert("insert into product_category(category_name,category_type) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);
    /*
    查
     */
    @Select("select * from product_category where category_type=#{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    }
    )
    ProductCategory findByCategoryType(Integer categoryType);
    /*
    改
     */
    @Update("update product_category set category_name=#{categoryName} where category_type=#{categoryType}")
    int updateByCategoryType(@Param("categoryType") Integer categoryType,
                             @Param("categoryName") String categoryName);
    @Update("update product_category set category_name=#{categoryName} where category_type=#{categoryType}")
    int updateByObject(ProductCategory productCategory);
    /*
    删
     */
    @Delete("delete from product_category where category_type=#{categoryType}")
    int deleteByCategoryType(Integer categoryType);
    /*
    xml的方式
     */
    ProductCategory selectByCategoryType(Integer categoryType);

}
