package twelvecabsoft.education.npsijp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class InputSongText extends AppCompatActivity {
    EditText SongText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_song_text);
        SongText=findViewById(R.id.etml_SongText);
        //int i= SongText.getLineCount();
    }

    private String readData()
    {
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
        return res;
        //printMessage("reading to file " + filename + " completed..");
    }


    public void btnTakeTextClick(View view)  {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String pasteData = "";
        ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
        pasteData = (String) item.getText();

            //Gson JtoF =ne




        if (pasteData!=null){
            SongText.setText(pasteData);
            String[] array = pasteData.split("\n", -1);
            Song[] SS=new Song[2];
            SS[0]=new Song("Исполнитель","Альбом", "Название",pasteData);
            SS[1]=new Song("Ar","AL","Tit","text of song");
            //ProgSettings PS=new ProgSettings(321,"иди в хуй");
            Gson StoF =new GsonBuilder().setPrettyPrinting().create();
            String res=StoF.toJson(SS);



            //StoF.


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
                //JsonWriter r
                //TO_DO вроде сохраняется. Надо проверить, прочитав, и понять, как лучше хранить (xml, json...)
            }// файл открылся

            res =readData();

            Song[] SS2= StoF.fromJson(res, Song[].class);
                res="dsasad";
            SS2[1].Artist ="HUUUUUUUUUUI";



            //fOut.close();
            /*There are certainly some other important methods, which are as mentioned below:

            getFileDir()- It helps to get the absolute path to the directory where internal files are there.
            getDir()- It opens an existing directory in the internal storage space. It also creates one, if it doesn’t exist.
            deleteFile()- It deletes an existing file that was there on the internal storage.
            fileList()- This method returns an array of the files currently saved by the application
            write (byte[] buffer, int byteOffset, int byteCount)- It writes count bytes from the byte array buffer that starts from the position offset to the stream. It is for FileInputStream.
                    read (byte[] buffer, int byteOffset, int byteCount)- It reads the length bytes and stores them in the byte array that starts at the offset. It is for FileOutputStream.*/
        }// в буфере какой-то текст



        //pasteData="";
        /*       int RawCount= SongText.getLineCount();
        //i++;
        String[] Stroki =new String[RawCount];
        for (int i=0;i<RawCount;i++)
        {
            int startPos = SongText.getLayout().getLineStart(i);
            int endPos = SongText.getLayout().getLineEnd(i);
            Stroki[i] = SongText.getText().toString().substring(startPos, endPos).trim();
        }
        RawCount++;*/

    }
}