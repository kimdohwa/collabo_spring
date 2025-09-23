package com.coffee.repository;

import com.coffee.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findProductByOrderByIdDesc(); // 상품의 아이디를 역순으로 정렬하여 상품목록을 보여주어야합니다.

    /* JpaRepository를 상속받으면 메서드
    findById(Long id)	ID로 조회
    findAll()	전체 목록 조회
    save(Member member)	저장 (insert or update)
    deleteById(Long id)	ID로 삭제
    count()	총 개수
    existsById(Long id)	존재 여부 확인
    를 사용가능*/


}
