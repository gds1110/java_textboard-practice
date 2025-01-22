package com.sis.exam.board.Repository;

import com.sis.exam.board.vo.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {



    int articleLastId ;
    List<Article> articleList ;


    public int getArticleLastId() {
        return articleLastId;
    }

    public void setArticleLastId(int articleLastId) {
        this.articleLastId = articleLastId;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }


    public ArticleRepository() {
        articleLastId =0;
        articleList = new ArrayList<>();
    }

    public int write(String title, String content) {
        int id = articleLastId+1;
        Article article = new Article(id,title,content);
        articleList.add(article);
        articleLastId = id;


        return id;
    }

    public Article getArticleById(int id) {
        for(Article article:articleList)
        {
            if(article.getId()==id)
            {
                return article;
            }
        }
        return  null;
    }

    public void remove(Article article) {
        articleList.remove(article);
    }

}
