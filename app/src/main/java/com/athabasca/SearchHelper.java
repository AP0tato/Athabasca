package com.athabasca;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchHelper 
{
    private int[] binarySearch(String[] toSearch, int left, int right, String key) {
        if (toSearch.length == 0) {
            return null; // Key not found.
        }
        if (left> right){
            return null;
        }
        if(key == null ){
            return null;
        }
        int middle = (left+right)/ 2;
        
        if (toSearch[middle].equals(key)) {
            return collect(toSearch, middle, key,left);
        } else if (key.compareTo(toSearch[middle]) > 0) {
            return binarySearch(toSearch, middle+1,right ,key);
        } else {
            
            
                return binarySearch(toSearch,left,middle-1, key);
           
        }
    }
    private int[] collect(String[] toCollect, int index, String key, int offset){
        int[] indexes;
        int start = index;
        int end = index;
        while(start > 0 && toCollect[start-1].equals(key)){
            start --;
        }
        while(end < toCollect.length-1 && toCollect[end+1].equals(key)){
            end++;
        }
        indexes = new int[end-start+1];
        for(int i = 0; i < indexes.length; i++){
            indexes[i] = start + i;
        }
        return indexes;
    }
    private int[] originalIndicesBinary(String[] toSort, String key){
        ArrayList<Map.Entry<String, Integer>> pairedList = new ArrayList<>();
                for (int i = 0; i < toSort.length; i++) {
                    pairedList.add(new AbstractMap.SimpleEntry<>(toSort[i].toLowerCase(), i));
                }
                pairedList.sort(Map.Entry.comparingByKey());
                String[] toSearch = new String[toSort.length];
                for(int i = 0; i < toSort.length; i++){
                    toSearch[i] = pairedList.get(i).getKey();
                }
                int[] foundIndexes = binarySearch(toSearch,0,toSearch.length-1, key);
                if(foundIndexes == null){
                    return null;
                }
                ArrayList<Integer> originalIndices = new ArrayList<>();
                for (int index : foundIndexes) {
                    originalIndices.add(pairedList.get(index).getValue());
                }
                // Convert the original indices list to an array.
                return originalIndices.stream().mapToInt(i -> i).toArray();
    }
}
