package com.magic.elana.data.database;

import com.magic.elana.data.Post;

import java.util.List;

public interface DataBase {
    void addPost(Post post);
    List<Post> readFirstPage(int pageSize);
    List<Post> readNext(int pageSize);
}
