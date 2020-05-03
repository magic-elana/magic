package com.magic.elana.data.database.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PostDao {
    @Insert
    void insert(Post... posts);

    @Update
    void update(Post... posts);

    @Delete
    void delete(Post... posts);

    @Query("SELECT * from posts ORDER BY timeStamp DESC")
    LiveData<List<Post>> getMostRecentPosts();

    @Query("DELETE FROM posts")
    void deleteAll();
}
