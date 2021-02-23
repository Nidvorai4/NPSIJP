package twelvecabsoft.education.npsijp;

public class Song {
    String Artist;
    String Album;
    String Title;
    RowrOwroWText[] RRRows;
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
