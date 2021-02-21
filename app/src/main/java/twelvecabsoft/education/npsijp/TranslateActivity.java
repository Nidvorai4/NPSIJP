package twelvecabsoft.education.npsijp;

import androidx.appcompat.app.AppCompatActivity;

//для работы кликабельных слов
import android.os.Bundle;
//для работы кликабельных слов

import android.view.View;


public class TranslateActivity extends AppCompatActivity {
   /* private void initET() {
        String definition = "Clickable words in text view ".trim();
        TextView definitionView = (EditText ) findViewById(R.id.editTextTextMultiLine);
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
    private void initTV() {

        TextView definitionView = (TextView) findViewById(R.id.twTestTextCkick);
        String definition =definitionView.getText().toString().trim();

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
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_translation  );
       // initET();
    }

    public void btnOnCkick(View view) {
        /*EditText ET = new EditText(this);
        ET.setText("ХЕР");
        ET.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)) ;
        LinearLayout L= findViewById(R.id.LayV);
        L.addView(ET);*/

        RowrOwroW R = new RowrOwroW();
        R.Add(this);

    }
}