package com.alevel.multiblog.entity;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "comments", uniqueConstraints =
@UniqueConstraint(columnNames = {"number", "post_id"}))
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private Integer number;

    @ManyToOne
    private User author;

    @ManyToOne
    private Post post;

    private String text;

    public Comment() {
    }

    public Comment(Integer number, User author, Post post, String text) {
        this.number = number;
        this.author = author;
        this.post = post;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(number, comment.number) &&
                Objects.equals(author, comment.author) &&
                Objects.equals(post, comment.post) &&
                Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, author, post, text);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", number=" + number +
                ", author=" + author +
                ", post=" + post +
                ", text='" + text + '\'' +
                '}';
    }
}
