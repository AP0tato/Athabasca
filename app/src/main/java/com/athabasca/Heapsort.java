package com.athabasca;

import java.util.List;

public class Heapsort 
{
    // Time complexity of O(nlogn)
    public static List<String> sort(List<String> list)
    {
        int n = list.size();

        for(int i = n/2-1; i >= 0; i--) // Loops n/2 times, where n is the size of the list -> O(n/2)
            list = heapify(list, i, n);

        for(int i = n-1; i >= 0; i--) // Loop n times where n is the size of the list -> O(n)
        {
            String swap = list.get(0);
            list.set(0, list.get(i));
            list.set(i, swap);
            heapify(list, 0, i);
        }

        return list;
    }

    private static List<String> heapify(List<String> list, int index, int size)
    {
        int largest = index;
        int left = 2*index+1;
        int right = 2*index+2;

        if(left<size && list.get(left).compareTo(list.get(largest))>0)
            largest = left;
        
        if(right<size && list.get(right).compareTo(list.get(largest))>0)
            largest = right;
            
        if(largest != index) 
        {
            String swap = list.get(largest);
            list.set(largest, list.get(index));
            list.set(index, swap);
            return heapify(list, largest, size);
        }

        return list;
    }
}
