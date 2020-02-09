package com.magic.elana.data.database;

import com.magic.elana.data.Comment;
import com.magic.elana.data.Post;

import java.util.List;

public interface DataBase {
    void addPost(Post post);
    void addComment(String postId, Comment comment);
    List<Post> readFirstPage(int pageSize);
    List<Post> readNext(int pageSize);
}
