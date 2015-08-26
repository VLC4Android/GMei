package yifeiyuan.practice.gmei;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alanchen on 15/8/26.
 */
public class GMeiAdapter extends RecyclerView.Adapter<GMeiAdapter.GViewHolder>{

    List<GMeiZi>mData;
    private Context mContext;
    public GMeiAdapter(List<GMeiZi>datas,Context mContext,OnGMeiClickListener onGMeiClickListener){
        this.mData = datas;
        mOnGMeiClickListener = onGMeiClickListener;
        this.mContext = mContext;
    }


    @Override
    public GViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_meizi,parent,false);
        return new GViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(GViewHolder holder, int position) {

        GMeiZi gmei = mData.get(position);

        Glide.with(mContext).load(gmei.getUrl()).into(holder.meizi);

        holder.itemView.setOnClickListener(v -> mOnGMeiClickListener.onClick(gmei,position));
    }

    static class GViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.iv_item)
        ImageView meizi;
        public GViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    OnGMeiClickListener mOnGMeiClickListener;
    interface OnGMeiClickListener{
        void onClick(GMeiZi gmei,int position);
    }
}
