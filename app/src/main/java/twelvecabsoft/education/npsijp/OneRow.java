package twelvecabsoft.education.npsijp;

import android.content.Context;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.accessibility.AccessibilityViewCommand;

import java.text.BreakIterator;
import java.util.Locale;

//import com.google.android.material.api api.translate.Language;
//import com.google.api.translate.Translate;

public class OneRow {//extends androidx.appcompat.widget.AppCompatEditText {
    public String Lyric;
    public OneRow(String RowText){if(RowText!=null) Lyric=RowText; else Lyric="";}

/*    public OneRow(Context context,String L) {

        super(context);
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)) ;
        
    }*/
}
