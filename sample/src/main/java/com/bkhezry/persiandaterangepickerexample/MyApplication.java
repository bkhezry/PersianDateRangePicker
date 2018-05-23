package com.bkhezry.persiandaterangepickerexample;

import android.app.Application;

import com.github.bkhezry.persiandaterangepickerexample.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
      .setDefaultFontPath("fonts/DroidNaskh-Regular.ttf")
      .setFontAttrId(R.attr.fontPath)
      .build()
    );
  }
}
