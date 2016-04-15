package com.freshdigitable.networktimesample;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by akihit on 2016/04/06.
 */
public class LogFile {

  public static final String SET_TIME_LOG = "set_time.log";

  static void store(Context context, String line) {
    final long now = System.currentTimeMillis();
    try {
      final FileOutputStream os = context.openFileOutput(SET_TIME_LOG, Context.MODE_APPEND);
      os.write((now + ", " + line + "\n").getBytes());
      os.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void store(Context context, String event, String memo) {
    store(context, event + ", " + memo);
    final long now = System.currentTimeMillis();
//    try {
//      final FileOutputStream os = context.openFileOutput(SET_TIME_LOG, Context.MODE_APPEND);
//      os.write((now + ", " + event + ", " + memo + "\n").getBytes());
//      os.close();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
    final RealmConfiguration realmConf = new RealmConfiguration.Builder(context).build();
    final Realm realm = Realm.getInstance(realmConf);
    realm.beginTransaction();
    final NetworkTimeLog ntl = realm.createObject(NetworkTimeLog.class);
    ntl.setTime(now);
    ntl.setEvent(event);
    ntl.setMemo(memo);
    realm.commitTransaction();
    realm.close();
  }


  public static List<String> read(Context context) throws IOException {
    FileInputStream fis = null;
    BufferedReader br = null;
    List<String> res = new ArrayList<>();
    try {
      fis = context.openFileInput(SET_TIME_LOG);
      br = new BufferedReader(new InputStreamReader(fis));
      String line;
      while ((line = br.readLine()) != null) {
        res.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fis != null) fis.close();
      if (br != null) {
        br.close();
      }
    }
    return res;
  }

  public static void create(Context context) throws IOException {
    File file = new File(context.getFilesDir(), SET_TIME_LOG);
    if (!file.exists()) {
      file.createNewFile();
    }
  }

  public static void delete(Context context) {
    File file = new File(context.getFilesDir(), SET_TIME_LOG);
    file.delete();
  }
}
