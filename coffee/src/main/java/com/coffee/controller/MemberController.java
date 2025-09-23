package com.coffee.controller;

import com.coffee.entity.Member;
import com.coffee.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController //해당 클래스는 회원과 관련된 웹 요청 (from react)을 접수하여 처리해주는 컨트롤러 클래스입니다.
@RequiredArgsConstructor // final 키워드 또는 @NotNull 필드가 들어있는 식별자에 값을 생성자를 통하여 외부에서 주입해줍니다.
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;//자동 생성자,객체생성

//    public MemberController(MemberService memberService) {
//    this.memberService = memberService;
//}

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody Member bean, BindingResult bindingResult) {
        //회원가입을 위한 컨트롤러 메소드
        //ResponseEntity : http 응답코드 (숫자형식)나, 적절한 메시지등을 표현하기위한 클래스입니다.
        // json : JavaScript Object Notation
        //@RequestBody : 제이슨 형태의 문자열을 자바 객체타입으로 변환해 줍니다.
        //@Valid : 입력데이터에 대한 유효성 검사를 수행하는 어노테이션입니다.
        //bindingResult : 유효성 검사시 문제가 있으면 이 객체에 해당 예외 정보들이 포함됩니다.
        System.out.println(bean);

        System.out.println("유효성 오류갯수");
        System.out.println(bindingResult.getFieldErrorCount());
//여기서 field는 각각의 변수를 말합니다.
        if (bindingResult.hasErrors()) {//유효성 검사에 문제가 있으면
            //Map<컬럼이름,오류메시지>형식으로 만들어서 클라이언트에게 전송해줍니다.
            Map<String, String> errors = new HashMap<>();
            for(FieldError error:bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        //입력된 이메일을 이용하여 이메일 중복 체크를 합니다.
        Member member = memberService.findByEmail(bean.getEmail());

        if (member != null) {//이미 존재하는 이메일
            return new ResponseEntity<>(Map.of("email", "이미 존재하는 이메일 주소 입니다."), HttpStatus.BAD_REQUEST);
        } else {// 회원가입처리
            memberService.insert(bean);
            return new ResponseEntity<>("회원가입성공", HttpStatus.OK);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Member bean){
        //bean은 클라이언트가 기입한 로그인 정보를 담고있는 객체
        System.out.println("클라이언트에서 로그인 요청 들어옴");
        System.out.println(bean);

        //member는 데이터 베이스에서 내가 조회한 회원 정보를 담고있는 객체
        Member member = this.memberService.findByEmail(bean.getEmail());

        boolean isFound = false ; //회원이 발견되면 true로 바뀜

        if(member != null){//아이디가 존재함
            if(bean.getPassword().equals(member.getPassword())){//비번까지 일치함
                isFound = true ;
            }
        }
        // response : 클라이언트에게 넘겨주고자 하는 응답 정보의 모음
        Map<String, Object> response = new HashMap<>();

        if(isFound == true){
            response.put("message", "success");
            response.put("member", member);
            return ResponseEntity.ok(response);
        }else {

            response.put("message", "아이디 또는 비번이 잘못되었습니다.");
            //응답코드 401은 인증 실패( Unauthorized)를 의미합니다.
            return ResponseEntity.status(401).body(response);
        }

    }

}
