package com.magic.elana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SavedFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.saved_fragment, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = getView().findViewById(R.id.button);
        final EditText write = getView().findViewById(R.id.write);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = getView().findViewById(R.id.text);
                String wrttenText = write.getText().toString();
                text.setText(wrttenText);
            }
        };
        button.setOnClickListener(clickListener);
    }
}

//to important option enter
//comments are command slash

//new adapter, new view holder, configure the recycler view using new adapter, same on_view_adapter