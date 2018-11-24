package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<ProductCategory> productCategoryPage = categoryService.findAll(pageRequest);
        map.put("productCategoryPage", productCategoryPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("/category/list", map);
    }

    /**
     * 进入修改类目的页面
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(categoryId)) {
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("category", productCategory);
        }
        return new ModelAndView("/category/index", map);
    }

    /**
     * 查询某个类目下的商品列表
     * @param categoryId
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("index1")
    public ModelAndView index1(@RequestParam("categoryId") Integer categoryId,
                               @RequestParam(value = "page",defaultValue = "1") Integer page,
                               @RequestParam(value = "size",defaultValue = "5") Integer size,
                               Map<String,Object> map){
        PageRequest pageRequest=PageRequest.of(page-1,size );
        ProductCategory productCategory=categoryService.findOne(categoryId);
        Page<ProductInfo> productInfoPage=productService.findByCategoryType(productCategory.getCategoryType(),pageRequest );
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("/product/list",map);
    }
}
