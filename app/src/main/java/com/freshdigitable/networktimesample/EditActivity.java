package com.freshdigitable.networktimesample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;

/**
 * Created by akihit on 2016/04/08.
 */
public class EditActivity extends AppCompatActivity {

  private EditText edit;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit);

    try {
      final List<String> logs = LogFile.read(this);
      final String replaced = logs.get(0).replaceAll("¥n", "\n");
      edit = (EditText) findViewById(R.id.editText);
      edit.setText(replaced);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void onPause() {
    LogFile.delete(this);
    try {
      LogFile.create(this);
      LogFile.store(this, edit.getText().toString());
    } catch (IOException e) {
      e.printStackTrace();
    }

    super.onPause();
  }
}
