package com.imooc.controller;

import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {
    @Autowired
    private ProductService productService;

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

    @GetMapping("/off_sale")
    public ModelAndView off_sale(@RequestParam("productId") String productId,
                                 Map<String, Object> map) {
        ProductInfo productInfo = new ProductInfo();
        try {
            productInfo = productService.off_sale(productId);
        } catch (SellException e) {
            log.error("【下架商品】 操作失败，productId={}", productId);
            map.put("msg", ResultEnum.PRODUCT_OFF_FAIL.getMsg());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("/common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_OFF_SUCCESS.getMsg());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/common/success", map);
    }

    @GetMapping("/on_sale")
    public ModelAndView on_sale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        ProductInfo productInfo = new ProductInfo();
        try {
            productInfo = productService.on_sale(productId);
        } catch (SellException e) {
            log.error("【上架商品】 操作失败，productId={}", productId);
            map.put("msg", ResultEnum.PRODUCT_ON_FAIL.getMsg());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("/common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_ON_SUCCESS.getMsg());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/common/success", map);
    }
}
