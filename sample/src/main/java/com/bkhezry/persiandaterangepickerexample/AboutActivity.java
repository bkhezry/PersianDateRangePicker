package com.bkhezry.persiandaterangepickerexample;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.bkhezry.persiandaterangepickerexample.R;

import androidx.annotation.IdRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AboutActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME |
      ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
    String versionName = "";
    try {
      versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    setTextWithLinks(R.id.text_application_info, getString(R.string.application_info_text, versionName));
    setTextWithLinks(R.id.text_developer_info, getString(R.string.developer_info_text));
    setTextWithLinks(R.id.text_libraries, getString(R.string.libraries_text));
    setTextWithLinks(R.id.text_license, getString(R.string.license_text));
  }

  private void setTextWithLinks(@IdRes int textViewResId, String htmlText) {
    AppUtils.setTextWithLinks((TextView) findViewById(textViewResId), AppUtils.fromHtml(htmlText));
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

}
