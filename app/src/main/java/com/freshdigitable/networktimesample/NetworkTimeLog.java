package com.freshdigitable.networktimesample;

import io.realm.RealmObject;

/**
 * Created by akihit on 2016/04/10.
 */
public class NetworkTimeLog extends RealmObject {
  private long time;
  private String event;
  private String memo;

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }
}
