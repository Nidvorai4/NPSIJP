package twelvecabsoft.education.npsijp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentUris;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

import twelvecabsoft.education.npsijp.Adapters.RowsAdapter;
import twelvecabsoft.education.npsijp.Adapters.SongsChoiceAdapter;

public class MainActivity extends AppCompatActivity  implements PopupMenu.OnMenuItemClickListener,
                                                                SongsChoiceAdapter.OnChItemListener,
                                                                RowsAdapter.OnRItemListener {
    ArrayList<Song> LSongs;
    Song CurSong;
    private RecyclerView rv_SongText;

    private RowsAdapter rowsAdapter;
    private SongsChoiceAdapter songsAdapter;
    private ItemTouchHelper itemTouchHelper;
    private LinearLayout BottomPanel;
    private int EditableRRRowIndex=0;
    private EditText etEditRow;
    private void ShowRVSongText()
    {
        rv_SongText=findViewById(R.id.rv_rrrows);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_SongText.setLayoutManager(layoutManager);
        //rv_SongText.setHasFixedSize(true); знаем размер списка заранее (я не факт, что знаю). Повышает производительность
        rowsAdapter =new RowsAdapter(CurSong,this);
        rv_SongText.setAdapter(rowsAdapter);
        itemTouchHelper.attachToRecyclerView(rv_SongText);


    }
    private void ShowRVChoiceSong(){
        rv_SongText=findViewById(R.id.rv_rrrows);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_SongText.setLayoutManager(layoutManager);
        //rv_SongText.setHasFixedSize(true); знаем размер списка заранее (я не факт, что знаю). Повышает производительность
        songsAdapter =new SongsChoiceAdapter(LSongs,this);
        rv_SongText.setAdapter(songsAdapter);

        itemTouchHelper.attachToRecyclerView(rv_SongText);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LSongs=new ArrayList<Song>();
        etEditRow=(EditText) findViewById(R.id.et_EditRow );
        itemTouchHelper=new ItemTouchHelper(simpleCallback);
        BottomPanel=(LinearLayout)findViewById(R.id.ll_bottom_bar);

        etEditRow.addTextChangedListener(new TextWatcher() {
            private static final String TAG = "MainActivity:textChange";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged: before" +s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: on"+s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                CurSong.setCurEditedRRRowText(s.toString());
                Objects.requireNonNull(rv_SongText.getAdapter()).notifyDataSetChanged();

                Log.d(TAG, "afterTextChanged: after");
            }
        });


    }
//ItemTouchHelper.UP | ItemTouchHelper.RIGHT
    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(
                                            0,
            0){

        private static final String TAG = "Тач хелпер" ;
       /* @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }*/

        @Override
        public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
            if(actionState==ItemTouchHelper.ACTION_STATE_DRAG){
                Log.d(TAG, "onSelectedChanged: ");
            }

            super.onSelectedChanged(viewHolder, actionState);
        }


        /*
        @Override  onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            super.onSelectedChanged(viewHolder, actionState)
            switch (actionState) {
                ItemTouchHelper.ACTION_STATE_DRAG :
                    Log.d("DragTest","Start to drag: $actionState")
                ItemTouchHelper.ACTION_STATE_SWIPE :
                    Log.d("DragTest","Start to swipe: $actionState")
                ItemTouchHelper.ACTION_STATE_IDLE : {
                    Log.d("DragTest","End action: $actionState")
                }
            }
        }
*/


        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            String S="Потащено  " + String.valueOf(viewHolder.getAdapterPosition()) + "" +String.valueOf(getDragDirs(recyclerView,viewHolder)) ;

            Toast.makeText(viewHolder.itemView.getContext(), S, Toast.LENGTH_SHORT).show();
            return false;
        }
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            String S="Засвайплено " + String.valueOf(viewHolder.getAdapterPosition()) + direction;

            Toast.makeText(viewHolder.itemView.getContext(), S, Toast.LENGTH_SHORT).show();

        }

//TODO доубрать следы второго диалога. переделать длинный клик на едит внизу

    };

    public void btnMenuOnClick(View view) {
        PopupMenu PopM = new PopupMenu(this, view);
        PopM.setOnMenuItemClickListener(this);
        PopM.inflate(R.menu.main_menu);
        PopM.show();
    }

    private void SaveSongs()
    {
        LoadSongs();
        LSongs.add(CurSong);
        Gson StoF =new GsonBuilder().setPrettyPrinting().create();
        String res=StoF.toJson(LSongs);


        FileOutputStream fOut = null;
        try {
            fOut = openFileOutput("test.txt", MODE_PRIVATE );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(fOut!=null) {
            try {
                fOut.write(res.getBytes());
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //String[] fl = fileList();
            //fl=null;
            //int f;
            //f=3;
        }// файл открылся
    }

    private void SaveFromBufToFile()
    {
        //LoadSongs();
        //LSongs.add(CurSong);
        //Gson StoF =new GsonBuilder().setPrettyPrinting().create();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String pasteData = null;
        ClipData.Item CB_item = clipboard.getPrimaryClip().getItemAt(0);
        pasteData = (String) CB_item.getText();
        if (pasteData!=null) {
            FileOutputStream fOut = null;
            try {
                fOut = openFileOutput("test.txt", MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (fOut != null) {
                try {
                    fOut.write(pasteData.getBytes());
                    fOut.close();
                    Toast.makeText(this, "Запихано в файл", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }// файл открылся
        }//в буфере что-то есть
    }


    private void LoadSongs(){
        String res=null;

        try
        {

            InputStreamReader tmp = new InputStreamReader(this.openFileInput ("test.txt") , StandardCharsets.UTF_8);
            //FileInputStream fin =  openFileInput("test.txt");
            //fin.
            //String Enc=fin.
            int a = 0;
            StringBuilder temp = new StringBuilder();
            while ((a = tmp.read()) != -1)
            {
                temp.append((char)a);
            }

            // setting text from the file.
            //fileContent.setText(temp.toString());
            res=temp.toString();
            tmp.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        if (res!=null)
        {

            Gson FtoJ =new GsonBuilder().setPrettyPrinting().create();
            LSongs=FtoJ.fromJson(res,new TypeToken<ArrayList<Song>>(){}.getType());
        }       
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menOpenSaved:
                LoadSongs();
                ShowRVChoiceSong();
                return true;
            case R.id.menFromBuftoFile:
                SaveFromBufToFile();

                return true;
            case R.id.menStartNewTr :
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String pasteData = null;
                ClipData.Item CB_item = clipboard.getPrimaryClip().getItemAt(0);
                pasteData = (String) CB_item.getText();
                if (pasteData!=null) {
                    CurSong = new Song("Артист", "Альбом", "Название", pasteData);
                    LSongs.add(CurSong);
                    CurSong.getSongInfo(false,false,false);
                }
                ShowRVSongText();
                return true;
            case R.id.menSaveAll :
                SaveSongs();
                return true;


            case R.id.menR_editEN:
                //CurSong.setCurEditedRowType(Song.RowType.ENG);
                show_etRowText(Song.RowType.ENG);

               return true;

            case R.id.menR_editQU:
                show_etRowText(Song.RowType.QU);
                return true;



            case R.id.menR_editRU:
                show_etRowText(Song.RowType.RU);
                return true;
            default:
                //throw new IllegalStateException("Unexpected value: " + item.toString());
                return false;
        }
        //Toast.makeText(this,item.toString(),Toast.LENGTH_LONG).show();
       // return true;
        //switch (item)



    }
    private void show_etRowText(@Song.RowType int Type){
        etEditRow.setText(CurSong.editRowText_Start(EditableRRRowIndex, Type));
        BottomPanel.setVisibility(View.VISIBLE);

        etEditRow.requestFocus();
        etEditRow.setSelection(etEditRow.getText().length());


    }
    @Override
    public void onChoiceItemClick(int position) {
        CurSong = LSongs.get(position);
        CurSong.editRowText_Stop(false); //для установки -1 после десериализации
        ShowRVSongText();




        //Toast.makeText(this, "жмакнуто" +position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRowItemLongClick(int position) {
        EditableRRRowIndex=position;
        //CurSong.setCurEditableRowPos(position);
        CurSong.editRowText_Stop(false);
        PopupMenu PopM = new PopupMenu(this, rv_SongText.getChildAt(position));
        PopM.setOnMenuItemClickListener(this);
        PopM.inflate(R.menu.row_menu);
        PopM.show();

        //Toast.makeText(this, "жмакнуто" +position, Toast.LENGTH_SHORT).show();
    }

    public void btnConfirmEditClick(View view) {
        //CurSong.setCurEditableRowPos(EditableRRRowIndex);
        CurSong.editRowText_Stop(true);
        rv_SongText.getAdapter().notifyDataSetChanged();
        //View view = this.getCurrentFocus();
        if (view != null) { //прятки клавиатуры
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        BottomPanel.setVisibility(View.GONE);
        //etEditRow.setVisibility(View.GONE);


    }
}