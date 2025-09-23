package com.coffee.controller;

import com.coffee.entity.Product;
import com.coffee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product") // product 가 들어간 요청이왔을때 이페이지와 매칭하게 됨
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService ;

    @GetMapping("/list") // 상품 목록을 List 컬렉션을 가져옵니다.
    public List<Product> list(){

        List<Product> products = this.productService.getProductList();
        System.out.println("프로덕트"+products);

        return  products ;
    }

}
