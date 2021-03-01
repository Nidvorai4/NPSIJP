package twelvecabsoft.education.npsijp;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Song {
    String Artist;
    String Album;
    String Title;
    RowrOwroWText[] RRRows;
    //Define the list of accepted constants
    @IntDef({RowType.ENG,RowType.QU,RowType.RU})
    //Tell the compiler not to store annotation data in the .class file
    @Retention(RetentionPolicy.SOURCE)
    public @interface RowType {
        int ENG=0;
        int QU=1;
        int RU=2;
    }

    /** @return Утроенное количество строк */
    public int getRRRowsCount(){
        return RRRows.length;
    }
    public String getSomeRow(int RRRid,@RowType int rt){
        switch (rt) {
            case RowType.ENG:
                return RRRows[RRRid].getRowEng();
                //break;
            case RowType.QU:
                return RRRows[RRRid].getRowQu();
                //break;
            case RowType.RU:
                return RRRows[RRRid].getRowRu();
                //break;
            default:
                throw new IllegalStateException("Unexpected value: 0,1,2. Got: " + rt);
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
