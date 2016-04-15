package com.freshdigitable.networktimesample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by akihit on 2016/04/06.
 */
public class NetworkSetTimeReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    final String action = intent.getAction();
    if ("android.intent.action.NETWORK_SET_TIME".equals(action)) {
      final long time = intent.getExtras().getLong("time");
      LogFile.store(context, "SET_TIME", Long.toString(time));
    }else if ("android.intent.action.NETWORK_SET_TIMEZONE".equals(action)) {
      final String zoneId = intent.getExtras().getString("time-zone");
      LogFile.store(context, "SET_TIMEZONE", zoneId);
    }
  }
}
