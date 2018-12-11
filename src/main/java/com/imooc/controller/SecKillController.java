package com.imooc.controller;

import com.imooc.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
@Slf4j
public class SecKillController {
    @Autowired
    private SeckillService seckillService;

    /**
     * 查询秒杀商品的库存
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) throws Exception {
        return seckillService.querySecKillProductInfo(productId);
    }

    /**
     * 秒杀
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/order/{productId}")
    public String sKill(@PathVariable String productId) throws Exception {
        log.info("@sKill request,productId:" + productId);
        seckillService.orderProductMockDiffUser(productId);
        return seckillService.querySecKillProductInfo(productId);
    }
}
