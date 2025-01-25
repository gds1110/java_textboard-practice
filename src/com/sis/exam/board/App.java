package com.sis.exam.board;

import com.sis.exam.board.container.Container;
import com.sis.exam.board.interceptor.Interceptor;
import com.sis.exam.board.session.Session;
import com.sis.exam.board.vo.Article;
import com.sis.exam.board.vo.Member;
import com.sis.exam.board.vo.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public  void run() {

        Scanner sc = Container.getSc();


        Article lastArticle = null;


        System.out.println("== 자바 텍스트 게시판 ==");
        System.out.println("== 프로그램 시작 ==");

        while (true) {


            Rq rq = new Rq();

            String promptName = "명령) ";

            if(rq.isLogined())
            {
                Member loginedMember = rq.getLoginedMember();
                promptName = loginedMember.getLoginId();

            }



            System.out.printf("%s ",promptName);
            String cmd = sc.nextLine();

            rq.setCommand(cmd);

            if(runInterceptor(rq)==false)
            {
                continue;
            }

            System.out.printf("입력 된 명령어 : %s\n", cmd);

            if (rq.getUrlPath().equals("/user/article/write"))
            {
               Container.getUserArticleController().actionWrite(rq);
            }
            else if (rq.getUrlPath().equals("/user/article/list"))
            {

                Container.getUserArticleController().showList(rq);
            }
            else if (rq.getUrlPath().equals("/user/article/detail"))
            {
                Container.getUserArticleController().showDetail(rq);
            }
            else if(rq.getUrlPath().equals("/user/article/modify"))
            {

                Container.getUserArticleController().actionModify(rq);
            }
            else if(rq.getUrlPath().equals("/user/article/delete"))
            {

                Container.getUserArticleController().actionDelete(rq);
            }
            else if(rq.getUrlPath().equals("/user/member/join"))
            {

                Container.getUserMemberController().actionJoin(rq);
            }
            else if(rq.getUrlPath().equals("/user/member/login"))
            {

                Container.getUserMemberController().actionLogin(rq);
            }
            else if(rq.getUrlPath().equals("/user/member/logout"))
            {

                Container.getUserMemberController().actionLogout(rq);
            }
            else if (rq.getUrlPath().equals("exit"))
            {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            else
            {
                System.out.println("명령어를 잘못 입력하셨습니다.");
            }
        }

        System.out.println("== 프로그램 종료 ==");
        sc.close(); // Scanner 사용했으면 필수.

    }

    private boolean runInterceptor(Rq rq) {
        List<Interceptor> interceptors = new ArrayList<>();

        interceptors.add(Container.getNeedLoginInterceptor());
        interceptors.add(Container.getNeedLogoutInterceptor());

        for(Interceptor interceptor :interceptors)
        {
            if(interceptor.run(rq)==false)
            {
                return false;
            }
        }

        return true;

    }


}
