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

import com.example.tan.mtapp.Model.ActivityModel;
import com.example.tan.mtapp.Model.SeatModel;
import com.example.tan.mtapp.Model.SeatModel.DetailBean;
import com.example.tan.mtapp.R;

import java.util.List;

public class SeatReAdapter extends RecyclerView.Adapter<SeatReAdapter.MenuViewHolder> {
//    String TAG ="Adapter";
    Context mContext;
    List<SeatModel.DetailBean> seat;
    OnItemClickListener mListener;

    public SeatReAdapter(Context context, List<SeatModel.DetailBean> activityModels, OnItemClickListener listener) {
        this.mContext = context;
        this.seat = activityModels;
        this.mListener = listener;
    }



    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat, parent, false);
//        Log.d(TAG, "onCreateViewHolder: ");
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
//        Log.d(TAG, "onBindViewHolder: ");
        holder.setMenu(seat, position);
    }

    @Override
    public int getItemCount() {
        return seat.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView seat,out;
        TextView number;

        public MenuViewHolder(View itemView) {
            super(itemView);
            seat = (ImageView) itemView.findViewById(R.id.seat);
            number = (TextView) itemView.findViewById(R.id.number);
            out = (ImageView) itemView.findViewById(R.id.out);
        }

        public void setMenu(final List<SeatModel.DetailBean> activityAdapters, int position) {

            number.setText(activityAdapters.get(position).getNumber_sit());
            if (activityAdapters.get(position).getStatus().equals("1")){
                seat.setAlpha((float) 0.4);
                out.setVisibility(View.VISIBLE);
            }

            seat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.seatClick(activityAdapters,getAdapterPosition());
                }
            });

        }
    }

    public interface OnItemClickListener {
        void seatClick(List<SeatModel.DetailBean> activityAdapters, int position);
    }

}