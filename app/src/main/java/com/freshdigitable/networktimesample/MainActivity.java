package com.freshdigitable.networktimesample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freshdigitable.networktimesample.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//    setContentView(R.layout.activity_main);

    try {
      LogFile.create(this);
    } catch (IOException e) {
      finish();
      return;
    }

//    final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.logs);
    final RecyclerView recyclerView = binding.logs;
    if (recyclerView == null) {
      finish();
      return;
    }
    recyclerView.setHasFixedSize(true);
    final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setAutoMeasureEnabled(true);
    recyclerView.setLayoutManager(layoutManager);

    final LogAdapter adapter = new LogAdapter();
    try {
      adapter.addAll(LogFile.read(this));
    } catch (IOException e) {
      finish();
      return;
    }
    recyclerView.setAdapter(adapter);
  }

  private static class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder> {
    private List<String> logs = new ArrayList<>();

    @Override
    public LogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new ViewHolder(new TextView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(LogAdapter.ViewHolder holder, int position) {
      ((TextView) holder.itemView).setText(logs.get(position));
    }

    @Override
    public int getItemCount() {
      return logs.size();
    }

    public void addAll(List<String> log) {
      logs.addAll(log);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

      public ViewHolder(View itemView) {
        super(itemView);
      }
    }
  }
}
