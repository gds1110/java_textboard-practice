package com.sis.exam.board.container;

import com.sis.exam.board.Session;
import com.sis.exam.board.controller.UserArticleController;
import com.sis.exam.board.controller.UserMemberController;

import java.util.Scanner;

public class Container {
    public static Scanner sc;
    static Session session;
    public static UserArticleController userArticleController;
    public static UserMemberController userMemberController;

    static
    {
      sc =   new Scanner(System.in);
      session = new Session();
      userArticleController = new UserArticleController();
      userMemberController = new UserMemberController();
    }

    public static Session getSession() {
        return session;
    }
}
