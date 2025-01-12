import java.util.ArrayList;
import java.util.List;

public class SearchHelper 
{
    private static List<Integer> found_indices = new ArrayList<Integer>();
    public static List<Integer> BinSearch(List<String> list, int right, int left, String elem)
    {
        if(list.size()==0||left>=right)
            return null;

        int mid = (right+left)/2;
        String a = list.get(mid);

        if(a.equals(elem))
        {
            found_indices.add(mid);
            BinSearch(list, mid+1, left, elem);
            BinSearch(list, right, mid-1, elem);
        }
        else if(a.compareTo(elem)>0)
        {
            return BinSearch(list, left, mid-1, elem);
        }
        else
        {
            return BinSearch(list, mid+1, right, elem);
        }
        
        return found_indices;
    }
}
