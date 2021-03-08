package twelvecabsoft.education.npsijp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import twelvecabsoft.education.npsijp.R;
import twelvecabsoft.education.npsijp.Song;

public class SongsChoiceAdapter extends  RecyclerView.Adapter<SongsChoiceAdapter.SongChoiceViewHolder>{
    //private final View.OnClickListener mOnClickListener = new
    ArrayList <Song> lsongs;
    private int ItemsCount;
    private int ItemsCurCount;
    private OnChItemListener onChItemListener;
    private ItemTouchHelper itemTouchHelper;
    public SongsChoiceAdapter(ArrayList<Song> lsongsIn, OnChItemListener onChItemListenerIn){
        lsongs=lsongsIn;
        ItemsCount = lsongs.size();
        ItemsCurCount = 0;
        onChItemListener = onChItemListenerIn; // todo проверить лики
    }
    @NonNull
    @Override
    public SongChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();//todo убедиться, что память не ликается
        int LayoutIDForListItem=R.layout.songs_list_item;
        LayoutInflater inflater=LayoutInflater.from(context); // чтоб надуть объект из XML
        View view = inflater.inflate(LayoutIDForListItem,parent,false); // непосредственно надуваем (создаём компонент из XML описания)
        SongChoiceViewHolder viewHolder = new SongChoiceViewHolder(view, onChItemListener);
        //viewHolder.tv_ItemNum.setText("tv: номер холдера: " + ItemsCurCount);
        ItemsCurCount++;
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull SongChoiceViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return ItemsCount;
    }
                                                            //=========================ХОЛДЕР
    class SongChoiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_SongName;
        OnChItemListener onChItemListener;
        public SongChoiceViewHolder(@NonNull View itemView, OnChItemListener onChItemListenerIn) {
            super(itemView);
            tv_SongName=itemView.findViewById(R.id.sli_tv_SongItem);
            itemView.setOnClickListener(this);
            this.onChItemListener = onChItemListenerIn;
        }
        void bind (int listIndex){
            //ArrayList<int> AL=new ArrayList<int>(new Song);

            Song SS=lsongs.get(listIndex);
            String S=SS.getSongInfo(true,true,true);
            tv_SongName.setText(S);

        }

        @Override
        public void onClick(View v) {

            onChItemListener.onChoiceItemClick(getAdapterPosition());
        }
    }
    public interface OnChItemListener {
        void onChoiceItemClick(int position);
    }
}
