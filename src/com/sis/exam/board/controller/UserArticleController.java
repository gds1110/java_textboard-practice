package com.sis.exam.board.controller;

import com.sis.exam.board.service.ArticleService;
import com.sis.exam.board.vo.Article;
import com.sis.exam.board.vo.Rq;
import com.sis.exam.board.utill.Util;
import com.sis.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public  class UserArticleController {

    private ArticleService articleService;
    private List<Article> articleList;


    public UserArticleController(){

        articleService = Container.getArticleService();

        articleList = articleService.getArticleList();

        articleService.addTestcase();

    }



    public void actionWrite(Rq rq) {
        System.out.println("==게시물 등록==");
        System.out.printf("제목 : ");
        String title = Container.getSc().nextLine();

        System.out.printf("내용 : ");
        String content =Container.getSc().nextLine();

        int id = articleService.write(title,content);

        Article article = new Article(id,title,content);
        articleList.add(article);

        System.out.println(id+"번 게시물이 등록되었습니다.\n");
        System.out.println("생성된 게시물 객체 : "+article);

    }

    public void showList(Rq rq) {

        System.out.println("== 게시물 리스트 ==");
        System.out.println("----------------");
        System.out.println("번호 / 제목");
        System.out.println("----------------");

        String searchKeyword = rq.getParam("searchKeyword","");

        List<Article> filteredArticles= articleService.getArticleList();

        if(searchKeyword.length()>0)
        {
            filteredArticles= new ArrayList<>();

            for(Article article : articleList)
            {
                System.out.println(articleList.size());
                System.out.println(article);
                boolean matched = article.getTitle().contains(searchKeyword)||article.getContent().contains(searchKeyword);

                if(matched)
                {
                    filteredArticles.add(article);
                }
            }
        }

        List<Article> sortedArticles = filteredArticles;

        String orderBy = rq.getParam("orderBy","idDesc");
        boolean orderByIdDesc = orderBy.equals("idDesc");


        if(orderByIdDesc)
        {
            sortedArticles = Util.reverseList(filteredArticles);
        }

        for(Article article : sortedArticles)
        {
            System.out.println(article);
        }
//                for(Article article: articleList)
//                {
//                    System.out.printf("%d / %s\n",article.id,article.title);
//                }
//
//                for(int i=articleList.size()-1;i>=0;i--)
//                {
//                    Article article =articleList.get(i);
//                    System.out.println(article);
//                }


    }

    public void showDetail(Rq rq) {
        Map<String,String> params = rq.getParams();
        if(params.containsKey("id")==false)
        {
            System.out.println("id를 입력해주세요.");
            return;
        }
        int id =0;
        try {
            id = Integer.parseInt(params.get("id"));
        }
        catch (NumberFormatException e){
            System.out.println("id를 정수 형태로 입력해주세요.");
            return;
        }
        if(articleList.isEmpty()||id>articleList.size())
        {
            System.out.println("게시물이 존재하지 않습니다");
            return;
        }
        Article article = articleService.getArticleById(id);

        if(article==null)
        {
            System.out.println("해당 게시물은 존재하지않습니다.");
            return;
        }

        System.out.println(article);
    }


    public void actionModify(Rq rq) {
        int id = rq.getIntParam("id",0);

        if(id==0)
        {
            System.out.println("아이디를 올바르게 입력해주세요.");

            return;
        }
        if(articleList.isEmpty()||id>articleList.size())
        {
            System.out.println("게시물이 존재하지 않습니다");
            return;
        }
        Article article = articleService.getArticleById(id);

        if(article==null)
        {
            System.out.println("해당 게시물은 존재하지않습니다.");
            return;
        }
        System.out.printf("새 제목 : ");
        article.setTitle(Container.getSc().nextLine());
        System.out.printf("새 내용 : ");
        article.setContent(Container.getSc().nextLine());
        System.out.println(article.getId()+"번 게시물이 수정되었습니다.");
    }

    public void actionDelete(Rq rq) {
     int id = rq.getIntParam("id",0);

     if(id==0)
     {
         System.out.println("아이디를 올바르게 입력해주세요.");

            return;
     }

        if(articleList.isEmpty()||id>articleList.size())
        {
            System.out.println("게시물이 존재하지 않습니다");
            return;
        }

        Article article = articleService.getArticleById(id);


        if(article==null)
        {
            System.out.println("해당 게시물은 존재하지않습니다.");
            return;
        }
        articleService.remove(article);
        System.out.printf(id+"번 게시물이 삭제되었습니다.");
    }
    private Article getArticleById(int id) {
        for(Article article:articleList)
        {
            if(article.getId()==id)
            {
                return article;
            }
        }

        return null;
    }
}
