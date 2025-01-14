package com.sis.exam.board.container;

import com.sis.exam.board.controller.UserArticleController;

import java.util.Scanner;

public class Container {
    public static Scanner sc;
    public static UserArticleController userArticleController;

    static
    {
      sc =   new Scanner(System.in);
      userArticleController = new UserArticleController();
    }
}
