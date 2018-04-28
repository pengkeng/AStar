package com.example.pqc.a.As;

import java.security.PublicKey;

public class MapInfo {

    public  int [][] maps;
    public  int width;
    public  int hight;
    public  Node start;
    public  Node end;
    public MapInfo(int[][] maps, int width, int hight, Node start, Node end)
    {
        this.maps = maps;
        this.width = width;
        this.hight = hight;
        this.start = start;
        this.end = end;
    }
}
