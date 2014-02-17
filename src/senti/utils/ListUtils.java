package senti.utils;
import java.util.ArrayList;

public class ListUtils {

	
	public static ArrayList<String> extractElementsForN(ArrayList<String> words_list, int nCount){
		
		if(words_list.size() <=1 || nCount == 1){
			
			return words_list;
			
		}
		
		if(words_list.size() < nCount){
			
			nCount = words_list.size();
			
		}
		
		int	iteration_count = (words_list.size() == nCount) ? 1 : ((words_list.size() - nCount) + 1);
		int start_index = 0, end_index = nCount;
		ArrayList<String> temp_list = new ArrayList<String>();
		for(int index = 0 ; index < iteration_count; index++){
			temp_list.add(StringUtils.extractStringFromList(words_list.subList(start_index, end_index)));
			start_index ++; end_index ++; 
		}
		
		return temp_list;
	
	}
	
	
	
}
