package com.example.pqc.a;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pqc.a.As.Astar;
import com.example.pqc.a.As.MapInfo;
import com.example.pqc.a.As.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ButtonFragment extends Fragment {
    private   Button startbutton ;
    private   Button rebutton ;
    private TextView textView;
    protected int[][] maps;
    protected int[][] map = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };;
    private MapInfo mapInfo;
    private Node startnode ;
    private Node endnode ;
    private int weight;
    private int hight;
    private List<Integer> mDatas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_button, container, false);
        startbutton = view.findViewById( R.id.start);
        rebutton = view.findViewById( R.id.restart);
        textView = view.findViewById( R.id.runtime ) ;

        startbutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Astar astar = new Astar();

                Map_Fragment map_fragment= (Map_Fragment)getFragmentManager().findFragmentById(R.id.map_fragement ) ;
                endnode = map_fragment.endnode;
                startnode = map_fragment.startnode;
                if(endnode==null||startnode==null)
                    Toast.makeText( v.getContext(),"请选择起点和终点",Toast.LENGTH_LONG ).show();
                else {
                mapInfo = new MapInfo( maps,weight,hight,startnode,endnode );
                long time = astar.start(mapInfo);
                mDatas = new ArrayList<>() ;
                for(int i = 0;i < 10;i++){
                    for (int j = 0; j < 10; j++) {
                        mDatas.add(maps[i][j]);
                    }
                }
                map_fragment.refresh( mDatas );
                textView.setText( time +" ms");}

            }
        } );
        rebutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map_Fragment map_fragment = (Map_Fragment)getFragmentManager().findFragmentById( R.id.map_fragement );
                map_fragment.startnode =null;
                map_fragment.endnode = null;
                map_fragment.isstart =false;
                map_fragment.isend =false;
                mDatas = new ArrayList<>();

                for(int i = 0;i < 10;i++){
                    for (int j = 0; j < 10; j++) {
                        maps[i][j] = map[i][j];
                        mDatas.add(maps[i][j]);
                    }
                }
                map_fragment.refresh( mDatas );
                textView.setText( 0 +" ms");

            }
        } );
        return  view;
    }
    protected void initData()
    {
        maps = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        weight = 10;
        hight = 10;
    }
}
