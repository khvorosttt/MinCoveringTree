/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mincoveringtree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Tops {
    private int number;
    private int label;
    boolean visited;
    List<Ribs> ribs;
    public Tops(int number){
        this.number=number;
        this.label=Integer.MAX_VALUE/2;
        this.visited=false;
        this.ribs=new ArrayList<>();
    }
    public Tops(int number, List<Ribs> ribs){
        this.number=number;
        this.label=Integer.MAX_VALUE/2;
        this.visited=false;
        this.ribs=ribs;
    }
    public int Get_label(){
        return label;
    }
    public int Get_number(){
        return number;
    }
    public void Set_label(int label){
        this.label=label;
    }
    public boolean isVisited(){
        return visited;
    }
    public void setVisited(boolean visited){
        this.visited=visited;
    }
    public void AddRibs(Ribs r){
        this.ribs.add(r);
    }
    public List<Ribs> Ribs(){
        return ribs;
    }
}
