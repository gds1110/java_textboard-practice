package com.sis.exam.board;


import com.sis.exam.board.container.Container;

import java.util.*;

public class Main {

    static int articleLastId ;
    static List<Article> articleList ;


    static{
        articleLastId =0;
        articleList = new ArrayList<>();
    }

    public static void main(String[] args) {

        Scanner sc = Container.sc;


        Article lastArticle = null;
        addTestcase();

        if(!articleList.isEmpty())
        {
            articleLastId = articleList.getLast().id;
            lastArticle = articleList.getLast();

        }

        System.out.println("== 자바 텍스트 게시판 ==");
        System.out.println("== 프로그램 시작 ==");

        while (true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);
            Map<String,String> params = rq.getParams();

            System.out.printf("입력 된 명령어 : %s\n", cmd);

            if (rq.getUrlPath().equals("/user/article/write"))
            {
                actionUserArticleWrite();
                articleLastId++;
            }
            else if (rq.getUrlPath().equals("/user/article/list"))
            {
                actionUserArticleList(rq);
            }
            else if (rq.getUrlPath().equals("/user/article/detail"))
            {
              actionUserArticleDetail(rq);
            }
            else if(rq.getUrlPath().equals("/user/article/modify"))
            {
                actionUserArticleModify(rq);
            }
            else if(rq.getUrlPath().equals("/user/article/delete"))
            {
                actionUserArticleDelete(rq);
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

    private static void actionUserArticleDelete(Rq rq) {
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

        Article foundArticle = null;

        for(Article article : articleList)
        {
            if(article.id == id)
            {
                foundArticle = article;
                break;
            }
        }
        if(foundArticle==null)
        {
            System.out.println("해당 게시물은 존재하지않습니다.");
            return;
        }
        articleList.remove(foundArticle);
        System.out.printf(id+"번 게시물이 삭제되었습니다.");
    }

    private static void actionUserArticleModify(Rq rq) {
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
        Article foundArticle = null;
        for(Article article : articleList)
        {
            if(article.id == id)
            {
                foundArticle = article;
                break;
            }
        }
        if(foundArticle==null)
        {
            System.out.println("해당 게시물은 존재하지않습니다.");
            return;
        }
        System.out.printf("새 제목 : ");
        foundArticle.title= Container.sc.nextLine();
        System.out.printf("새 내용 : ");
        foundArticle.content = Container.sc.nextLine();
        System.out.println(foundArticle.id+"번 게시물이 수정되었습니다.");
    }

    private static void actionUserArticleDetail(Rq rq) {
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
        Article foundArticle = null;

        for(Article article : articleList)
        {
            if(article.id == id)
            {
                foundArticle = article;
                break;
            }
        }

        if(foundArticle==null)
        {
            System.out.println("해당 게시물은 존재하지않습니다.");
            return;
        }

        System.out.println(foundArticle);

    }

    private static void actionUserArticleWrite() {
        System.out.println("==게시물 등록==");
        System.out.printf("제목 : ");
        String title = Container.sc.nextLine();

        System.out.printf("내용 : ");
        String content =Container.sc.nextLine();

        int id = articleLastId+1;
        articleLastId++;

        Article article = new Article(id,title,content);
        articleList.add(article);

        System.out.println(id+"번 게시물이 등록되었습니다.\n");
        System.out.println("생성된 게시물 객체 : "+article);


    }

    private static void actionUserArticleList(Rq rq) {

        System.out.println("== 게시물 리스트 ==");
        System.out.println("----------------");
        System.out.println("번호 / 제목");
        System.out.println("----------------");

        Map<String,String> params = rq.getParams();

        List<Article> filteredArticles= articleList;

        String searchKeywordIndex = "searchKeyword";
        if(params.containsKey(searchKeywordIndex))
        {
            String searchKeyword = params.get(searchKeywordIndex);
            filteredArticles= new ArrayList<>();

            for(Article article : articleList)
            {
                System.out.println(articleList.size());
                System.out.println(article);
                boolean matched = article.title.contains(searchKeyword)||article.content.contains(searchKeyword);

                if(matched)
                {
                    filteredArticles.add(article);
                }
            }
        }

        List<Article> sortedArticles = filteredArticles;

        boolean orderByIdDesc = true;

        if(params.containsKey("orderBy")&&params.get("orderBy").equals("idAsc"))
        {
            orderByIdDesc =false;
            //Collections.reverse(articleList);
        }
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

    private static void addTestcase() {

        for(int i=0;i<100;i++)
        {
            articleList.add(new Article(i,"제목"+i,"내용"+i));
        }

    }
}
