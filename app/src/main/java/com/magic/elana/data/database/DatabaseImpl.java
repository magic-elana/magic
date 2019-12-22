package com.magic.elana.data.database;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.magic.elana.data.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseImpl implements DataBase {
    private static final String TAG = "Database: ";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void addPost(Post post) {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put(Post.TITLE_KEY, post.title());
        user.put(Post.CONTENT_KEY, post.content());

// Add a new document with a generated ID
        db.collection("posts")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    @Override
    public List<Post> readFirstPage(int pageSize) {
        return null;
    }

    @Override
    public List<Post> readNext(int pageSize) {
        return null;
    }
}
