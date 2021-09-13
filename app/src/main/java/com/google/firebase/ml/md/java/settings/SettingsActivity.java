/**
 * Created by Anand kashyap on 19,July,2020
 */

package com.google.firebase.ml.md.java.settings;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.ml.md.R;

/** Hosts the preference fragment to configure settings. */
public class SettingsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_settings);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.settings_container, new SettingsFragment())
        .commit();
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }
}
