package com.athabasca;
import java.util.ArrayList;
import java.util.List;

public class SearchHelper 
{
    // List to store indices of found elements
    private static List<Integer> found_indices = new ArrayList<Integer>();

    /**
     * Performs a recursive binary search to find all indices of a given element in a sorted list.
     *
     * @param list The sorted list of strings to search.
     * @param left The left boundary of the current search range.
     * @param right The right boundary of the current search range.
     * @param elem The element to search for.
     * @return A list of indices where the element is found, or null if the list is empty or invalid.
     */
    public static List<Integer> BinSearch(List<String> list, int left, int right, String elem)
    {
        // Base case: If the list is empty or the search range is invalid, return null
        if(list.size() == 0 || left >= right)
            return null;

        // Calculate the middle index of the current search range
        int mid = (right + left) / 2;
        String a = list.get(mid);

        // Check if the middle element matches the search element
        if(a.equals(elem))
        {
            // Add the index of the found element to the list
            found_indices.add(mid);
            // Recursively search the right part of the list for duplicates
            BinSearch(list, mid + 1, left, elem);
            // Recursively search the left part of the list for duplicates
            BinSearch(list, right, mid - 1, elem);
        }
        // If the middle element is greater than the search element, search the left half
        else if(a.compareTo(elem) > 0)
        {
            return BinSearch(list, left, mid - 1, elem);
        }
        // If the middle element is less than the search element, search the right half
        else
        {
            return BinSearch(list, mid + 1, right, elem);
        }

        // Return the list of found indices
        return found_indices;
    }
}
