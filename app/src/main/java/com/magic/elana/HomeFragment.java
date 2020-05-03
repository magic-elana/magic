package com.magic.elana;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.magic.elana.data.database.local.Post;
import com.magic.elana.data.database.local.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PostsAdapter postsAdapter = new PostsAdapter(getContext());
        RecyclerView recyclerView = getView().findViewById(R.id.content_list);
        recyclerView.setAdapter(postsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PostRepository postRepository = new PostRepository(getActivity().getApplication());
        LiveData<List<Post>> allPosts = postRepository.getAllPosts();
        final MutableLiveData<List<com.magic.elana.data.Post>> allLivePost = new MutableLiveData<>();
        allPosts.observe(getViewLifecycleOwner(),
                list -> {
                    List<Post> postList = allPosts.getValue();
                    List<com.magic.elana.data.Post> livePostList = new ArrayList<>();
                    for (Post post : postList) {
                        livePostList.add(com.magic.elana.data.Post.builder()
                                .title(post.title)
                        .content(post.content)
                        .timeStamp(post.timeStamp).build());
                    }
                    allLivePost.postValue(livePostList);
                });

        allLivePost.observe(getViewLifecycleOwner(),
                list -> postsAdapter.setPosts(list));

        Button button = getView().findViewById(R.id.clear);
        button.setOnClickListener(v -> postRepository.deleteAll());
    }

    static class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {
        static class PostViewHolder extends RecyclerView.ViewHolder {
            private final TextView titleView;
            private final TextView contentView;

            private PostViewHolder(View itemView) {
                super(itemView);
                titleView = itemView.findViewById(R.id.post_title);
                contentView = itemView.findViewById(R.id.post_content);
            }
        }

        private final LayoutInflater inflater;
        private List<com.magic.elana.data.Post> posts; // Cached copy of words

        PostsAdapter(Context context) { inflater = LayoutInflater.from(context); }

        @Override
        public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.post_item, parent, false);
            return new PostViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PostViewHolder holder, int position) {
            if (posts != null) {
                com.magic.elana.data.Post current = posts.get(position);
                holder.titleView.setText(current.title());
                holder.contentView.setText(current.content());
            } else {
                // Covers the case of data not being ready yet.
                holder.titleView.setText("No Post");
            }
        }

        void setPosts(List<com.magic.elana.data.Post> posts){
            this.posts = posts;
            notifyDataSetChanged();
        }

        // getItemCount() is called many times, and when it is first called,
        // mWords has not been updated (means initially, it's null, and we can't return null).
        @Override
        public int getItemCount() {
            if (posts != null)
                return posts.size();
            else return 0;
        }

    }

}
