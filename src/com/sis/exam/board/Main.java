package com.sis.exam.board;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int articleLastId =0;

        System.out.println("== 자바 텍스트 게시판 ==");
        System.out.println("== 프로그램 시작 ==");

        while (true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine();
            System.out.printf("입력 된 명령어 : %s\n", cmd);

            if (cmd.equals("/user/article/write")) {
                System.out.println("==게시물 등록==");
                System.out.printf("제목 : ");
                String title = sc.nextLine();

                System.out.printf("내용 : ");
                String content =sc.nextLine();

                int id = articleLastId+1;
                articleLastId++;

                Article article = new Article(id,title,content);


                System.out.println(id+"번 게시물이 등록되었습니다.\n");
                System.out.println("생성된 게시물 객체 : "+article);
            }
            else if (cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            else {
                System.out.println("명령어를 잘못 입력하셨습니다.");
            }
        }

        System.out.println("== 프로그램 종료 ==");
        sc.close(); // Scanner 사용했으면 필수.

    }
}

class Article
{
    int id ;
    String title ;
    String content ;

    public Article(int id, String title, String content) {
        this.id =id;
        this.title=title;
        this.content=content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}