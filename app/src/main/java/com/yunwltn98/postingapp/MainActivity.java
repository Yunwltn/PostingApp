package com.yunwltn98.postingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yunwltn98.postingapp.Adapter.PostAdapter;
import com.yunwltn98.postingapp.model.Posting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    PostAdapter adapter;
    ArrayList<Posting> postingList = new ArrayList<>();
    final String URL = "https://block1-image-test.s3.ap-northeast-2.amazonaws.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("포스팅 리스트");

        progressBar = findViewById(R.id.progressBar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getApplicationContext(),new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        // 네트워크 통신해서 데이터 가져오기
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, URL + "/posting.json", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // 리스트 데이터 가져오기
                try {
                    JSONArray responseData = response.getJSONArray("data");
                    for ( int i = 0; i < responseData.length(); i++ ) {
                        JSONObject data = responseData.getJSONObject(i);

                        int userId = data.getInt("userId");
                        int id = data.getInt("id");
                        String title = data.getString("title");
                        String body = data.getString("body");

                        Posting posting = new Posting(userId, id, title, body);
                        postingList.add(posting);
                    }
                } catch (JSONException e) {
                    return;
                }

                adapter = new PostAdapter(MainActivity.this, postingList);
                recyclerView.setAdapter(adapter);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        queue.add(request);

    }
}