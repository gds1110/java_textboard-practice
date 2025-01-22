package com.sis.exam.board.service;

import com.sis.exam.board.Repository.ArticleRepository;
import com.sis.exam.board.container.Container;
import com.sis.exam.board.vo.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {


    private ArticleRepository articleRepository;

    public ArticleService()
    {
        articleRepository = Container.getArticleRepository();

    }

    public void addTestcase() {
        for(int i=0;i<100;i++)
        {
            String title = "제목"+i;
            String content = "내용"+i;
            write(title,content);
            articleRepository.getArticleList().add(new Article(i,"제목"+i,"내용"+i));
        }
    }

    public int write(String title, String content) {
        return articleRepository.write(title,content);

    }

    public Article getArticleById(int id) {
        return  articleRepository.getArticleById(id);
    }

    public void remove(Article article) {

        articleRepository.remove(article);
    }

    public List<Article> getArticleList() {
        return  articleRepository.getArticleList();
    }
}
