package com.magic.elana.data.database.local;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.NO_ACTION;

@Entity(foreignKeys = @ForeignKey(entity = Post.class,
        parentColumns = "uid",
        childColumns = "postId",
        onDelete = NO_ACTION),
        tableName = "comments")
public class Comment {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    public int postId;

    public String title;

    public String content;

}
