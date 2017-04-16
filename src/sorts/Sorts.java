/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorts;

import java.util.Random;

/**
 *
 * @author gmaal
 */
public class Sorts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] a = fillArray(50000000, 10000);
        System.out.println("Array fertisch");
//        System.out.println("unsorted:");
//        System.out.print("|");
//        for (int i = 0; i < a.length; i++) {
//            System.out.print(a[i] + "|");
//        }
//        System.out.println("");
//        SeqMergeSort sorter = new SeqMergeSort(a);
        ParMergeSort sorter = new ParMergeSort(a);
        int[] sorted = sorter.sort();
//        System.out.println("sorted:");
//        System.out.print("|");
//        for (int i = 0; i < sorted.length; i += 10000) {
//            System.out.print(sorted[i] + "|");
//        }
    }

    private static int[] fillArray(int size, int max) {
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = new Random().nextInt(max + 1);
        }
        return a;
    }

}
