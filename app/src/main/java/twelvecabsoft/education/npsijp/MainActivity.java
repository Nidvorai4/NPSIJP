package twelvecabsoft.education.npsijp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
import java.util.ListIterator;

import twelvecabsoft.education.npsijp.Adapters.RowsAdapter;

public class MainActivity extends AppCompatActivity  implements PopupMenu.OnMenuItemClickListener {
    ArrayList<Song> LSongs;
    Song CurSong;
    private RecyclerView rv_SongText;
    private RowsAdapter rowsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LSongs=new ArrayList<Song>();

        rv_SongText=findViewById(R.id.rv_rrrows);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_SongText.setLayoutManager(layoutManager);
        //rv_SongText.setHasFixedSize(true); знаем размер списка заранее (я не факт, что знаю). Повышает производительность
        rowsAdapter =new RowsAdapter(100);
        rv_SongText.setAdapter(rowsAdapter);
        //todo намутил ресайкл. Надо в нём создать три поля, кликабельные слова в первом и всю хуйню
    }

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
            fOut = openFileOutput("test.txt",MODE_PRIVATE );
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
            String[] fl = fileList();
            fl=null;
            int f;
            f=3;
        }// файл открылся
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
            LSongs.add(FtoJ.fromJson(res,new TypeToken<ArrayList<Song>>(){}.getType()));
        }
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menStartNewTr :
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String pasteData = null;
                ClipData.Item CB_item = clipboard.getPrimaryClip().getItemAt(0);
                pasteData = (String) CB_item.getText();
                if (pasteData!=null) {
                    CurSong = new Song("Артист", "Альбом", "Название", pasteData);
                }
                return true;
            case R.id.menSaveAll :
                SaveSongs();
                return true;

            default:
                //throw new IllegalStateException("Unexpected value: " + item.toString());
                return false;
        }
        //Toast.makeText(this,item.toString(),Toast.LENGTH_LONG).show();
       // return true;
        //switch (item)
        //TODO загружается, создаётся, сохраняется. Надо мутить визуализацию едиттекстами текущей песни
        //return false;
    }
}