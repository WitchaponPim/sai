package com.example.tan.mtapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tan.mtapp.Model.ActivityModel;
import com.example.tan.mtapp.R;

import java.util.List;

public class HitoryAdapter  extends RecyclerView.Adapter<ActivityAdapter.MenuViewHolder> {

    Context mContext;
    List<ActivityModel.DetailBean> mActivityModel;
    ActivityAdapter.OnItemClickListener mListener;

    public HitoryAdapter(Context context, List<ActivityModel.DetailBean> activityModels, ActivityAdapter.OnItemClickListener listener) {
        this.mContext = context;
        this.mActivityModel = activityModels;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ActivityAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityAdapter.MenuViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//        TextView discription;
//        Button more,regis;
        public MenuViewHolder(View itemView) {
            super(itemView);
//            Log.d(TAG, "MenuViewHolder: ");
//            discription = (TextView) itemView.findViewById(R.id.Description);
//            more = (Button) itemView.findViewById(R.id.btn1);
//            regis = (Button) itemView.findViewById(R.id.btn2);
//
//            more.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    mListener.moreClick(mActivityModel,getAdapterPosition());
//                }
//            });
//            regis.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mListener.regisClick(mActivityModel,getAdapterPosition());
//                }
//            });

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d(TAG, "onClick: ");
//                    mListener.onItemClick(mActivityModel,getAdapterPosition());
//                }
//            });
        }

        public void setMenu(List<ActivityModel.DetailBean> activityAdapters, int position) {
//            Log.d(TAG, "setMenu: ");
//            discription.setText(activityAdapters.get(position).getAc_name());
//            if(activityAdapters.get(position).getActivity_pay().equals("0")){
//                regis.setText("REGISTER");
//            }else{
//                regis.setText("RESERV");
//            }
        }
    }

    public interface OnItemClickListener {
//        void moreClick(List<ActivityModel.DetailBean> activityAdapters, int position);
//        void regisClick(List<ActivityModel.DetailBean> activityAdapters, int position);
    }
}
