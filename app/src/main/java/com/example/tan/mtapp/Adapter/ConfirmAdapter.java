package com.example.tan.mtapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tan.mtapp.Model.HistoryModel;
import com.example.tan.mtapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ConfirmAdapter extends RecyclerView.Adapter<ConfirmAdapter.MenuViewHolder> {
    String TAG ="Adapter";
    Context mContext;
    List<HistoryModel> history;
    OnItemClickListener mListener;
    String URL = "http://meeting.wat-huathanon.com";

    public ConfirmAdapter(Context context, List<HistoryModel> activityModels, OnItemClickListener listener) {
        this.mContext = context;
        this.history = activityModels;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.act_row, parent, false);

        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

        holder.setMenu(history, position);
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView act,date;
        Button detail;
        public MenuViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.act_date);
            act = (TextView) itemView.findViewById(R.id.act_name);
            detail = (Button) itemView.findViewById(R.id.btn);
            imageView = (ImageView) itemView.findViewById(R.id.imageVmenu);
            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.moreClick(history.get(getAdapterPosition()).getDetail().get(0),getAdapterPosition());
                }
            });



        }

        public void setMenu(List<HistoryModel> activityAdapters, int position) {
            String pic = null;
            date.setText(activityAdapters.get(position).getDate());
            act.setText(activityAdapters.get(position).getActivity_name());
            //../project
            if(!activityAdapters.get(position).getPicture().isEmpty()){
                pic = activityAdapters.get(position).getPicture().replace("../project", "");
            }
            Picasso.get()
                    .load(URL + pic)
                    .error(R.drawable.pricess)
                    .resize(80, 100)
                    .centerCrop()
                    .into(imageView);

        }
    }

    public interface OnItemClickListener {
        void moreClick(HistoryModel.DetailBean activityAdapters, int position);
    }
}
