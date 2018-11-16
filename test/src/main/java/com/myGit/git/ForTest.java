package com.myGit.git;

import org.junit.Test;

public class ForTest {
    @Test
    public void fun01(){
        int[] arr={5,6,1,2,3,4,9,7,8,0};
        System.out.print("排序前顺序:");
        for (int i : arr) {
            System.out.print(i);
        }
        System.out.println();
        pubboSort(arr);
        System.out.print("排序后顺序:");
        for (int i : arr) {
            System.out.print(i);
        }
    }
    //冒泡排序
    public void pubboSort(int[] arr){
        int temp = 0;
        for (int i = 0; i<arr.length-1;i++){
            for (int j = 0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
