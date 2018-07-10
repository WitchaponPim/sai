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
import com.example.tan.mtapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.MenuViewHolder> {
    String TAG = "Adapter";
    Context mContext;
    List<ActivityModel.DetailBean> mActivityModel;
    OnItemClickListener mListener;
    String URL = "http://meeting.wat-huathanon.com";

    public ActivityAdapter(Context context, List<ActivityModel.DetailBean> activityModels, OnItemClickListener listener) {
        this.mContext = context;
        this.mActivityModel = activityModels;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menulaout, parent, false);
//        Log.d(TAG, "onCreateViewHolder: ");
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
//        Log.d(TAG, "onBindViewHolder: ");
        holder.setMenu(mActivityModel, position);
    }

    @Override
    public int getItemCount() {
        return mActivityModel.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView discription, roomName, mDate;
        Button more, regis;

        public MenuViewHolder(View itemView) {
            super(itemView);
//            Log.d(TAG, "MenuViewHolder: ");
            discription = (TextView) itemView.findViewById(R.id.Description);
            roomName = (TextView) itemView.findViewById(R.id.roomName);
            mDate = (TextView) itemView.findViewById(R.id.actDate);
            more = (Button) itemView.findViewById(R.id.btn1);
            regis = (Button) itemView.findViewById(R.id.btn2);
            imageView = (ImageView) itemView.findViewById(R.id.imageVmenu);

            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mListener.moreClick(mActivityModel, getAdapterPosition());
                }
            });
            regis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.regisClick(mActivityModel, getAdapterPosition());
                }
            });

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Log.d(TAG, "onClick: ");
//                    mListener.onItemClick(mActivityModel,getAdapterPosition());
//                }
//            });
        }

        public void setMenu(List<ActivityModel.DetailBean> activityAdapters, int position) {
            String pic = null;
            Log.d(TAG, "setMenu: " + activityAdapters.get(position).getPicture());
            discription.setText("กิจกรรม : " + activityAdapters.get(position).getAc_name());
            roomName.setText("ห้อง : " + activityAdapters.get(position).getRoom_name());
            mDate.setText("วันที่จัด : \n" + activityAdapters.get(position).getStart_date().replace("2018", "18")
                    + " - " + activityAdapters.get(position).getEnd_date().replace("2018", "18"));
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
            if (activityAdapters.get(position).getActivity_pay().equals("0")) {
                regis.setText("REGISTER");
            } else {
                regis.setText("RESERVE");
            }
        }
    }

    public interface OnItemClickListener {
        void moreClick(List<ActivityModel.DetailBean> activityAdapters, int position);

        void regisClick(List<ActivityModel.DetailBean> activityAdapters, int position);
    }
}
