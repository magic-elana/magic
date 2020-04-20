package com.magic.elana.data.database.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class Post {
    @PrimaryKey(autoGenerate = true)
    public Long uid;

    public String title;

    public String content;

    public boolean synced;

    public static Post getFromModel(com.magic.elana.data.Post post) {
        Post localPost = new Post();
        localPost.title = post.title();
        localPost.content = post.content();
        localPost.synced = false;
        return localPost;
    }
}
