package senti.utils;

import java.util.List;

public class StringUtils {
	
	public static  String filterPunctuations(final String input_string){
		
		String temp = input_string;
		temp = temp.replaceAll("[^a-zA-Z]+$", "");
		return (temp.length() == 0)? input_string : temp;
		
	}
	
	public static String extractStringFromList(List<String> words_list){
		
		 String _literal = "";
		
		for(String word : words_list){
			
			_literal += filterPunctuations(word.toLowerCase())+" ";
			
		}	
		
		return _literal;
		
	}
	
}
