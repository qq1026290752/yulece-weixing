package com.yulece.weixing.controller;

import com.yulece.weixing.service.ProductService;
import com.yulece.weixing.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyichao@28ph.cn
 * @Title: BuyerProductController
 * @Package com.yulece.weixing.controller
 * @Description: 卖家商品信息接口API
 * @Date 2018/4/1414:42
 **/
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/list")
    public ResultVo list(){
        return productService.getProductAll();
    }

}
