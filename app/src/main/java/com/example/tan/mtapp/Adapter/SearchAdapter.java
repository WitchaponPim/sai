package com.example.tan.mtapp.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.tan.mtapp.Model.SearchModel;
import com.example.tan.mtapp.R;
import com.example.tan.mtapp.StaticClass;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MenuViewHolder> implements Filterable {
    Context context;
    ProgressDialog progressDialog;
    List<SearchModel> models;
    List<SearchModel> mStringFilterList;
    ValueFilter valueFilter;
    SearchAdapter.OnItemClickListener listener;
    String TAG = "SearchAdapter";

    public interface OnItemClickListener {
        void onItemClick(SearchModel searchModel, int position);

    }

    public SearchAdapter(Context context, List<SearchModel> models, SearchAdapter.OnItemClickListener listener) {
        Log.d(TAG, "SearchAdapter: " + String.valueOf(models.size()));
        this.context = context;
        this.models = models;
        this.listener = listener;
        mStringFilterList = models;
    }

    @Override
    public SearchAdapter.MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_search, parent, false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MenuViewHolder holder, int position) {
        holder.setMenu(models, position);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            ArrayList<String> list = new ArrayList<>();

            if (constraint != null && constraint.length() > 0) {
                List<SearchModel> filterList = new ArrayList();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getFullname().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                    if ((mStringFilterList.get(i).getJob().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                    if ((mStringFilterList.get(i).getSex().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                    if ((mStringFilterList.get(i).getType().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                    if ((mStringFilterList.get(i).getTel().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                    if ((mStringFilterList.get(i).getId_attendee().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;

            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            models = (List) results.values;
            notifyDataSetChanged();
        }
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView idcard, name, sex, tel, type, job;

        public MenuViewHolder(View itemView) {
            super(itemView);
            idcard = (TextView) itemView.findViewById(R.id.idCard);
            name = (TextView) itemView.findViewById(R.id.name);
            sex = (TextView) itemView.findViewById(R.id.sex);
            tel = (TextView) itemView.findViewById(R.id.tel);
            type = (TextView) itemView.findViewById(R.id.type);
            job = (TextView) itemView.findViewById(R.id.job);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(models.get(getAdapterPosition()),getAdapterPosition());
                }
            });
        }

        public void setMenu(List<SearchModel> models, int position) {
            idcard.setText(models.get(position).getId_attendee());
            name.setText(models.get(position).getFullname());
            sex.setText(models.get(position).getSex());
            tel.setText(models.get(position).getTel());
            type.setText(models.get(position).getType());
            job.setText(models.get(position).getJob());

            if (models.get(position).getType().equals("attendee")) {
                type.setTextColor(context.getResources().getColor(R.color.colorHead));
            } else {
                type.setTextColor(context.getResources().getColor(R.color.colorFollow));
            }
        }
    }
}
