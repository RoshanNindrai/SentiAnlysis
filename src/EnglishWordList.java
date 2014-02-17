import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public enum EnglishWordList implements IWordList {
	
	INSTANCE();
	
	public HashMap<String, Word>words = new HashMap<String, Word>();
	public HashMap<String, Double>stop_words = new HashMap<String, Double>();
	public HashMap<String, Double>boast_words = new HashMap<String, Double>();
	public HashMap<String, Double>conjuction_words = new HashMap<String, Double>();
	public HashMap<String, Word>noun_words = new HashMap<String, Word>();
	private static final double POSITIVE =  1.0;
	private static final double NEGATIVE = -1.0;
	private static final double NEUTRAL  =  0.0;
	
	@Override
	public boolean addPositiveWords(String filepath){
	
		File file = new File(filepath);
		 try {

			Scanner scan = new Scanner(file);
			
			while (scan.hasNextLine()) {
		        
				String word = scan.nextLine();
				
				words.put(word, new Word(word, POSITIVE));
		            
		      }
		      scan.close();
		      return true;
		      
		    } 
		    catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
		return false;
	}

	@Override
	public boolean addNegativeWords(String filepath) {
		// TODO Auto-generated method stub
		File file = new File(filepath);
		 try {

			Scanner scan = new Scanner(file);
			
			while (scan.hasNextLine()) {
		        
				String word = scan.nextLine();
				
				words.put(word, new Word(word, NEGATIVE));
		            
		      }
		      scan.close();
		      return true;
		      
		    } 
		    catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
		return false;
		
	}

	@Override
	public boolean addStopWords(String filepath) {
		File file = new File(filepath);
		 try {

			Scanner scan = new Scanner(file);
			
			while (scan.hasNextLine()) {
		        
				stop_words.put(scan.nextLine(), NEUTRAL);
		            
		      }
		      scan.close();
		      return true;
		      
		    } 
		    catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
		return false;
	}

	@Override
	public ArrayList<String> filterStopWords(ArrayList<String> words) {
		
		for(Iterator<String> iter = words.iterator(); iter.hasNext();){
			
			String word = iter.next();
			if(this.stop_words.containsKey(word.toLowerCase())){
					iter.remove();
			}
			
		}
		return words;
	}

	public void updateListPolarity(double polarity, ArrayList<String>words){
		
		for(String word : words){
			
			String word_lowercase = word.toLowerCase();
			if(this.words.containsKey(word_lowercase)){
				
				Word input_word = this.words.get(word_lowercase);
				input_word.updatePolarity(polarity);
			
			}
			if(this.noun_words.containsKey(word_lowercase)){
				
				Word input_word = this.noun_words.get(word_lowercase);
				input_word.updatePolarity(polarity);
			
			}
			
			System.out.println(this.noun_words);
		}
		
	}

	@Override
	public boolean addBoasterWords(String filepath) {
		File file = new File(filepath);
		 try {

			Scanner scan = new Scanner(file);
			
			while (scan.hasNextLine()) {
		        
				boast_words.put(scan.nextLine(), NEUTRAL);
		            
		      }
		      scan.close();
		      return true;
		      
		    } 
		    catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
		return false;
	}
	
	public boolean addConjuctionWords(String filepath) {
		File file = new File(filepath);
		 try {

			Scanner scan = new Scanner(file);
			
			while (scan.hasNextLine()) {
		        
				conjuction_words.put(scan.nextLine(), NEUTRAL);
		            
		      }
		      scan.close();
		      return true;
		      
		    } 
		    catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
		return false;
	}
	
}
