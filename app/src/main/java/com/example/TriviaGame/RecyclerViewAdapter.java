package com.example.TriviaGame;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private List<CountryEntity> mCountryEntityList;
    private Context mCtx;

    private OnItemSelectCallback mCallback;

    public RecyclerViewAdapter(Context ctx, List<CountryEntity> countryEntityList) {
        this.mCountryEntityList = countryEntityList;
        this.mCtx = ctx;

    }

    public void setCallback(OnItemSelectCallback callback) {
        mCallback = callback;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( mCtx );
        View view = inflater.inflate( R.layout.item_row, parent, false );
        return new MyViewHolder( view );
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final CountryEntity countryEntity = mCountryEntityList.get( position );
        holder.view.setBackgroundColor( countryEntity.isSelected() ? Color.LTGRAY : Color.TRANSPARENT );
        holder.tvItems.setText( countryEntity.getName() );
        holder.tvItems.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//

                final CountryEntity countryEntity = mCountryEntityList.get( holder.getAdapterPosition() );
                countryEntity.setSelected( !countryEntity.isSelected() );
                holder.tvItems.setBackgroundColor( countryEntity.isSelected() ? Color.TRANSPARENT : Color.LTGRAY );
                notifyItemChanged( holder.getAdapterPosition() );

                if (mCallback != null) {
                    mCallback.onItemSelected( countryEntity );
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        if (mCountryEntityList.size() > 5) {
            return 5;
        } else {
            return mCountryEntityList.size();
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvItems;
        private View view;

        public MyViewHolder(View itemView) {
            super( itemView );
            view = itemView;
            tvItems = itemView.findViewById( R.id.txtView );
        }

        @Override
        public void onClick(View v) {

        }


    }


}