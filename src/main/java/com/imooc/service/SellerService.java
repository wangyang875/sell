package com.imooc.service;

import com.imooc.dataobject.SellerInfo;

/**
 * 卖家端
 */
public interface SellerService {
    /**
     * 通过openid查询卖家信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
