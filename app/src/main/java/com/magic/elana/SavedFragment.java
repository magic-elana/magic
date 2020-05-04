package com.magic.elana;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.magic.elana.data.Post;

import java.util.ArrayList;
import java.util.List;

public class SavedFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        loads the layout
        return inflater.inflate(R.layout.saved_fragment, container, false);

    }

    @Override
//    on create view creates view onviewcreated uses the view
//    creates 2 posts in here
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Post> postList = new ArrayList<>();
        postList.add(Post.builder().title("Hi").content("content").build());
        postList.add(Post.builder().title("title").content("another post").build());
//        has to have recycler view in homefragment.xml wish id.rvContacts in it
        RecyclerView viewcreated = view.findViewById(R.id.rvContacts);
//        constructor PostsAdapter used to create an instance of that class
//        adapter knows the data and how to render each item
//        set own post adapter to saved fragment
//        calling a constructor
//        created the instance
//        set post adapter to recycler view
//        reycler view has the post adapter, knows how to be rendered
        viewcreated.setAdapter(new PostsAdapter(postList));
//        linear layout manager: one item per row or column
//        grid layout manager: grid view
//        how the items are going to render relative to each other
        viewcreated.setLayoutManager(new LinearLayoutManager(view.getContext()));
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
        }
    }
    //each post has title, content
    public class PostsAdapter extends RecyclerView.Adapter<ViewHolder> {
//        member inside the class
//        similar to a variable, give it a type, name, value, has a descriptor "private"
//        public means whenever you have a post adapter you can say PostsAdapter.postList
//        this member is accessable to everything inside the class
        private List<Post> postList;

        // Pass in the contact array into the constructor
//    you need adapter for saved fragment
//        method name and return type is the same thing
//        method name and class thing are the same
//        put the input into private variables
//        taking the posts, and putting whatever the input into it's own post
//        special method, used to instantiate (creating a new thing) the class

        public PostsAdapter(List<Post> posts) {
            postList = posts;
        }

        @NonNull
        @Override
//        inflate each specific view holder for each specific view holder we are using layout home fragment items
//        each viewholder is being created from layout home fragment items
//        creates view holder inflates the view with specific layout we are using,
//        than create the view holder so that adapter knows how to create each view holder.
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
//            inflate makes the layout appear (magic)
//            draws layout on devices
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
//            getting the view
            View contactView = inflater.inflate(R.layout.saved_fragment_items, parent, false);

            // Return a new holder instance
//            inflating viewHolder
//            calling the constructor of viewHolder to pass in the contactView, creating viewHolder
//
            return new ViewHolder(contactView);
        }

        @Override
//        creates the view holder and bind the view holder
//        bind means putting data into view used in creating websites what user can see and a server that serves data to the UI
//        sending data to each view is called binding
//        process to put data into the view holder, specific position than we set data into each text view
//        create the view holder and onbind view holder look for specific item that is supposed to be with the view than
//        get the data from that view so the data shows up

        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            //        position: every time you want to add different data to a new view holder, helps get the right data
            Post post = postList.get(position);
//            title text view is holder title
            TextView titletextview = holder.title;
//            putting post title into the view
            titletextview.setText(post.title());
            TextView contenttextview = holder.content;
            contenttextview.setText(post.content());

        }
//how many items inside the list
//        returns an integer
        @Override
        public int getItemCount() {
            return postList.size();
        }
    }

}

//to important option enter
//comments are command slash

//new adapter, new view holder, configure the recycler view using new adapter, same on_view_adapter