package com.coffee.controller;

import com.coffee.entity.Fruit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// 컨트롤러는 특정 요청에 대한 처리를 수행해줍니다.

@RestController
public class FruitController {
    @GetMapping("/Fruit")
    public Fruit test() {
        Fruit bean = new Fruit();
        bean.setId("banana");
        bean.setName("바나나");
        bean.setPrice(1000);
        return bean;
    }
    @GetMapping("/Fruit/list")
    public List<Fruit> test02(){
        List<Fruit> fruitList = new ArrayList<>();
        fruitList.add(new Fruit("apple","사과",100));
        fruitList.add(new Fruit("pear","배",200));
        fruitList.add(new Fruit("grape","포도",300));
        return fruitList;
    }
}

