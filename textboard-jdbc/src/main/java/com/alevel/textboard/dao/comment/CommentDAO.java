package com.alevel.textboard.dao.comment;

import com.alevel.textboard.dao.DAO;
import com.alevel.textboard.model.Comment;

import java.util.List;

public interface CommentDAO extends DAO<Long, Comment> {

    List<Comment> findByAuthorId(Long authorId);

    List<Comment> findByThreadId(Long threadId);

    List<Comment> findByThreadIdAndAuthorId(Long threadId, Long authorId);

    List<Comment> findByParentCommentId(Long parentCommentId);

}
