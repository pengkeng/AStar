package com.example.pqc.a.As;

public class Coord {
    public int x;
    public int y;
    public Coord(int x,int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public  boolean equals(Object object){
        if(object == null) return  false;
        if(object instanceof Coord){
            Coord c = (Coord)object;
            return  x == c.x && y ==c.y;
        }
        return  false;
    }
}
