package twelvecabsoft.education.npsijp;


public class RowrOwroWText {
    private OneRow RowEng;
    private OneRow RowQu;
    private OneRow RowRu;
    //static int R1=1;


    public String getRowEng() {
        return RowEng.Lyric == null ? "" : RowEng.Lyric;
    }

    public String getRowQu() {
        return RowQu.Lyric == null ? "" : RowQu.Lyric;
    }

    public String getRowRu() {
        return RowRu.Lyric == null ? "" : RowRu.Lyric;
    }


    public void SetAnyRowText(String text, @Song.RowType int Type) {
        switch (Type) {
            case Song.RowType.ENG:
                setRowEng(text);
                break;
            case Song.RowType.QU:
                setRowQu(text);
                break;
            case Song.RowType.RU:
                setRowRu(text);
                break;
        }
    }

    public void setRowEng(String s) {
        RowEng.Lyric = s == null ? "" : s;
    }

    public void setRowQu(String s) {
        RowQu.Lyric = s == null ? "" : s;
    }

    public void setRowRu(String s) {
        RowRu.Lyric = s == null ? "" : s;
    }


    //private LinearLayout LL_EQR;
    //private WeakReference<TranslateActivity> wrActivity;
    /*private ClickableSpan getClickableSpan(final String word) {
        return new ClickableSpan() {
            String URL=null;
            final String mWord;
            {
                mWord = word;
            }

            @Override
            public void onClick(View widget) {
                Log.d("tapped on:", mWord);

                Toast.makeText(widget.getContext(), mWord, Toast.LENGTH_SHORT)
                        .show();
                TranslateActivity act = wrActivity.get();

                //myWebView.loadUrl("https://translate.google.ru/?hl=ru&sl=en&tl=ru&text=fuck%20you&op=translate");
                //myWebView.loadUrl("https://www.google.com");
                Dialog DialTr =new Dialog(act);
                DialTr.setContentView(R.layout.dial_translate);
                WebView myWebView = (WebView)  DialTr.findViewById(R.id.WebViewTR);
                WebSettings webSettings = myWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                URL="https://translate.google.ru/?hl=ru&sl=en&tl=ru&text=".concat(mWord).concat("&op=translate");
                myWebView.loadUrl(URL);
                //myWebView.loadUrl("https://translate.google.ru/?sl=en&tl=ru&op=translate&hl=ru");
                //myWebView.

                DialTr.show();
            }

            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
            }
        };
    }*/
    /*public void setClickable(OneRow OR) {
        //String definition = "Clickable words in text view ".trim();
        androidx.appcompat.widget.AppCompatEditText definitionView = OR;
        String definition = OR.getText().toString().trim();
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
    }*/
    public RowrOwroWText(String EngL) {
        RowEng = new OneRow(EngL);
        RowQu = new OneRow(null);
        RowRu = new OneRow(null);
    }

    public RowrOwroWText(String EngL, String QuL, String RuL) {
        RowEng = new OneRow(EngL);
        RowQu = new OneRow(QuL);
        RowRu = new OneRow(RuL);
    }
    /*public void Add(TranslateActivity activity) {

        wrActivity = new WeakReference<TranslateActivity>(activity);
        TranslateActivity act = wrActivity.get();
        LL_EQR=new LinearLayout(act);
        LL_EQR.setOrientation(LinearLayout.VERTICAL);

        RowEng= new OneRow(act);
        RowEng.setText(EngL);
        setClickable(RowEng);
        RowQuasiRu= new OneRow(act);
        RowQuasiRu.setText(QuL);
        RowRu= new OneRow(act);
        RowRu.setText(RuL);

        LL_EQR.addView(RowEng);
        LL_EQR.addView(RowQuasiRu);
        LL_EQR.addView(RowRu);

        ((LinearLayout)act.findViewById(R.id.LayV)).addView(LL_EQR);
        //act.setContentView(RowEng) ;
        //act.findViewById(R.layout.activity_main)
        // EditText myEditText = new EditText(context); // Pass it an Activity or Context
        //myEditText.setLayoutParams(new LayoutParams(..., ...)); // Pass two args; must be LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, or an integer pixel value.
        //myLayout.addView(myEditText);
    }*/
}
