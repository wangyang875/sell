package com.imooc.service.impl;

import com.imooc.exception.SellException;
import com.imooc.service.RedisLock;
import com.imooc.service.SeckillService;
import com.imooc.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SeckillServiceimpl implements SeckillService {
    @Autowired
    private RedisLock redisLock;
    private static final int TIMEOUT=10*1000;//超时时间10s
    /*
    模拟特价商品
     */
    static Map<String,Integer>  products;
    static Map<String,Integer>  stock;
    static Map<String,String >  orders;
    static {
        products=new HashMap<>();
        stock=new HashMap<>();
        orders=new HashMap<>();
        products.put("12345",100000 );
        stock.put("12345",100000 );
    }
    private String queryMap(String productId){
        return "特价商品限量份"+products.get(productId)
                +"还剩"+stock.get(productId)+"份"
                +"该商品成功下单的数目为"
                +orders.size()+"人";
    }


    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    /**
     * 保证这段代码单线程访问
     * 方法一：方法前加sychronized关键字
     * 方法二：redis分布式锁
     */
    public void orderProductMockDiffUser(String productId) {
        //加锁
        long time=System.currentTimeMillis()+TIMEOUT;
        if(!redisLock.lock(productId,String.valueOf(time))){
            throw new SellException(101,"哎哟喂，人太多了。。");
        };

        //查询商品的库存，为0则活动结束
        Integer stockNum=stock.get(productId);
        if (stockNum==0){
            throw new SellException(100,"活动结束");
        }else {
            //下单
            orders.put(KeyUtil.getUnique(), productId);
            //减库存
            stockNum=stockNum-1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId,stockNum );
        }
        //解锁
            redisLock.unlock(productId,String.valueOf(time) );

    }
}
