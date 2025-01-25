package com.sis.exam.board.interceptor;

import com.sis.exam.board.vo.Rq;

public class NeedLoginInterceptor implements Interceptor{
    @Override
    public boolean run(Rq rq) {
        if(rq.isLogined())
        {
            return true;
        }

        switch (rq.getUrlPath())
        {
            case "/user/article/wrtie":
            case "/user/article/modify":
            case "/user/article/delete":
            case "/user/member/logout":
                System.out.println("로그인 후 이용해주세요.");
                return false;
        }
        return true ;
    }
}
