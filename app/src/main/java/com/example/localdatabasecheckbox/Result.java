package com.example.localdatabasecheckbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.localdatabasecheckbox.databinding.ActivityResultBinding;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ActivityResultBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_result);
        binding.ans.setText("Your Score is"+ MainActivity.result + "/" + MainActivity.totalQuestions);

        binding.retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Result.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}