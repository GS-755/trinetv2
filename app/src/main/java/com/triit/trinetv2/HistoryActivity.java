package com.triit.trinetv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.triit.trinetv2.adapter.HistoryAdapter;
import com.triit.trinetv2.adapter.HistoryModule;
import com.triit.trinetv2.model.Data;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView rvHistory;
    private Button btnDelHistory;
    private ArrayList<Data> dataArrayList;
    private HistoryAdapter historyAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i = getIntent();
        int id = item.getItemId();
        switch(id) {
            case R.id.itemHome: {
                //TODO: Fix Intent feature
                Intent x = new Intent(this, MainActivity.class);
                startActivity(x);
                finish();
            } break;
            case R.id.itemVersionH: {
                Toast.makeText(this, i.
                        getStringExtra("App_Version"), Toast.LENGTH_SHORT).show();
            } break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void setComponents() {
        dataArrayList = HistoryModule.getDataList();
        historyAdapter = new HistoryAdapter(this, dataArrayList);
        rvHistory = findViewById(R.id.rvHistory);
        btnDelHistory = findViewById(R.id.btnDelHistory);
        LinearLayoutManager layout = new
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvHistory.setLayoutManager(layout);
        rvHistory.setAdapter(historyAdapter);
    }
    public void setActionDelHistory() {
        btnDelHistory.setOnClickListener(e -> {
            historyAdapter.clearHistory();
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setComponents();
        setActionDelHistory();
    }
}