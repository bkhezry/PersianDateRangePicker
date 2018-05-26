package com.bkhezry.persiandaterangepickerexample;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.bkhezry.persiandaterangepicker.date.PersianDatePickerDialog;
import com.github.bkhezry.persiandaterangepicker.persiandateutils.LanguageUtils;
import com.github.bkhezry.persiandaterangepicker.persiandateutils.PersianDate;
import com.github.bkhezry.persiandaterangepicker.time.PersianRadialPickerLayout;
import com.github.bkhezry.persiandaterangepicker.time.PersianTimePickerDialog;
import com.github.bkhezry.persiandaterangepickerexample.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements
  PersianDatePickerDialog.OnDateSetListener, PersianTimePickerDialog.OnTimeSetListener {
  private TextView fromDate;
  private TextView toDate;
  private TextView fromTime;
  private TextView toTime;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    final String fontName = "DroidNaskh-Regular";
    // Find our View instances
    fromDate = findViewById(R.id.from_date);
    toDate = findViewById(R.id.to_date);
    fromTime = findViewById(R.id.from_time);
    toTime = findViewById(R.id.to_time);
    Button dateButton = findViewById(R.id.date_button);
    Button timeButton = findViewById(R.id.time_button);

    dateButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        PersianDate now = new PersianDate();
        PersianDatePickerDialog dpd = PersianDatePickerDialog.newInstance(
          MainActivity.this,
          now.getPersianYear(),
          now.getPersianMonth(),
          now.getPersianDay()
        );
        dpd.setTypeface(fontName);
        dpd.show(getFragmentManager(), "PersianDatePickerDialog");
      }
    });

    timeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        PersianDate now = new PersianDate();
        PersianTimePickerDialog tpd = PersianTimePickerDialog.newInstance(
          MainActivity.this,
          now.get(PersianDate.HOUR_OF_DAY),
          now.get(PersianDate.MINUTE),
          true
        );
        tpd.setTypeface(fontName);
        tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
          @Override
          public void onCancel(DialogInterface dialogInterface) {
            Log.d("TimePicker", "Dialog was cancelled");
          }
        });
        tpd.show(getFragmentManager(), "PersianTimePickerDialog");
      }
    });
  }

  @Override
  public void onResume() {
    super.onResume();
    PersianDatePickerDialog dpd = (PersianDatePickerDialog) getFragmentManager().findFragmentByTag("PersianDatePickerDialog");
    if (dpd != null) {
      dpd.setOnDateSetListener(this);
    }
  }

  @Override
  public void onDateSet(PersianDatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
    String monthString = String.valueOf(monthOfYear + 1);
    if (monthOfYear < 9) {
      monthString = "0" + monthString;
    }
    String dayString = String.valueOf(dayOfMonth);
    if (dayOfMonth < 10) {
      dayString = "0" + dayString;
    }
    String monthStringEnd = String.valueOf(monthOfYearEnd + 1);
    if (monthOfYearEnd < 9) {
      monthStringEnd = "0" + monthStringEnd;
    }
    String dayStringEnd = String.valueOf(dayOfMonthEnd);
    if (dayOfMonthEnd < 10) {
      dayStringEnd = "0" + dayStringEnd;
    }
    fromDate.setText(String.format("%s/%s/%s", LanguageUtils.getPersianNumbers(String.valueOf(year)), LanguageUtils.getPersianNumbers(monthString), LanguageUtils.getPersianNumbers(dayString)));
    toDate.setText(String.format("%s/%s/%s", LanguageUtils.getPersianNumbers(String.valueOf(yearEnd)), LanguageUtils.getPersianNumbers(monthStringEnd), LanguageUtils.getPersianNumbers(dayStringEnd)));
  }


  @Override
  public void onTimeSet(PersianRadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
    String hourString = String.valueOf(hourOfDay);
    if (hourOfDay < 10) {
      hourString = "0" + hourString;
    }
    String minuteString = String.valueOf(minute);
    if (minute < 10) {
      minuteString = "0" + minuteString;
    }
    String hourStringEnd = String.valueOf(hourOfDayEnd);
    if (hourOfDayEnd < 10) {
      hourStringEnd = "0" + hourStringEnd;
    }
    String minuteStringEnd = String.valueOf(minuteEnd);
    if (minuteEnd < 10) {
      minuteStringEnd = "0" + minuteStringEnd;
    }
    fromTime.setText(String.format("%s:%s", LanguageUtils.getPersianNumbers(hourString), LanguageUtils.getPersianNumbers(minuteString)));
    toTime.setText(String.format("%s:%s", LanguageUtils.getPersianNumbers(hourStringEnd), LanguageUtils.getPersianNumbers(minuteStringEnd)));
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.item_about) {
      startActivity(new Intent(MainActivity.this, AboutActivity.class));
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
