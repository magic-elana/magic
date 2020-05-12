package com.magic.elana.data.database.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class Post {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    public String title;

    public String content;

    public boolean synced;

    public long timeStamp;

    public boolean saved;

    public static Post getFromModel(com.magic.elana.data.Post post, boolean isNew,  boolean setSaved) {
        Post localPost = new Post();
        localPost.title = post.title();
        localPost.content = post.content();
        localPost.synced = false;
        localPost.timeStamp = post.timeStamp();
        localPost.saved = post.saved();
        if (!isNew) {
            localPost.uid = post.uid();
            localPost.saved = setSaved;
        }
        return localPost;
    }
}
