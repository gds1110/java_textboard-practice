package com.sis.exam.board.controller;

import com.sis.exam.board.vo.Member;
import com.sis.exam.board.vo.Rq;
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
            memberLastId = memberList.get(memberList.size()-1).getId();
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


        int id = ++memberLastId;


        Member member = new Member(id,loginId,loginPw,name);
        memberList.add(member);

        System.out.printf("\"%s\"님 회원 가입을 환영합니다..\n",member.getName());



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

        Member member = getMemberLoginID(loginId);

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

    private Member getMemberLoginID(String loginId) {

        for(Member member : memberList)
        {
            if(member.getLoginId().equals(loginId))
            {
                return member;
            }
        }

        return null;
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
