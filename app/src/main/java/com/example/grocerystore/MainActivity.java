package com.example.grocerystore;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.grocerystore.res.ClickSound;

public class MainActivity extends AppCompatActivity {
    private Button signInBtn;
    private Button signUpBtn;
    private TextView skipTxtV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Hide the default action bar
        setContentView(R.layout.activity_main);

        // Initialize ClickSound with the context of this activity
        ClickSound.init(this);

        signInBtn = findViewById(R.id.signInBtn);
        signUpBtn = findViewById(R.id.signUpBtn);

        signInBtn.setOnClickListener(v -> ClickSound.playSound());

        signUpBtn.setOnClickListener(v -> ClickSound.playSound());

        skipTxtV = findViewById(R.id.skipTxtV);
        skipTxtV.setOnClickListener(view -> {
            ClickSound.playSound();
            Intent intent = new Intent(MainActivity.this, ProductsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClickSound.releaseResources();
    }
}