package com.coffee.repository;

import com.coffee.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

//회원 정보들을 이용하여 데이터베이스와 교신하는 인터페이스입니다.
// 이전의 DAO 역할
//JpaRepository<엔티티이름, 해당 엔티티의 기본키변수의 타입>
public interface MemberRepository extends JpaRepository<Member,Long> {

}
