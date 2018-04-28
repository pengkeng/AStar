package com.example.pqc.a;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pqc.a.As.Astar;
import com.example.pqc.a.As.MapInfo;
import com.example.pqc.a.As.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.pqc.a.R.id.button_fragment;

public class Map_Fragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<Integer> mDatas;
    private HomeAdapter homeAdapter;
    public Node startnode ;
    public Node endnode ;
    public boolean isstart=false;
    public boolean isend=false;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map_, container, false);
        initData();
        refresh( mDatas );
        return view;
    }

    protected void refresh(List<Integer> mDatas){
        View v = view.findViewById( R.id.map_fragement );
        mRecyclerView = v.findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager( new GridLayoutManager( getContext(),10 ));
        homeAdapter = new HomeAdapter( mDatas);
        mRecyclerView.setAdapter(homeAdapter);
    }

    protected void initData()
    {
        ButtonFragment buttonFragment = (ButtonFragment)getFragmentManager().findFragmentById(R.id.button_fragment ) ;

        buttonFragment.initData();

        mDatas = new ArrayList<>() ;
        for(int i = 0;i < 10;i++){
            for (int j = 0; j < 10; j++) {
                mDatas.add(buttonFragment.maps[i][j]);
            }
        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view =  LayoutInflater.from( parent.getContext()).inflate(R.layout.grid,parent,false);
            final MyViewHolder holder = new MyViewHolder( view );
            return holder;
        }
        @Override
        public void onBindViewHolder(@NonNull final HomeAdapter.MyViewHolder holder, int position) {
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
                        startnode = new Node( y,x );
                        isstart = true;
                    }
                    else{
                        holder.tv.setBackgroundColor( Color.rgb(0,0,255 ));
                        int x = position/10;
                        int y = position%10;
                        endnode = new Node( y,x );
                        isend = true;
                    }
                }
            } );
        }
        public  HomeAdapter(List<Integer > list){

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
}
