package com.magic.elana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // three things:
        // Edit text, Text view, and Button
        Button button = findViewById(R.id.button);
        final EditText write = findViewById(R.id.write);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView text = findViewById(R.id.text);
                        String wrttenText = write.getText().toString();
                        text.setText(wrttenText);
                    }
                }
        );
    }
}
