package com.sis.exam.board.interceptor;

import com.sis.exam.board.vo.Rq;

public class NeedLogoutInterceptor implements Interceptor{
    @Override
    public boolean run(Rq rq) {
        if(rq.isLogined()==false)
        {
            return true;
        }

        switch (rq.getUrlPath())
        {
            case "/user/member/login":
            case "/user/member/join":
            case "/user/member/findLoginId":
            case "/user/member/findLogout":
                System.out.println("이미 로그인 상태입니다.");
                return false;
        }
        return true ;
    }
}
