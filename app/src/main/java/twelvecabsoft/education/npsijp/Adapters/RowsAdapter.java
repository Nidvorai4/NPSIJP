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

public class RowsAdapter extends  RecyclerView.Adapter<RowsAdapter.RowsViewHolder>{
    private int ItemsCount;
    private int ItemsCurCount;
    public RowsAdapter(int Items_Count){
        ItemsCount = Items_Count;
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
        viewHolder.tv_ItemNum.setText("tv: номер холдера: " + ItemsCurCount);
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
        TextView tv_ItemNum;
        EditText et_Row;

        public RowsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ItemNum=itemView.findViewById(R.id.li_tv_item_num);
            et_Row=itemView.findViewById(R.id.li_et_one_row);
        }
        void bind (int listIndex){
            et_Row.setText("et_изменяемое значение: "+String.valueOf(listIndex));

        }

    }
}
