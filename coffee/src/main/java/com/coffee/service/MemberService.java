package com.coffee.service;

import com.coffee.constant.Role;
import com.coffee.entity.Member;
import com.coffee.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service //서비스 역할을 하며, 주로 로직처리에 활용되는 자바클래스입니다.
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository ;
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public void insert(Member bean) {
        //사용자 역할과 등록일자는 요기서 넣어줍니다.
        bean.setRole(Role.User);
        bean.setRegdate(LocalDate.now());
        memberRepository.save(bean); //주의 Repository 인서트작업은 save()메소드를 사용합니다.
    }
}
