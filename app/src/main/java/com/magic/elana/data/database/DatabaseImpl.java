package com.magic.elana.data.database;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.magic.elana.data.Comment;
import com.magic.elana.data.Post;
import com.magic.elana.data.database.local.LocalDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DatabaseImpl implements DataBase {
    private static final String TAG = "Database";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    LocalDatabase localDatabase;

    public DatabaseImpl(Context context) {
        localDatabase = LocalDatabase.getDatabase(context);
    }

    @Override
    public void addPost(final Post post) {
        Log.d(TAG, post.title() + " " + post.content());

        Map<String, Object> user = new HashMap<>();
        user.put(Post.TITLE_KEY, post.title());
        user.put(Post.CONTENT_KEY, post.content());
        localDatabase.getPostDao().insert(
                com.magic.elana.data.database.local.Post.getFromModel(post, true, false));

        // Add a new document with a generated ID
        db.collection("posts")
                .add(user)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(Task<DocumentReference> task) {
                        Log.d(TAG, "Added post with id: " + task.getResult().getId() + ". ");
                        if (task.getResult() != null) {
                            CollectionReference repliesReference =
                                    db.collection("posts")
                                            .document(task.getResult().getId())
                                            .collection(Post.REPLY_KEY);
                        }
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
    public void addComment(String postId, Comment comment) {

    }

    @Override
    public List<Post> readFirstPage(int pageSize) {
        db.collection("posts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                Log.d(TAG, "Query Posts complete.");
                Iterator<QueryDocumentSnapshot> iterator = task.getResult().iterator();
                while (iterator.hasNext()) {
                    QueryDocumentSnapshot snapshot = iterator.next();
                    Map<String, Object> stringObjectMap = snapshot.getData();
                    Log.d(TAG, Post.TITLE_KEY + ":" + stringObjectMap.get(Post.TITLE_KEY).toString());
                    Log.d(TAG, Post.CONTENT_KEY + ":" + stringObjectMap.get(Post.CONTENT_KEY).toString());
                    db.collection("posts")
                            .document(snapshot.getId())
                            .collection(Post.REPLY_KEY)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(Task<QuerySnapshot> task) {
                                    Log.d(TAG, "Query replies complete.");
                                    Iterator<QueryDocumentSnapshot> replyIterator = task.getResult().iterator();
                                    while (replyIterator.hasNext()) {
                                        Map<String, Object> stringObjectMap = replyIterator.next().getData();
                                        Log.d(TAG, stringObjectMap.get(Comment.CONTENT_KEY).toString());
                                    }

                                }
                            });
                }
            }
        });
        return null;
    }

    @Override
    public List<Post> readNext(int pageSize) {
        return null;
    }
}
