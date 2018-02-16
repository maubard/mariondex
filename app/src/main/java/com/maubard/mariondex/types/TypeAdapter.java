package com.maubard.mariondex.types;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maubard.mariondex.R;
import com.maubard.mariondex.data.entity.TypeEntity;

import java.util.List;

/**
 * Created by Marion Aubard on 16/02/2018.
 */

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {

    /***********************************************************
    *  Attributes
    **********************************************************/

    private Context mContext;
    private List<TypeEntity> mTypes;

    /***********************************************************
    *  Constructors
    **********************************************************/

    TypeAdapter(@NonNull Context context, @NonNull List<TypeEntity> types) {
        this.mContext = context;
        this.mTypes = types;
    }

    /***********************************************************
    *  Implements
    **********************************************************/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(mTypes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mTypes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /***********************************************************
        *  Attributes
        **********************************************************/

        private TextView tvTitle;

        /***********************************************************
        *  Constructors
        **********************************************************/

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_row_name);
        }
    }
}
