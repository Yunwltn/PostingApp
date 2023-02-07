package com.yunwltn98.postingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yunwltn98.postingapp.model.Posting;

public class AddActivity extends AppCompatActivity {

    EditText editTitle;
    EditText editBody;
    Button btnSave;
    public static final int SAVE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setTitle("포스팅 생성");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTitle = findViewById(R.id.editTitle);
        editBody = findViewById(R.id.editBody);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SavePosting();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menuOk) {
            SavePosting();
        }
        return super.onOptionsItemSelected(item);
    }

    // 포스팅 저장할 메소드 만들어주기
    void SavePosting() {
        String title = editTitle.getText().toString();
        String body = editBody.getText().toString();

        // 데이터 확인
        if (title.isEmpty() || body.isEmpty()) {
            Toast.makeText(AddActivity.this,"모두 입력해주세요",Toast.LENGTH_SHORT).show();
            return;
        }

        Posting posting = new Posting(title, body);

        Intent intent = new Intent();
        intent.putExtra("posting", posting);
        setResult(SAVE, intent);

        // 창 종료 메인으로 돌아가기
        Toast.makeText(AddActivity.this, "포스팅이 저장되었습니다", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}