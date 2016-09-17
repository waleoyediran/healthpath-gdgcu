package com.healthpath.healthpath;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 *
 * Created by oyewale on 17/09/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private List<Hospital> hospitals;

    public MyAdapter(Context context, List<Hospital> list){
        mContext = context;
        hospitals = list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row_hospital, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Hospital event = hospitals.get(position);
        ((ItemViewHolder)(holder)).bindEvent(event);
    }

    @Override
    public int getItemCount() {
        return hospitals != null ? hospitals.size() : 0;
    }

    public void refresh(List<Hospital> posts) {
        this.hospitals = posts;
        notifyDataSetChanged();
    }




    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTextView;
        private Hospital hosp;

        TextView hospitalNameText;
        TextView postDescriptionText;

        public ItemViewHolder(View itemView) {
            super(itemView);
            hospitalNameText = (TextView) itemView.findViewById(R.id.hospital_name);

        }

        public void bindEvent(final Hospital hosp){
            this.hosp = hosp;

            hospitalNameText.setText(hosp.name);
        }

    }
}


