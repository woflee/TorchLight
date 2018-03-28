package com.gsnathan.torchlight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.franmontiel.attributionpresenter.AttributionPresenter;
import com.franmontiel.attributionpresenter.entities.Attribution;
import com.franmontiel.attributionpresenter.entities.License;

/**
 * Created by Gokul Swaminathan on 3/26/2018.
 */

public class AboutActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    public boolean useDarkTheme;

    TextView versionView;   //shows the version
    private final String APP_VERSION_RELEASE = "Version " + Utils.getAppVersion();   //contains Version + the version number
    private final String APP_VERSION_DEBUG = "Version " + Utils.getAppVersion() + "-debug";   //contains Version + the version number + debug

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Use the chosen theme
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if (useDarkTheme) {
            setTheme(R.style.DarkTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initUI();
    }

    private void initUI() {
        //initialize the textview
        versionView = (TextView) findViewById(R.id.text_version);

        // check if app is debug
        if (BuildConfig.DEBUG) {
            versionView.setText(APP_VERSION_DEBUG);
        } else    //if app is release
        {
            versionView.setText(APP_VERSION_RELEASE);
        }

        Switch toggle = (Switch) findViewById(R.id.theme_switch);
        toggle.setChecked(useDarkTheme);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                toggleTheme(isChecked);
            }
        });
    }

    public void replayIntro(View v) {
        //navigate to intro class (replay the intro)
        startActivity(Utils.navIntent(this, MainIntroActivity.class));
    }

    public void showLibraries(View v) {

        //Library builder
        //Library title, copyright notice, license type, website url

        AttributionPresenter attributionPresenter = new AttributionPresenter.Builder(this)
                .addAttributions(
                        new Attribution.Builder("AttributionPresenter")
                                .addCopyrightNotice("Copyright 2017 Francisco José Montiel Navarro")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/franmontiel/AttributionPresenter")
                                .build()
                )

                .addAttributions(
                        new Attribution.Builder("material-intro")
                                .addCopyrightNotice("Copyright 2017 Jan Heinrich Reimer")
                                .addLicense(License.MIT)
                                .setWebsite("https://github.com/heinrichreimer/material-intro")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("Android Open Source Project")
                                .addCopyrightNotice("Copyright 2016 The Android Open Source Project")
                                .addLicense(License.APACHE)
                                .setWebsite("http://developer.android.com/tools/support-library/index.html")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("Android Support Libraries")
                                .addCopyrightNotice("Copyright 2016 The Android Open Source Project")
                                .addLicense(License.APACHE)
                                .setWebsite("http://developer.android.com/tools/support-library/index.html")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("CardView")
                                .addCopyrightNotice("Copyright 2016 The Android Open Source Project")
                                .addLicense(License.APACHE)
                                .setWebsite("https://developer.android.com/reference/android/support/v7/widget/CardView.html")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("Material Ripple Layout")
                                .addCopyrightNotice("Copyright 2015 Balys Valentukevicius")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/balysv/material-ripple")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("SwitchButton")
                                .addCopyrightNotice("Copyright 2016 https://github.com/zcweng/SwitchButton")
                                .addLicense(License.MIT)
                                .setWebsite("https://github.com/zcweng/SwitchButton")
                                .build()
                )
                .build();

        //show license dialogue
        attributionPresenter.showDialog("Open Source Libraries");
    }

    public void emailDev(View v) {
        startActivity(Utils.emailIntent("gokulswaminathan@outlook.com", "Android-Scouter", APP_VERSION_RELEASE, "Send email..."));
    }

    public void navToGit(View v) {
        startActivity(Utils.webIntent("https://github.com/JavaCafe01"));
    }

    public void navToSourceCode(View v) {
        startActivity(Utils.webIntent("https://github.com/JavaCafe01/TorchLight"));
    }

    private void toggleTheme(boolean darkTheme) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_DARK_THEME, darkTheme);
        editor.apply();

        Intent intent = getIntent();
        finish();

        startActivity(intent);
    }
}