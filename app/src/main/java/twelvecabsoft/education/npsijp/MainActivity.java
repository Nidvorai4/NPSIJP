package twelvecabsoft.education.npsijp;

import androidx.appcompat.app.AppCompatActivity;

//для работы кликабельных слов
import android.os.Bundle;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import java.text.BreakIterator;
import java.util.Locale;
//для работы кликабельных слов

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/*
private void init() {
    String definition = "Clickable words in text view ".trim();
    TextView definitionView = (TextView) findViewById(R.id.text);
    definitionView.setMovementMethod(LinkMovementMethod.getInstance());
    definitionView.setText(definition, BufferType.SPANNABLE);
    Spannable spans = (Spannable) definitionView.getText();
    BreakIterator iterator = BreakIterator.getWordInstance(Locale.US);
    iterator.setText(definition);
    int start = iterator.first();
    for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator
            .next()) {
        String possibleWord = definition.substring(start, end);
        if (Character.isLetterOrDigit(possibleWord.charAt(0))) {
            ClickableSpan clickSpan = getClickableSpan(possibleWord);
            spans.setSpan(clickSpan, start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
}

private ClickableSpan getClickableSpan(final String word) {
    return new ClickableSpan() {
        final String mWord;
        {
            mWord = word;
        }

        @Override
        public void onClick(View widget) {
            Log.d("tapped on:", mWord);
            Toast.makeText(widget.getContext(), mWord, Toast.LENGTH_SHORT)
                    .show();
        }

        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
        }
    };
}
 */
public class MainActivity extends AppCompatActivity {
    private void init() {
        String definition = "Clickable words in text view ".trim();
        TextView definitionView = (TextView) findViewById(R.id.twTestTextCkick);
        definitionView.setMovementMethod(LinkMovementMethod.getInstance());
        definitionView.setText(definition, TextView.BufferType.SPANNABLE);
        Spannable spans = (Spannable) definitionView.getText();
        BreakIterator iterator = BreakIterator.getWordInstance(Locale.US);
        iterator.setText(definition);
        int start = iterator.first();
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator
                .next()) {
            String possibleWord = definition.substring(start, end);
            if (Character.isLetterOrDigit(possibleWord.charAt(0))) {
                ClickableSpan clickSpan = getClickableSpan(possibleWord);
                spans.setSpan(clickSpan, start, end,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    private ClickableSpan getClickableSpan(final String word) {
        return new ClickableSpan() {
            final String mWord;

            {
                mWord = word;
            }

            @Override
            public void onClick(View widget) {
                Log.d("tapped on:", mWord);
                Toast.makeText(widget.getContext(), mWord, Toast.LENGTH_SHORT)
                        .show();
            }

            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
            }
        };
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}