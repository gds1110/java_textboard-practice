package com.sis.exam.board.container;

import com.sis.exam.board.controller.UserArticleController;
import com.sis.exam.board.controller.UserMemberController;

import java.util.Scanner;

public class Container {
    public static Scanner sc;
    public static UserArticleController userArticleController;
    public static UserMemberController userMemberController;

    static
    {
      sc =   new Scanner(System.in);
      userArticleController = new UserArticleController();
      userMemberController = new UserMemberController();
    }
}
