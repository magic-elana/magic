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

import com.magic.elana.data.Post;
import com.magic.elana.data.database.local.PostRepository;

import java.util.ArrayList;
import java.util.List;

import static com.magic.elana.data.database.local.Post.getFromModel;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        loads the layout
        return inflater.inflate(R.layout.home_fragment, container, false);

    }

    @Override
//    on create view creates view onviewcreated uses the view
//    creates 2 posts in here
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Post> postList = new ArrayList<>();
//        has to have recycler view in homefragment.xml wish id.rvContacts in it
        RecyclerView viewcreated = view.findViewById(R.id.rvContacts);
//        constructor PostsAdapter used to create an instance of that class
//        adapter knows the data and how to render each item
//        set own post adapter to saved fragment
        PostsAdapter postsAdapter = new PostsAdapter(postList);
        viewcreated.setAdapter(postsAdapter);
//        linear layout manager: one item per row or column
//        grid layout manager: grid view
        viewcreated.setLayoutManager(new LinearLayoutManager(view.getContext()));
        PostRepository postRepository = new PostRepository(getActivity().getApplication());
        LiveData<List<com.magic.elana.data.database.local.Post>> allPosts = postRepository.getAllPosts();
        final MutableLiveData<List<Post>> allLivePost = new MutableLiveData<>();
        allPosts.observe(getViewLifecycleOwner(),
                list -> {
                    List<com.magic.elana.data.database.local.Post> posts = allPosts.getValue();
                    List<com.magic.elana.data.Post> livePostList = new ArrayList<>();
                    for (com.magic.elana.data.database.local.Post post : posts) {
                        livePostList.add(com.magic.elana.data.Post.builder()
                                .title(post.title)
                                .content(post.content)
                                .timeStamp(post.timeStamp)
                                .uid(post.uid)
                                .saved(post.saved).build());
                    }
                    allLivePost.postValue(livePostList);
                });

        allLivePost.observe(getViewLifecycleOwner(),
                list -> postsAdapter.setPosts(list));

        Button button = getView().findViewById(R.id.clear);
        button.setOnClickListener(v -> postRepository.deleteAll());

    }
// recycler view is broader definition, list view wish a list of times and view holder represents every item
//    view holder puts whats in view holder into each thin g in the list of recycler view
//    adapter has the content and can put it into view holder individually to put it into one of the things in the list of recycler view
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
//        other classes can access because public
        public TextView title;
        public TextView content;
        public Button button;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
//        this has no return type because it is a constructor same name as the class
// need this for saved fragment
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            button = (Button) itemView.findViewById(R.id.button);
        }
    }
//each post has title, content
    public class PostsAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<Post> postList;

        // Pass in the contact array into the constructor
//    you need adapter for saved fragment

        public PostsAdapter(List<Post> posts) {
            postList = posts;
        }

        void setPosts(List<com.magic.elana.data.Post> posts){
            this.postList = posts;
            notifyDataSetChanged();
        }


    @NonNull
        @Override
//        inflate each specific view holder for each specific view holder we are using layout home fragment items
//        each viewholder is being created from layout home fragment items
//        creates view holder inflates the view with specific layout we are using,
//        than create the view holder so that adapter knows how to create each view holder.
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.home_fragement_items, parent, false);

            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(contactView);
            return viewHolder;
        }

        @Override
//        creates the view holder and bind the view holder
//        bind means putting data into view used in creating websites what user can see and a server that serves data to the UI
//        sending data to each view is called binding
//        process to put data into the view holder, specific position than we set data into each text view
//        create the view holder and onbind view holder look for specific item that is supposed to be with the view than
//        get the data from that view so the data shows up
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Post post = postList.get(position);
            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PostRepository postRepository = new PostRepository(getActivity().getApplication());
                    postRepository.update(getFromModel(post, false, !post.saved()));
                }
            };
            // Set item views based on your views and data model
            TextView titletextview = holder.title;
            titletextview.setText(post.title());
            TextView contenttextview = holder.content;
            contenttextview.setText(post.content());
            if (post.saved()) {
                holder.button.setBackground(getContext().getDrawable(R.drawable.ic_turned_in_black_24dp));
            } else {
                holder.button.setBackground(getContext().getDrawable(R.drawable.ic_turned_in_not_black_24dp));
            }

            holder.button.setOnClickListener(clickListener);
        }

        @Override
//        how many items there are, makes sure only a specific amount of items are shown at a time
//        this tells how many times to repeat
//        this says put as many out as there are
        public int getItemCount() {
            return postList.size();
        }
    }

}
