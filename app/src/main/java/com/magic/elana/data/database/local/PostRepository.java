package com.magic.elana.data.database.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PostRepository {
    private PostDao postDao;
    private LiveData<List<Post>> allPosts;

    public PostRepository(Application application) {
        LocalDatabase db = LocalDatabase.getDatabase(application);
        postDao = db.getPostDao();
        allPosts = postDao.getMostRecentPosts();
    }

    public LiveData<List<Post>> getAllPosts() {
        return allPosts;
    }

    public void insert(Post post) {
        LocalDatabase.databaseWriteExecutor.execute(() -> {
            postDao.insert(post);
        });
    }

    public void deleteAll() {
        LocalDatabase.databaseWriteExecutor.execute(() -> {
            postDao.deleteAll();
        });

    }
}
