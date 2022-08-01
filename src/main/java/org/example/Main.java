package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       int a =5;
       boolean result = (a < 10) | ((a=10) <20);
        System.out.println(a+", "+result);


        boolean b = false;
       // int i = 4;
        do {
       //     i++ ;
        } while (b = !b);
     //   System.out.println( i );

        int count = 0, sum = 0;
        do {
            if(count % 4 == 0) continue;
            sum+=count;
        }
        while(count++ < 10);
        System.out.println(sum);


       // Integer[] arr = {1, 2, 3, 4};
       // List<Integer> list = Arrays.asList(1, 2, 3, 4);
      //  List<Integer> list2 = new ArrayList<>(list);
     //   Integer[] arr2 = new Integer[4];
     //   arr2 = list2.toArray(arr2);
      //  list2.add(0, 5);
      //  System.out.println(Arrays.toString(arr2));


        List<String> listaa = new ArrayList<>();
        listaa.add("1");
        listaa.add("2");
        listaa.add("3");
        listaa.add("3");
        int n = listaa.size();
        for(int i=0; i<listaa.size(); i++){
            if(listaa.get(i).contains("3")){
                listaa.remove(i);
            }
        }
        System.out.println(listaa);


        Integer[] arr  = {1, 2, 3, 4};
        List<Integer> list = Arrays.asList(arr);
        arr[0] = 5;
        System.out.println(list);

    }
}
