package com.sis.exam.board;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("== 자바 텍스트 게시판 ==");
        System.out.println("== 프로그램 시작 ==");

        while (true)
        {
            System.out.printf("명령) ");
            String cmd = sc.nextLine();
            System.out.printf("입력 된 명령어 : %s\n", cmd);

            if(cmd.equals("exit"))
            {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
        }

        System.out.println("== 프로그램 종료 ==");
        sc.close(); // Scanner 사용했으면 필수.

    }
}