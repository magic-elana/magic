package com.magic.elana;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.magic.elana.data.database.local.Post;
import com.magic.elana.data.database.local.PostRepository;

//    this is the createfragment class extended by fragment
// Fragment is the parent class and CreateFragment is one thing inside Fragment, like home and saved fragments
public class CreateFragment extends Fragment {

    //        this is the onCreateView method, type is View input is LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState
//       bundle is type , saved etc is name
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for create fragment
//        the layout file from the first parameter is inflated and returned as a View because attachToRoot is false
        return inflater.inflate(R.layout.create_fragment, container, false);
//end of onCreateView method
    }

    @Override
    //        this is the onViewCreated method, type is void input is @NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState
//        view and bundle are types, view an savedInstanceState are names
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
///        defining the variable button of type Button and find view by id and asign the value to button variable
        Button button = getView().findViewById(R.id.button);
//        defining the variable write of type EditText, to find view by id and asign the value to write variable
        final EditText write_title = getView().findViewById(R.id.write_title);
        final EditText write_content = getView().findViewById(R.id.write_content);
//            defining variable clickListener of type View.OnClickListener and uses the method OnClickListener and asigns it to clickListener
        View.OnClickListener clickListener = new View.OnClickListener() {
            //                this is the onClick method, type is void input is View v
            @Override
            public void onClick(View v) {
//                defining varible text of type textview and find view with id text and asign value to text variable
                TextView title = getView().findViewById(R.id.title);
                TextView content = getView().findViewById(R.id.content);
//                TextView name = getView().findViewById(R.id.name)
//                defining variable writtenText of type string and get the text of variable write and makes it a string asign information to writtenText variable
                String writtenTitle = write_title.getText().toString();
                String writtenContent = write_content.getText().toString();
//                variable text, function setText, variable writtenText
//                sets text in writtenText into text
                title.setText(writtenTitle);
                content.setText(writtenContent);
                title.setText(writtenTitle);
                content.setText(writtenContent);
                PostRepository postRepository = new PostRepository(getActivity().getApplication());
                postRepository.insert(Post.getFromModel(com.magic.elana.data.Post.builder()
                        .title(title.getText().toString())
                        .content(content.getText().toString())
                        .timeStamp(SystemClock.currentThreadTimeMillis())
                        .saved(false).build(), true, false));

            }
        };
//        variable button, function setOnClickListener, variable clickListener
//        starts the function sets on click listener when the button is pressed
        button.setOnClickListener(clickListener);

    }
    //ending createfragment class
}


//comments always go above the line