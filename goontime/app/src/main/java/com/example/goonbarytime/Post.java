package com.example.goonbarytime;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Post {

    private String documentId;
    private String nickname;
    private String title;
    private String content;
    private String index;
    @ServerTimestamp
    private Date date;

    public Post(String nickname, String contents) {
    }

    public Post(String documentId, String nickname, String title, String contents) {
        this.documentId = documentId;
        this.nickname = nickname;
        this.title = title;
        this.content = contents;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public String getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Post{" +
                "documentId='" + documentId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }

}
