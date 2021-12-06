/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mincoveringtree;

/**
 *
 * @author Lenovo
 */
public class Ribs implements Comparable<Ribs>{
    private int Tops_out;
    private int Tops_in;
    private int weight;
    public Ribs(int Tops_out, int Tops_in, int weight){
        this.Tops_out=Tops_out;
        this.Tops_in=Tops_in;
        this.weight=weight;
    }
    public int Out(){
        return Tops_out;
    }
    public int In(){
        return Tops_in;
    }
    public int Weight(){
        return weight;
    }

    @Override
    public int compareTo(Ribs o) {
        return o.Weight() - this.Weight();
    }
}
