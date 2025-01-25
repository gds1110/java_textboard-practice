package com.sis.exam.board.controller;

import com.sis.exam.board.service.ArticleService;
import com.sis.exam.board.service.MemberService;
import com.sis.exam.board.vo.Member;
import com.sis.exam.board.vo.Rq;
import com.sis.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;

public class UserMemberController {

    private MemberService memberService;

    public UserMemberController(){

        memberService = Container.getMemberService();

        memberService.MakeTestData();
    }



    public void actionJoin(Rq rq) {
        System.out.println("==회원 가입==");
        System.out.printf("로그인 아이디 : ");
        String loginId = Container.getSc().nextLine();

        System.out.printf("로그인 패스워드 : ");
        String loginPw =Container.getSc().nextLine();

        System.out.printf("로그인 패스워드 확인 : ");
        String loginPwConfirm =Container.getSc().nextLine();

        if(loginPw.equals(loginPwConfirm)==false)
        {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;

        }

        System.out.printf("이름 : ");
        String name = Container.getSc().nextLine();


        int id = memberService.join(loginId,loginPw,name);

        System.out.printf("\"%s\"님 회원 가입을 환영합니다..\n",memberService.getMemberById(id).getName());



    }

    public void actionLogin(Rq rq)
    {

        if(rq.isLogined())
        {
            System.out.println("이미 로그인 되어있습니다.");
            System.out.println("로그아웃 해주세요");
            return;

        }

        System.out.println("==로그인==");
        System.out.printf("로그인 아이디 : ");

        String loginId = Container.getSc().nextLine();


        if(loginId.trim().length()==0)
        {
            System.out.println("로그인 아이디를 입력해주세요.");
            return;
        }

        Member member = memberService.getMemberLoginId(loginId);

        if(member==null)
        {
            System.out.println("해당 아이디는 존재하지 않습니다.");
            return;
        }

        System.out.printf("로그인 패스워드 : ");
        String loginPw =Container.getSc().nextLine();
        if(loginPw.trim().length()==0)
        {
            System.out.println("로그인 패스워드를 입력해주세요.");
            return;
        }

        if(member.getLoginPw().equals(loginPw)==false)
        {
            System.out.println("로그인 패스워드가 일치하지 않습니다.");
            return;
        }

        rq.login(member);

        System.out.printf("\"%s\"님 환영합니다. \n",member.getName());
    }


    public void actionLogout(Rq rq) {

        if(rq.isLogined()==false)
        {
            System.out.println("로그아웃 상태입니다.");
            return;
        }


        rq.logout();
        System.out.println("로그아웃 완료");
    }
}
