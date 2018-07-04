package com.example.tan.mtapp.Adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.tan.mtapp.R;

import java.util.List;

public class SeatAdapter extends BaseAdapter {
    private Context mContext;
    private int mSeat;

    public SeatAdapter(Context context, int seat) {
        mContext = context;
        mSeat = seat;
    }

    public int getCount() {
        return mSeat;
//        return 350;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView btn;
        btn = new TextView(mContext);
        btn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_seat ,0, 0);
//        btn.setText(position);
        return btn;
    }
}
