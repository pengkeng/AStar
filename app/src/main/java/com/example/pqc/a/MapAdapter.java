package com.example.pqc.a;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pqc.a.As.Node;

import java.util.List;

class MapAdapter extends RecyclerView.Adapter<MapAdapter.MyViewHolder>{



    private List<Integer> mDatas;
    public boolean isstart=false;
    public boolean isend=false;
    public Node startnode ;
    public Node endnode ;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from( parent.getContext()).inflate(R.layout.grid,parent,false);
        final MyViewHolder  holder = new MyViewHolder( view );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position)+"");
        if(mDatas.get( position)==1)
        holder.tv.setBackgroundColor( Color.rgb(0, 0, 0) );
        if(mDatas.get( position)==2)
            holder.tv.setBackgroundColor( Color.rgb(255, 0, 0) );
        holder.tv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                if (isstart==false){
                    holder.tv.setBackgroundColor( Color.rgb(0,255,0 ));
                    int x = position/10;
                    int y = position%10;
                    startnode = new Node( x,y );
                    isstart = true;
                }
                else{
                    holder.tv.setBackgroundColor( Color.rgb(0,0,255 ));
                    int x = position/10;
                    int y = position%10;
                    endnode = new Node( x,y );
                    isend = true;
                }
            }
        } );
    }
    public MapAdapter(List<Integer > list){

        mDatas = list;

    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View view)
        {
            super(view);
            tv = view.findViewById(R.id.grid_text);

        }
    }
}
