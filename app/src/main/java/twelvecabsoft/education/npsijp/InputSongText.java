package twelvecabsoft.education.npsijp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class InputSongText extends AppCompatActivity {
    EditText SongText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_song_text);
        SongText=findViewById(R.id.etml_SongText);
        //int i= SongText.getLineCount();
    }


    public void btnTakeTextClick(View view)  {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String pasteData = "";
        ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
        pasteData = (String) item.getText();
        if (pasteData!=null){
            SongText.setText(pasteData);
            String[] array = pasteData.split("\n", -1);
            pasteData="";
            FileOutputStream fOut = null;
            /*try {
                fOut = openFileOutput("test.txt",MODE_APPEND );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }*/
            FileInputStream fin =null;
            try {
                FileInputStream fin = openFileInput("dasda");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            fOut.close();
            /*There are certainly some other important methods, which are as mentioned below:

            getFileDir()- It helps to get the absolute path to the directory where internal files are there.
            getDir()- It opens an existing directory in the internal storage space. It also creates one, if it doesn’t exist.
            deleteFile()- It deletes an existing file that was there on the internal storage.
                    fileList()- This method returns an array of the files currently saved by the application
            write (byte[] buffer, int byteOffset, int byteCount)- It writes count bytes from the byte array buffer that starts from the position offset to the stream. It is for FileInputStream.
                    read (byte[] buffer, int byteOffset, int byteCount)- It reads the length bytes and stores them in the byte array that starts at the offset. It is for FileOutputStream.*/
        }



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