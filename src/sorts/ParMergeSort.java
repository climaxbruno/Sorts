/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorts;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author gmaal
 */
public class ParMergeSort {

    private final int[] source;
    private int[] temp;

    public ParMergeSort(int[] a) {
        this.source = a;
    }

    private void copyValues(int[] org, int[] out, int start, int end) {
        for (int i = start; i <= end; i++) {
            out[i] = org[i];
        }
    }

    public int[] sort() {
        this.temp = new int[source.length];
        ForkJoinPool fjp = new ForkJoinPool();
        Split split = new Split(0, this.source.length - 1);
        fjp.invoke(split);
        return this.source;
    }

    class Split extends RecursiveAction {

        int start;
        int end;

        Split(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (this.start < this.end) {
                int mid = (end - start) / 2 + start;
                Split left = new Split(this.start, mid);
                Split right = new Split(mid + 1, end);
                left.fork();
                right.fork();
                left.join();
                right.join();
                merge(start, mid, end);
            }
        }

        private void merge(int start, int mid, int end) {
            int lpos = start;
            int rpos = mid + 1;
            int i = start;
            copyValues(source, temp, start, end);
            while (lpos <= mid && rpos <= end) {
                if (temp[lpos] <= temp[rpos]) {
                    source[i] = temp[lpos];
                    lpos++;
                } else {
                    source[i] = temp[rpos];
                    rpos++;
                }
                i++;
            }
            while (lpos <= mid) {
                source[i] = temp[lpos];
                i++;
                lpos++;
            }
        }

    }

}
