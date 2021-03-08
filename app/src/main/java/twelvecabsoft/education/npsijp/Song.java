package twelvecabsoft.education.npsijp;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Song {
    public  String Artist;
    public  String Album;
    public  String Title;
    public  RowrOwroWText[] RRRows;

    private transient  int CurEditableRRRowIndex =-1;
    private transient  @RowType int CurEditableRowType =RowType.ENG;
    private transient  String CurEditableRowText;
    private transient  String SavedRowText;



    //Define the list of accepted constants
    @IntDef({RowType.ENG,RowType.QU,RowType.RU})
    //Tell the compiler not to store annotation data in the .class file
    @Retention(RetentionPolicy.SOURCE)
    public @interface RowType {
        int ENG=0;
        int QU=1;
        int RU=2;
    }
    public String getSongInfo(boolean Art, boolean Alb, boolean Tit)
    {
        String res="";
        if(Art)res=res.concat(Artist);
        if(Alb){
            if (!res.equals(""))res=res.concat(" ");
            res=res.concat(Album);
        }
        if(Tit){
            if (!res.equals(""))res=res.concat(" ");
            res=res.concat(Title);
        }
        return res;
    }
   /* public void setCurEditableRowPos(int pos){
        //EditableRowType=langType;
        CurEditedRRRowIndex =pos;
    }*/
    /*public void setCurEditedRowTextAndType(String Text,@RowType int langType){

        CurEditableRowType =langType;
    }*/


    /** @return Утроенное количество строк */
    public int getRRRowsCount(){
        return RRRows.length;
    }



                                                                    //>>>>>>>>> РЕЖИМ РЕДАКТИРОВАНИЯ
    public String editRowText_Start(int Index,@RowType int Language){
       CurEditableRowText =this.getSomeRow(Index,Language);
       CurEditableRRRowIndex =Index;
       CurEditableRowType=Language;

       return CurEditableRowText;
    }
    public void setCurEditedRRRowText(String text) {
        if(CurEditableRRRowIndex!=-1) CurEditableRowText=text;
    }
    public void editRowText_Stop(boolean Save)
    {
        if (Save) {
            RRRows[CurEditableRRRowIndex].SetAnyRowText(CurEditableRowText,CurEditableRowType);
        }
        CurEditableRRRowIndex=-1;
        CurEditableRowText ="";
    }
                                                                    //<<<<<<<<< РЕЖИМ РЕДАКТИРОВАНИЯ



    public String getSomeRow(int RRRid, @RowType int Language){
        if(RRRid==CurEditableRRRowIndex && Language==CurEditableRowType) return CurEditableRowText;
        switch (Language) {
            case RowType.ENG:
                return RRRows[RRRid].getRowEng();
            case RowType.QU:
                return RRRows[RRRid].getRowQu();
            case RowType.RU:
                return RRRows[RRRid].getRowRu();
            default:
                throw new IllegalStateException("Unexpected value (need 0,1,2):" + Language);
        }
    }
    public Song(String Ar, String Al, String Ti, String Text){
        Artist=Ar;Album=Al;Title = Ti;
        String[] array = Text.split("\n", -1);
        RRRows=new   RowrOwroWText[array.length];
        int i=0;
        for (String r : array){
            RRRows[i]=new RowrOwroWText(r.trim()); // заполнение троестрочий текстом из буфера
            i++;
        }
    }
}
