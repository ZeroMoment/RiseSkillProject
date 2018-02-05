package lzm.cn.riseskillproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lzm.cn.riseskillproject.R;

/**
 * Created by lizhiming on 2018/2/1.
 */

public class MainLeftMenuAdapter extends RecyclerView.Adapter {

    private List<String> mMenuDatas;

    private Context mContext;

    public MainLeftMenuAdapter(Context context, ItemClickListner itemClickListner) {
        this.mContext = context;
        this.mItemClickListener = itemClickListner;
    }


    public void setMenuDatas(List<String> menuDatas) {
        this.mMenuDatas = menuDatas;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_menu, parent, false);
        MyViewHolder holder  = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.menuTv.setText(mMenuDatas.get(position));
    }

    @Override
    public int getItemCount() {
        if(mMenuDatas == null || mMenuDatas.isEmpty()) {
            return 0;
        }
        return mMenuDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView menuTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            menuTv = (TextView) itemView.findViewById(R.id.item_main_menu_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    private ItemClickListner mItemClickListener;
    public interface ItemClickListner {
        void onItemClick(int poisition);
    }

}
