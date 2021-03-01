package twelvecabsoft.education.npsijp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import twelvecabsoft.education.npsijp.R;
import twelvecabsoft.education.npsijp.Song;

public class RowsAdapter extends  RecyclerView.Adapter<RowsAdapter.RowsViewHolder>{
    Song song;
    private int ItemsCount;
    private int ItemsCurCount;
    public RowsAdapter(Song songIn){
        song=songIn;
        ItemsCount = song.getRRRowsCount();
        ItemsCurCount = 0;
    }
    @NonNull
    @Override
    public RowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();//TODO убедиться, что память не ликается
        int LayoutIDForListItem=R.layout.rrrow_list_item;
        LayoutInflater inflater=LayoutInflater.from(context); // чтоб надуть объект из XML
        View view = inflater.inflate(LayoutIDForListItem,parent,false); // непосредственно надуваем (создаём компонент из XML описания)
        RowsViewHolder viewHolder = new RowsViewHolder(view);
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

    class RowsViewHolder extends RecyclerView.ViewHolder{
        //TextView tv_ItemNum,;
        EditText et_EN_Row,et_QU_Row,et_RU_Row;

        public RowsViewHolder(@NonNull View itemView) {
            super(itemView);
            //tv_ItemNum=itemView.findViewById(R.id.li_tv_item_num);
            et_EN_Row=(EditText)itemView.findViewById(R.id.li_et_EN_row);
            et_QU_Row=(EditText)itemView.findViewById(R.id.li_et_QU_row);
            et_RU_Row=(EditText)itemView.findViewById(R.id.li_et_RU_row);
        }
        void bind (int listIndex){
            et_EN_Row.setText(song.getSomeRow(listIndex, Song.RowType.ENG));
            et_QU_Row.setText("полухуй");//song.getSomeRow(listIndex, Song.RowType.QU));
            et_RU_Row.setText("хуй");//song.getSomeRow(listIndex, Song.RowType.RU));
            //todo сначала сделать выбор из сохранённых, чтоб не ебаться каждый раз с буфером
            // потом уже понять, как можно вводимый текст связать обратно с Сонгом
        }

    }
}
