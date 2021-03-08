package twelvecabsoft.education.npsijp.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.Locale;

import twelvecabsoft.education.npsijp.R;
import twelvecabsoft.education.npsijp.Song;

public class RowsAdapter extends  RecyclerView.Adapter<RowsAdapter.RowsViewHolder>{
    Song song;
    private int ItemsCount;
    private int ItemsCurCount;
    private OnRItemListener onRItemListener;
    //private ItemTouchHelper itemTouchHelper;
    public RowsAdapter(Song songIn, OnRItemListener onRItemListenerIn ){
        song=songIn;
        ItemsCount = song.getRRRowsCount();
        ItemsCurCount = 0;
        onRItemListener=onRItemListenerIn;
        //itemTouchHelper = itemTouchHelperIn;
    }
    @NonNull
    @Override
    public RowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();//todo убедиться, что память не ликается
        int LayoutIDForListItem=R.layout.rrrow_list_item;
        LayoutInflater inflater=LayoutInflater.from(context); // чтоб надуть объект из XML
        View view = inflater.inflate(LayoutIDForListItem,parent,false); // непосредственно надуваем (создаём компонент из XML описания)
        RowsViewHolder viewHolder = new RowsViewHolder(view,onRItemListener);
        //viewHolder.tv_ItemNum.setText("tv: номер холдера: " + ItemsCurCount);
        ItemsCurCount++;
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull RowsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return ItemsCount;
    }
                                                        //=================================ХОЛДЕР
    class RowsViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        View ParentItem; //todo проверить лик памяти
        //TextView tv_ItemNum,;
        TextView et_EN_Row,et_QU_Row,et_RU_Row;
        private OnRItemListener onRItemListener;
        //private ItemTouchHelper itemTouchHelper;

        public RowsViewHolder(@NonNull View itemView, OnRItemListener onRItemListenerIn   ) {
            super(itemView);
            ParentItem=itemView;
            itemView.setOnLongClickListener(this);
            //itemView.setOnTouchListener(this);
            /*itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    itemTouchHelper.startDrag();

                    return false;
                }
            });*/
            this.onRItemListener = onRItemListenerIn;
            //this.itemTouchHelper = itemTouchHelperIn;
            //tv_ItemNum=itemView.findViewById(R.id.li_tv_item_num);
            et_EN_Row=(TextView)itemView.findViewById(R.id.li_tv_EN_row);
            et_QU_Row=(TextView)itemView.findViewById(R.id.li_tv_QU_row);
            et_RU_Row=(TextView)itemView.findViewById(R.id.li_tv_RU_row);

        }

        private void initET(String text) {
            //String definition = "Clickable words in text view ".trim();
            //TextView definitionView = (EditText ) ParentItem.findViewById(R.id.li_et_EN_row);
            et_EN_Row.setMovementMethod(LinkMovementMethod.getInstance());
            et_EN_Row.setText(text, EditText.BufferType.SPANNABLE);
            Spannable spans = (Spannable) et_EN_Row.getText();
            BreakIterator iterator = BreakIterator.getWordInstance(Locale.US);
            iterator.setText(text);
            int start = iterator.first();
            for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator
                    .next()) {
                String possibleWord = text.substring(start, end);
                if (Character.isLetterOrDigit(possibleWord.charAt(0))) {
                    ClickableSpan clickSpan = getClickableSpan(possibleWord);
                    spans.setSpan(clickSpan, start, end,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        private ClickableSpan getClickableSpan(final String word) {

            return new ClickableSpan() {
                String URL=null;
                final String mWord;
                {
                    mWord = word;
                }
                @Override
                public void onClick(View widget) {
                    //Log.d("tapped on:", mWord);
                    Toast.makeText(widget.getContext(), mWord, Toast.LENGTH_SHORT).show();


                    Dialog DialTr =new Dialog(widget.getContext());
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
        }
       void bind (int listIndex){
            et_EN_Row.setText(song.getSomeRow(listIndex, Song.RowType.ENG));
            //initET(song.getSomeRow(listIndex, Song.RowType.ENG));
            et_QU_Row.setText(song.getSomeRow(listIndex, Song.RowType.QU));//song.getSomeRow(listIndex, Song.RowType.QU));
            et_RU_Row.setText(song.getSomeRow(listIndex, Song.RowType.RU));//song.getSomeRow(listIndex, Song.RowType.RU));

            //to do сначала сделать выбор из сохранённых, чтоб не ебаться каждый раз с буфером
            // потом уже понять, как можно вводимый текст связать обратно с Сонгом
        }


        @Override
        public boolean onLongClick(View v) {


            //Toast.makeText(this, String.format("Вью:%s", v.toString()), Toast.LENGTH_SHORT).show();
            onRItemListener.onRowItemLongClick(getAdapterPosition());
            //v.findViewById()
            return false;
        }


    }
    public interface OnRItemListener {
        void onRowItemLongClick(int position);
    }
}
