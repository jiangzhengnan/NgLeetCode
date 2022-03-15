package com.ng.ngleetcode.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ng.code.util.ItemInfo;
import com.ng.ngleetcode.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LeftListAdapter extends RecyclerView.Adapter<LeftListAdapter.VH> {

    private Context mContext;
    private List<ItemInfo> mData;

    public LeftListAdapter(Context mContext, List<ItemInfo> mData ) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public interface OnLeftItemClick {
        void onItem(int pos);
    }

    private OnLeftItemClick onLeftItemClick;

    public void setOnLeftItemClick(OnLeftItemClick onLeftItemClick) {
        this.onLeftItemClick = onLeftItemClick;
    }

    @NonNull
    @NotNull
    @Override
    public VH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_node_first, parent, false);
        VH vh = new VH(view);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = vh.getAdapterPosition();
                ItemInfo info = mData.get(position);
                onLeftItemClick.onItem(position);
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull VH holder, int position) {
        ItemInfo info = mData.get(position);
        holder.tvLeft.setText(info.name);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvLeft;

        public VH(@NonNull @NotNull View itemView) {
            super(itemView);
            tvLeft = itemView.findViewById(R.id.title);
        }
    }
}
