package com.vanniktech.emoji.sample;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.text.emoji.EmojiCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.vanniktech.emoji.EmojiEditText;
import com.vanniktech.emoji.EmojiPopup;

// We don't care about duplicated code in the sample.
@SuppressWarnings("CPD-START") public class MainActivity extends AppCompatActivity {
  static final String TAG = "MainActivity";

  ChatAdapter chatAdapter;
  EmojiPopup emojiPopup;

  EmojiEditText editText;
  ViewGroup rootView;
  ImageView emojiButton;
  EmojiCompat emojiCompat;

  @Override protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    chatAdapter = new ChatAdapter();

    editText = findViewById(R.id.main_activity_chat_bottom_message_edittext);
    rootView = findViewById(R.id.main_activity_root_view);
    emojiButton = findViewById(R.id.main_activity_emoji);
    final ImageView sendButton = findViewById(R.id.main_activity_send);

    emojiButton.setColorFilter(ContextCompat.getColor(this, R.color.emoji_icons),
        PorterDuff.Mode.SRC_IN);
    sendButton.setColorFilter(ContextCompat.getColor(this, R.color.emoji_icons),
        PorterDuff.Mode.SRC_IN);

    emojiButton.setOnClickListener(ignore -> emojiPopup.toggle());
    sendButton.setOnClickListener(ignore -> {
      final String text = editText.getText().toString().trim();

      if (text.length() > 0) {
        chatAdapter.add(text);

        editText.setText("");
      }
    });

    final RecyclerView recyclerView = findViewById(R.id.main_activity_recycler_view);
    recyclerView.setAdapter(chatAdapter);
    recyclerView.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    setUpEmojiPopup();
  }


  @Override public void onBackPressed() {
    if (emojiPopup != null && emojiPopup.isShowing()) {
      emojiPopup.dismiss();
    } else {
      super.onBackPressed();
    }
  }

  @Override protected void onStop() {
    if (emojiPopup != null) {
      emojiPopup.dismiss();
    }

    super.onStop();
  }

  private void setUpEmojiPopup() {
    emojiPopup = EmojiPopup.Builder.fromRootView(rootView)
        .setOnEmojiPopupShownListener(() -> emojiButton.setImageResource(R.drawable.ic_keyboard))
        .setOnEmojiPopupDismissListener(() -> emojiButton.setImageResource(R.drawable.emoji_ios_category_smileysandpeople))
        .build(editText);
  }
}
