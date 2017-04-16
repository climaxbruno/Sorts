/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorts;

/**
 *
 * @author gmaal
 */
public class SeqMergeSort {

    private final int[] source;
    private int[] temp;

    public SeqMergeSort(int[] a) {
        this.source = a;
    }

    private void copyValues(int[] org, int[] out, int start, int end) {
        for (int i = start; i <= end; i++) {
            out[i] = org[i];
        }
    }

    public int[] sort() {
        this.temp = new int[source.length];
        split(0, this.source.length - 1);
        return this.source;
    }

    private void split(int start, int end) {
        if (start < end) {
            int mid = (end - start) / 2 + start;
            split(start, mid);
            split(mid + 1, end);
            merge(start, mid, end);
        }
    }

    private void merge(int start, int mid, int end) {
        int lpos = start;
        int rpos = mid + 1;
        int i = start;
        copyValues(this.source, this.temp, start, end);
        while (lpos <= mid && rpos <= end) {
            if (this.temp[lpos] <= this.temp[rpos]) {
                this.source[i] = this.temp[lpos];
                lpos++;
            } else {
                this.source[i] = this.temp[rpos];
                rpos++;
            }
            i++;
        }
        while (lpos <= mid) {
            this.source[i] = this.temp[lpos];
            i++;
            lpos++;
        }
    }

}
