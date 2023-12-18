package com.example.localdatabasecheckbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.localdatabasecheckbox.databinding.ActivityMainBinding;
import com.example.localdatabasecheckbox.model.Question;
import com.example.localdatabasecheckbox.model.QuestionList;
import com.example.localdatabasecheckbox.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewModel viewModel;

    ActivityMainBinding binding;

    List<Question> questionList;

    static int result=0;
    static  int totalQuestions=0;

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        result = 0;
        totalQuestions = 0;

        DisplayFirstQuestion();
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShownextQuestion();
            }

        });
    }

        private void DisplayFirstQuestion(){
            viewModel.getLiveData().observe(this, new Observer<QuestionList>() {
                @Override
                public void onChanged(QuestionList questions) {
                    questionList=questions;

                    binding.txtQuestion.setText("Question1:"+questions.get(0).getQuestion());
                    binding.checkbox1.setText(questions.get(0).getOption1());
                    binding.checkbox2.setText(questions.get(0).getOption2());
                    binding.checkbox3.setText(questions.get(0).getOption3());
                    binding.checkbox4.setText(questions.get(0).getOption4());
                }
            });
        }
        private void ShownextQuestion(){
            if(binding.btnNext.getText().toString().equals("Done")){
                Intent intent=new Intent(MainActivity.this, Result.class);
                startActivity(intent);
                finish();
            }
            int selectedOptionsCount = getSelectedOptionsCount();
            if ( selectedOptionsCount>1) {
                List<CheckBox> selectedCheckboxes = getSelectedCheckboxes();
                if (questionList.size() - 1 > 0) {
                    totalQuestions = questionList.size();

                    String correctOption1 = questionList.get(i).getCorrectOption1();
                    String correctOption2 = questionList.get(i).getCorrectOption2();
                    if (areOptionsCorrect(selectedCheckboxes, correctOption1, correctOption2)) {
                        result++;

                    }
                }
                if (i == 0) {
                    i++;
                }

                displayQuestion();
                if (i == (questionList.size() - 1)) {
                    binding.btnNext.setText("Done");
                }
                clearAllCheckboxes();
                i++;
            } else {
                Toast.makeText(this, "Please select exactly two answers", Toast.LENGTH_SHORT).show();
            }
        }
    private void displayQuestion() {
        Question currentQuiz = questionList.get(i);
        binding.txtQuestion.setText(String.format("Question" + ((i + 1)) + ":" + currentQuiz.getQuestion()));
        binding.checkbox1.setText(currentQuiz.getOption1());
        binding.checkbox2.setText(currentQuiz.getOption2());
        binding.checkbox3.setText(currentQuiz.getOption3());
        binding.checkbox4.setText(currentQuiz.getOption4());
    }
    private int getSelectedOptionsCount() {
        int count = 0;
        if (binding.checkbox1.isChecked()) {
            count++;
        }
        if (binding.checkbox2.isChecked()) {
            count++;
        }
        if (binding.checkbox3.isChecked()) {
            count++;
        }
        if (binding.checkbox4.isChecked()) {
            count++;
        }
        return count;
    }
    private List<CheckBox> getSelectedCheckboxes() {
        List<CheckBox> selectedCheckboxes = new ArrayList<>();
        if (binding.checkbox1.isChecked()) {
            selectedCheckboxes.add(binding.checkbox1);
        }
        if (binding.checkbox2.isChecked()) {
            selectedCheckboxes.add(binding.checkbox2);
        }
        if (binding.checkbox3.isChecked()) {
            selectedCheckboxes.add(binding.checkbox3);
        }
        if (binding.checkbox4.isChecked()) {
            selectedCheckboxes.add(binding.checkbox4);
        }
        return selectedCheckboxes;
    }
    private void clearAllCheckboxes() {
        binding.checkbox1.setChecked(false);
        binding.checkbox2.setChecked(false);
        binding.checkbox3.setChecked(false);
        binding.checkbox4.setChecked(false);
    }
    private boolean areOptionsCorrect(List<CheckBox> selectedCheckboxes, String correctOption1, String correctOption2) {
        if (selectedCheckboxes.size() != 2) {
            return false;
        }
        String selectedOption1 = selectedCheckboxes.get(0).getText().toString();
        String selectedOption2 = selectedCheckboxes.get(1).getText().toString();
        return (correctOption1.equals(selectedOption1) && correctOption2.equals(selectedOption2)) ||
                (correctOption1.equals(selectedOption2) && correctOption2.equals(selectedOption1));
    }
}




