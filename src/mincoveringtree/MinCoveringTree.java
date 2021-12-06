/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mincoveringtree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class MinCoveringTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        List<Ribs> ribs = new ArrayList<>();
        List<Tops> tops = new ArrayList<>();
        IN(ribs, tops);
        int[][] Matrix = new int[tops.size()][tops.size()];
        for (int i = 0; i < tops.size(); i++) {
            List<Tops> temp = Copy(tops);
            Adjacent_1(i, ribs, temp);
            showTops(temp);
            System.out.println();
            InMatrix(i, temp, Matrix);
        }
        show(Matrix);
        int answer = Find(Matrix);
        System.out.println("Пункт скорой помощи необходимо расположить в районе " + answer);

    }

    public static void InMatrix(int i, List<Tops> tops, int[][] Matrix) {
        for (Tops t : tops) {
            Matrix[i][t.Get_number()] = t.Get_label();
        }
    }

    public static List<Tops> Copy(List<Tops> tops) {
        List<Tops> temp = new ArrayList<>();
        for (Tops t : tops) {
            temp.add(new Tops(t.Get_number(), t.Ribs()));
        }
        return temp;
    }

    //Метод заполнения ребёр 
    public static void IN(List<Ribs> ribs, List<Tops> tops) throws IOException {
        BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "\\Data.txt")));
        String[] data_Power = fin.readLine().split(" ");
        for (int i = 0; i < Integer.parseInt(data_Power[0]); i++) {
            tops.add(new Tops(i));
        }
        for (int i = 0; i < Integer.parseInt(data_Power[1]); i++) {
            String[] data = fin.readLine().split(" ");
            ribs.add(new Ribs(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
        }
        for (int i = 0; i < tops.size(); i++) {
            for (Ribs b : ribs) {
                if (tops.get(i).Get_number() == b.Out()) {
                    tops.get(i).AddRibs(b);
                }
            }
        }
    }

    public static void show(int[][] Matrix) {
        for (int i = 0; i < Matrix.length - 1; i++) {
            for (int j = 0; j < Matrix[0].length - 1; j++) {
                System.out.print(Matrix[i][j] + " ");
            }
            System.out.print(Matrix[i][Matrix[0].length - 1] + " ");
            System.out.println();
        }
        for (int j = 0; j < Matrix[0].length - 1; j++) {
            System.out.print(Matrix[Matrix.length - 1][j] + " ");
        }
        System.out.print(Matrix[Matrix.length - 1][Matrix[0].length - 1] + " ");
        System.out.println();
    }

    public static void Adjacent_1(int index, List<Ribs> ribs, List<Tops> tops) {
        tops.get(index).Set_label(0);
        for (int e = 0; e < tops.size(); e++) {
            for (int i = 0; i < tops.size(); i++) {
                if (!tops.get(i).isVisited()) {
                    for (int j = 0; j < tops.get(i).Ribs().size(); j++) {
                        if (Integer.MAX_VALUE > tops.get(i).Get_label()) {
                            int A = tops.get(i).Ribs().get(j).Weight() + tops.get(i).Get_label();
                            for (int k = 0; k < tops.size(); k++) {
                                if (tops.get(i).Ribs().get(j).In() == tops.get(k).Get_number()) {
                                    if (tops.get(k).Get_label() > A) {
                                        tops.get(k).Set_label(A);
                                    }
                                }

                            }
                        }
                    }

                }

            }
            tops.get(e).setVisited(true);
        }
    }

    //Метод показа вершин и их нагрузок
    public static void showTops(List<Tops> tops_result) {
        for (Tops t : tops_result) {
            System.out.println(t.Get_number() + " " + t.Get_label());
        }
    }

    public static int[] Max_way(int[][] Matrix) {
        int[] M_matrix = new int[Matrix.length];
        for (int i = 0; i < M_matrix.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < Matrix[0].length; j++) {
                if (Matrix[i][j] != Integer.MAX_VALUE / 2 && Matrix[j][i] != Integer.MAX_VALUE / 2) {
                    if (max < Matrix[i][j] + Matrix[j][i]) {
                        max = Matrix[i][j] + Matrix[j][i];
                    }
                } else {
                    if (Matrix[i][j] == Integer.MAX_VALUE / 2 && Matrix[j][i] == Integer.MAX_VALUE / 2) {
                        max = Integer.MAX_VALUE / 2;
                    } else {
                        if (Matrix[i][j] != Integer.MAX_VALUE / 2) {
                            if (max < Matrix[i][j] * 2) {
                                max = Matrix[i][j] * 2;
                            }
                        } else {
                            if (max < Matrix[j][i] * 2) {
                                max = Matrix[j][i] * 2;
                            }
                        }
                    }
                }

            }
            M_matrix[i] = max;
        }
        return M_matrix;
    }

    public static int[] Max_wayFirst(int[][] Matrix) {
        int[] f_matrix = new int[Matrix.length];
        for (int i = 0; i < f_matrix.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < Matrix[0].length; j++) {
                if (Matrix[i][j] != Integer.MAX_VALUE / 2) {
                    if (max < Matrix[i][j]) {
                        max = Matrix[i][j];
                    }
                }else{
                    if(max<Matrix[j][i]){
                        max=Matrix[j][i];
                    }
                }
            }
            f_matrix[i] = max;
        }
        return f_matrix;
    }

    public static int Find(int[][] Matrix) {
        int[] M_temp = Max_way(Matrix);
        int[] f_temp = Max_wayFirst(Matrix);
        int min = Integer.MAX_VALUE;
        int min_i = -1;
        for (int i = 0; i < M_temp.length; i++) {
            if (min > M_temp[i]) {
                min = M_temp[i];
                min_i = i;
            }
            if (min == M_temp[i]) {
                if (f_temp[min_i] > f_temp[i]) {
                    min_i = i;
                }
            }
        }
        return min_i;
    }

}
