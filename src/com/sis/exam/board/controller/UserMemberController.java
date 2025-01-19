package com.sis.exam.board.controller;

import com.sis.exam.board.Article;
import com.sis.exam.board.Member;
import com.sis.exam.board.Rq;
import com.sis.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;

public class UserMemberController {

    int memberLastId ;
    List<Member> memberList;


    public UserMemberController(){
        memberLastId =0;
        memberList = new ArrayList<>();

        MakeTestData();
        
        if(memberList.size()>0)
        {
            memberLastId = memberList.get(memberList.size()-1).id;
        }

    }

    public void MakeTestData() {

        for(int i=1;i<3;i++)
        {
            memberList.add(new Member(i,"user"+i,"user"+i,"홍길동"+i));
        }
    }


    public void actionJoin(Rq rq) {
        System.out.println("==회원 가입==");
        System.out.printf("로그인 아이디 : ");
        String loginId = Container.sc.nextLine();

        System.out.printf("로그인 패스워드 : ");
        String loginPw =Container.sc.nextLine();

        System.out.printf("로그인 패스워드 확인 : ");
        String loginPwConfirm =Container.sc.nextLine();

        if(loginPw.equals(loginPwConfirm)==false)
        {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;

        }

        System.out.printf("이름 : ");
        String name = Container.sc.nextLine();


        int id = ++memberLastId;


        Member member = new Member(id,loginId,loginPw,name);
        memberList.add(member);

        System.out.printf("\"%s\"님 회원 가입을 환영합니다..\n",member.name);



    }
}
