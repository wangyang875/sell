package com.imooc.utils;

import com.imooc.VO.ResultVO;
import com.imooc.enums.ResponseEnum;

public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(ResponseEnum.Suceess.getCode());
        resultVO.setMsg(ResponseEnum.Suceess.getMsg());
        resultVO.setData(object);
        return resultVO;
    }
    public static ResultVO success(){
        return success(null);
    }
    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(null);
        return resultVO;
    }
}
