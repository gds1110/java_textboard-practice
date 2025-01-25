package com.sis.exam.board.container;

import com.sis.exam.board.Repository.ArticleRepository;
import com.sis.exam.board.Repository.MemberRepository;
import com.sis.exam.board.interceptor.NeedLoginInterceptor;
import com.sis.exam.board.interceptor.NeedLogoutInterceptor;
import com.sis.exam.board.service.ArticleService;
import com.sis.exam.board.service.MemberService;
import com.sis.exam.board.session.Session;
import com.sis.exam.board.controller.UserArticleController;
import com.sis.exam.board.controller.UserMemberController;

import java.util.Scanner;

public class Container {
    private static Scanner sc;
    private static Session session;

    private static NeedLoginInterceptor needLoginInterceptor;
    private static NeedLogoutInterceptor needLogoutInterceptor;
    private static ArticleRepository articleRepository;
    private static ArticleService articleService;
    private static MemberRepository memberRepository;
    private static MemberService memberService;
    private static UserArticleController userArticleController;
    private static UserMemberController userMemberController;



    static
    {
      sc =   new Scanner(System.in);
      session = new Session();
        needLoginInterceptor = new NeedLoginInterceptor();
        needLogoutInterceptor = new NeedLogoutInterceptor();
      articleRepository = new ArticleRepository();
      articleService = new ArticleService();
      memberRepository = new MemberRepository();
      memberService = new MemberService();
      userArticleController = new UserArticleController();
      userMemberController = new UserMemberController();
    }

    public static Session getSession() {
        return session;
    }

    public static Scanner getSc() {
        return sc;
    }

    public static NeedLoginInterceptor getNeedLoginInterceptor() {
        return needLoginInterceptor;
    }

    public static void setNeedLoginInterceptor(NeedLoginInterceptor needLoginInterceptor) {
        Container.needLoginInterceptor = needLoginInterceptor;
    }

    public static NeedLogoutInterceptor getNeedLogoutInterceptor() {
        return needLogoutInterceptor;
    }

    public static void setNeedLogoutInterceptor(NeedLogoutInterceptor needLogoutInterceptor) {
        Container.needLogoutInterceptor = needLogoutInterceptor;
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


    public static MemberRepository getMemberRepository() {
        return memberRepository;
    }

    public static void setMemberRepository(MemberRepository memberRepository) {
        Container.memberRepository = memberRepository;
    }

    public static MemberService getMemberService() {
        return memberService;
    }

    public static void setMemberService(MemberService memberService) {
        Container.memberService = memberService;
    }

}
