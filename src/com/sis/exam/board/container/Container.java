package com.sis.exam.board.container;

import com.sis.exam.board.Repository.ArticleRepository;
import com.sis.exam.board.service.ArticleService;
import com.sis.exam.board.session.Session;
import com.sis.exam.board.controller.UserArticleController;
import com.sis.exam.board.controller.UserMemberController;

import java.util.Scanner;

public class Container {
    public static Scanner sc;
    public static Session session;
    public static ArticleRepository articleRepository;
    public static ArticleService articleService;
    public static UserArticleController userArticleController;
    public static UserMemberController userMemberController;



    static
    {
      sc =   new Scanner(System.in);
      session = new Session();
      articleRepository = new ArticleRepository();
      articleService = new ArticleService();
      userArticleController = new UserArticleController();
      userMemberController = new UserMemberController();
    }

    public static Session getSession() {
        return session;
    }

    public static Scanner getSc() {
        return sc;
    }

    public static UserArticleController getUserArticleController() {
        return userArticleController;
    }

    public static UserMemberController getUserMemberController() {
        return userMemberController;
    }


    public static ArticleService getArticleService() {
        return articleService;
    }

    public static void setArticleService(ArticleService articleService) {
        Container.articleService = articleService;
    }

    public static ArticleRepository getArticleRepository() {
        return articleRepository;
    }

    public static void setArticleRepository(ArticleRepository articleRepository) {
        Container.articleRepository = articleRepository;
    }


}
