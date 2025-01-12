package com.sis.exam.board;


import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int articleLastId =0;

        Article lastArticle = null;
        List<Article> articleList =  new ArrayList<>();
        addTestcase(articleList);

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
                actionUserArticleWrite(sc,articleList,articleLastId);
                articleLastId++;
            }
            else if (rq.getUrlPath().equals("/user/article/list"))
            {
                actionUserArticleList(rq,articleList);
            }
            else if (rq.getUrlPath().equals("/user/article/detail"))
            {
              actionUserArticleDetail(rq,articleList);
            }
            else if(rq.getUrlPath().equals("/user/article/modify"))
            {
                actionUserArticleModify(sc,rq,articleList);
            }
            else if(rq.getUrlPath().equals("/user/article/delete"))
            {
                actionUserArticleDelete(sc,rq,articleList);
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

    private static void actionUserArticleDelete(Scanner sc, Rq rq, List<Article> articleList) {
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

    private static void actionUserArticleModify(Scanner sc, Rq rq, List<Article> articleList) {
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
        foundArticle.title= sc.nextLine();
        System.out.printf("새 내용 : ");
        foundArticle.content = sc.nextLine();
        System.out.println(foundArticle.id+"번 게시물이 수정되었습니다.");
    }

    private static void actionUserArticleDetail(Rq rq, List<Article> articleList) {
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

    private static void actionUserArticleWrite(Scanner sc, List<Article> articleList, int articleLastId) {
        System.out.println("==게시물 등록==");
        System.out.printf("제목 : ");
        String title = sc.nextLine();

        System.out.printf("내용 : ");
        String content =sc.nextLine();

        int id = articleLastId+1;
        articleLastId++;

        Article article = new Article(id,title,content);
        articleList.add(article);

        System.out.println(id+"번 게시물이 등록되었습니다.\n");
        System.out.println("생성된 게시물 객체 : "+article);


    }

    private static void actionUserArticleList(Rq rq, List<Article> articleList) {

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

    private static void addTestcase(List<Article> articleList) {

        for(int i=0;i<100;i++)
        {
            articleList.add(new Article(i,"제목"+i,"내용"+i));
        }

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


class Rq{
    String url;

    Map<String,String> params;
    String urlPath;

    public Rq(String url) {
        this.url = url;
        params = Util.getParamsFromUrl(url);
        urlPath = Util.getUrlPathFromUrl(url);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getUrlPath() {
        return urlPath;
    }
}


class Util{

    static Map<String,String> getParamsFromUrl(String url){
        System.out.println("getParamsFromUrl 실행됨");
        Map<String,String> params = new HashMap<>();

        String[] urlBits = url.split("\\?",2);

        if(urlBits.length==1)
        {
            return params;
        }


        String queryStr = urlBits[1];

        for(String bit : queryStr.split("&"))
        {
            String[] bits = bit.split("=",2);

            if(bits.length==1)
            {
                continue ;
            }
            System.out.println("bits[0] = "+bits[0]+" bits[1] = "+bits[1]);
            params.put(bits[0],bits[1]);
        }

        return params;
    }

    public static String getUrlPathFromUrl(String url) {
        System.out.println("getUrlPathFromUrl 실행됨");
        return url.split("\\?",2)[0];

    }

    public static<T> List<T> reverseList(List<T> list)
    {

        List<T> reverse = new ArrayList<T>(list.size());

        for(int i=list.size()-1;i>=0;i--)
        {
            reverse.add(list.get(i));
        }
        return reverse;
    }

}