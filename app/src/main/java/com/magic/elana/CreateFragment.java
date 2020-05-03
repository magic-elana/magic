package com.magic.elana;

import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;

import com.magic.elana.data.database.local.Post;
import com.magic.elana.data.database.local.PostRepository;

import java.util.List;

public class CreateFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.create_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = getView().findViewById(R.id.button);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final EditText titleInputBox = getView().findViewById(R.id.write_title);
                        final EditText contentInputBox = getView().findViewById(R.id.write_content);

                        PostRepository postRepository = new PostRepository(getActivity().getApplication());
                        postRepository.insert(Post.getFromModel(com.magic.elana.data.Post.builder()
                                .title(titleInputBox.getText().toString())
                                .content(contentInputBox.getText().toString())
                                .timeStamp(SystemClock.currentThreadTimeMillis())
                                .build()));
                    }
                }
        );
    }

}
