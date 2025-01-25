package com.sis.exam.board.service;

import com.sis.exam.board.Repository.MemberRepository;
import com.sis.exam.board.container.Container;
import com.sis.exam.board.vo.Member;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService() {
        this.memberRepository = Container.getMemberRepository();
    }

    public void MakeTestData() {

        for(int i=1;i<3;i++)
        {
            String loginId = "user"+i;
            String loginPw = "user"+i;
            String name = "홍길동"+i;
            join(loginId,loginPw,name);
        }
    }

    public int join(String loginId, String loginPw,String name) {

        return memberRepository.join(loginId,loginPw,name);
    }

    public Member getMemberById(int id) {

        return  memberRepository.getMemberById(id);
    }

    public Member getMemberLoginId(String loginId) {
        return memberRepository.getMemberByLoginId(loginId);
    }
}
