package com.sis.exam.board.vo;

public class Article {
   private int id ;
   private String title ;



    private String content ;



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

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }
}
