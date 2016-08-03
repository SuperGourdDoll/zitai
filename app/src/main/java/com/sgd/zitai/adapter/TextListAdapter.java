package com.sgd.zitai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sgd.zitai.R;
import com.sgd.zitai.bean.TextEntity;
import com.sgd.zitai.ui.widget.CircleImageView;
import com.sgd.zitai.ui.widget.DrawableCenterTextView;
import com.sgd.zitai.ui.widget.DrawableCenterCheckBox;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List <TextEntity> mDatas;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onUserHeadClick(View view ,int position);

        void onItemLongClick(View view, int position);
    }


    public TextListAdapter(List<TextEntity> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      /*  View view = LayoutInflater.from(mContext).inflate(R.layout.item_text,parent,false);
        TextViewHolder holder = new TextViewHolder(view);*/

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_text, parent,
                    false);
            return new TextViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_footer, parent,
                    false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            //holder.tv.setText(data.get(position));
            TextViewHolder textHolder = (TextViewHolder) holder;
            TextEntity data = mDatas.get(position);
            if (onItemClickListener != null) {
                textHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });

                textHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
                textHolder.itemUserHead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onUserHeadClick(holder.itemView, position);
                    }
                });

            }
            textHolder.itemContent.setText(data.content);
            textHolder.itemUserName.setText(data.userName);
        }
    }

    @Override
    public int getItemCount() {
        return (mDatas != null && mDatas.size() != 0) ? mDatas.size() + 1 : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    /*  普通的itemView  */
    public class TextViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_text_card_content)
        TextView itemContent;
        @BindView(R.id.item_text_card_username)
        TextView itemUserName;
        @BindView(R.id.item_text_card_userhead)
        CircleImageView itemUserHead;
        @BindView(R.id.item_text_card_pictures)
        RecyclerView itempictures;
        @BindView(R.id.item_text_card_praise)
        DrawableCenterCheckBox itemPraise;
        @BindView(R.id.item_text_card_comment)
        DrawableCenterTextView itemComment;
        @BindView(R.id.item_text_card_share)
        DrawableCenterTextView itemShare;

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    /* footer的itemView  */
    public class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View view) {
            super(view);
        }
    }
}
