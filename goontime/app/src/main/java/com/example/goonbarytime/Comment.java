package com.example.goonbarytime;

public class Comment {

    private String doucumentId;
    private String publisher;
    private String comment;

    public Comment(String doucumentId, String publisher, String comment) {
        this.doucumentId = doucumentId;
        this.publisher = publisher;
        this.comment = comment;
    }

    public Comment(String publisher, String comment) {
        this.publisher = publisher;
        this.comment = comment;
    }

    public Comment() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDoucumentId() {
        return doucumentId;
    }

    public String getContent() {
        return comment;
    }

    public String getNickname() {
        return publisher;
    }
}
