package com.vanniktech.emoji.sample;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.ios.IosEmojiProvider;

import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_AUTO;

public class EmojiApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();

    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_AUTO);
    EmojiManager.install(new IosEmojiProvider());

  }
}
