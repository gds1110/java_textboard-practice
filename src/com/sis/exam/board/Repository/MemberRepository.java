package com.sis.exam.board.Repository;

import com.sis.exam.board.vo.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {


    int memberLastId ;
    List<Member> memberList ;

    public MemberRepository() {
        this.memberLastId =0;
        this.memberList = new ArrayList<>();
    }

    public int join(String loginId, String loginPw, String name) {
        int id = memberLastId+1;
        Member member = new Member(id,loginId,loginPw,name);
        memberList.add(member);
        memberLastId =id;

        return id;
    }

    public Member getMemberById(int id) {
        for(int i=0;i<memberList.size();i++)
        {
            if(memberList.get(i).getId()==id)
            {
                return memberList.get(i);
            }
        }
        return null;
    }

    public Member getMemberByLoginId(String loginId) {
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getLoginId().equals(loginId)) {
                return memberList.get(i);
            }
        }
        return null;
    }
}
