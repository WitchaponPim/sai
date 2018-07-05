package com.example.tan.mtapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tan.mtapp.Model.ActivityModel;
import com.example.tan.mtapp.Model.HistoryMedel;
import com.example.tan.mtapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class ConfirmAdapter extends RecyclerView.Adapter<ConfirmAdapter.MenuViewHolder> {
    String TAG ="Adapter";
    Context mContext;
    List<HistoryMedel> history;
    OnItemClickListener mListener;

    public ConfirmAdapter(Context context, List<HistoryMedel> activityModels, OnItemClickListener listener) {
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
            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.moreClick(history.get(getAdapterPosition()).getDetail().get(0),getAdapterPosition());
                }
            });



        }

        public void setMenu(List<HistoryMedel> activityAdapters, int position) {
            date.setText(activityAdapters.get(position).getDate());
            act.setText(activityAdapters.get(position).getActivity_name());

        }
    }

    public interface OnItemClickListener {
        void moreClick(HistoryMedel.DetailBean activityAdapters, int position);
    }
}
