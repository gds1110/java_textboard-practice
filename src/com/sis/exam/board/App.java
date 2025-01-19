package com.sis.exam.board;

import com.sis.exam.board.container.Container;

import java.util.Map;
import java.util.Scanner;

public class App {

    public  void run() {

        Scanner sc = Container.sc;


        Article lastArticle = null;


        System.out.println("== 자바 텍스트 게시판 ==");
        System.out.println("== 프로그램 시작 ==");

        while (true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);

            System.out.printf("입력 된 명령어 : %s\n", cmd);

            if (rq.getUrlPath().equals("/user/article/write"))
            {
               Container.userArticleController.actionWrite(rq);
            }
            else if (rq.getUrlPath().equals("/user/article/list"))
            {

                Container.userArticleController.showList(rq);
            }
            else if (rq.getUrlPath().equals("/user/article/detail"))
            {
                Container.userArticleController.showDetail(rq);
            }
            else if(rq.getUrlPath().equals("/user/article/modify"))
            {

                Container.userArticleController.actionModify(rq);
            }
            else if(rq.getUrlPath().equals("/user/article/delete"))
            {

                Container.userArticleController.actionDelete(rq);
            }
            else if(rq.getUrlPath().equals("/user/member/join"))
            {

                Container.userMemberController.actionJoin(rq);
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


}
