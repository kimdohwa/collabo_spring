package com.coffee.entity;

import com.coffee.constant.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
@Entity // 해당 클래스를 엔티티로 관리해주세요
//회원 1명에 대한 정보를 저장하고 있는 자바 클래스
@Table(name = "Members") // 테이블 이름은 Members로 생성해주세요
public class Member {
    @Id // 이 컬럼은 primary key 입니다.
    @GeneratedValue(strategy = GenerationType.AUTO) //기본키 생성 전략
    @Column(name="member_id") // 컬럼이름을 변경합니다
    private Long id;
    private String name;
    @Column(unique = true, nullable = false) // 필수사항이고 절대 동일한 값이 들어갈수없음
    private String email;
    private String password;
    private String address;
    @Enumerated(EnumType.STRING) //컬럼에 문자열 형식으로 데이터가 들어감
    private Role role ; //일반인 또는 관리자
    private LocalDate regdate ; //등록일자

}
