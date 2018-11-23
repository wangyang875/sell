package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.ProductForm;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 分页查询所有商品
     *
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("/product/list", map);
    }

    /**
     * 商品下架
     *
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView off_sale(@RequestParam("productId") String productId,
                                 Map<String, Object> map) {
        ProductInfo productInfo = new ProductInfo();
        try {
            productInfo = productService.off_sale(productId);
        } catch (SellException e) {
            log.error("【下架商品】 操作失败，productId={}", productId);
            map.put("msg", ResultEnum.PRODUCT_OFF_FAIL.getMsg() + ": " + e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("/common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_OFF_SUCCESS.getMsg());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/common/success", map);
    }

    /**
     * 商品上架
     *
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView on_sale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        ProductInfo productInfo = new ProductInfo();
        try {
            productInfo = productService.on_sale(productId);
        } catch (SellException e) {
            log.error("【上架商品】 操作失败，productId={}", productId);
            map.put("msg", ResultEnum.PRODUCT_ON_FAIL.getMsg() + ": " + e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("/common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_ON_SUCCESS.getMsg());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/common/success", map);
    }

    /**
     * 进入修改商品的页面
     *
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }
//查询所有的类目
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("categoryList", productCategoryList);
        return new ModelAndView("/product/index", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            log.error("【新增或修改商品】 参数不正确，productForm={}", productForm);
//            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("/common/error", map);
        }
        ProductInfo productInfo = new ProductInfo();

            if (StringUtils.isEmpty(productForm.getProductId())) {
                productForm.setProductId(KeyUtil.getUnique());
            }
        try {
            productInfo = productService.findOne(productForm.getProductId());
            BeanUtils.copyProperties(productForm, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("/common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_INDEX_ADD_SUCCESS.getMsg());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/common/success",map);
    }
}
