import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import senti.utils.*;


public class Analyser {

	public EnglishWordList englishDictionary = EnglishWordList.INSTANCE;
	
	public void addWordList(String language, String positive_path, String negative_path, 
			String stop_path, String boast_path, String conjunction_path){
		
		if(language.equals("English")){
			
			englishDictionary.addPositiveWords(positive_path);
			englishDictionary.addNegativeWords(negative_path);
			englishDictionary.addStopWords(stop_path);
			englishDictionary.addBoasterWords(boast_path);
			englishDictionary.addConjuctionWords(conjunction_path);
		
		}
		
	}
	
	public String getSentiment(String message){
	
		ArrayList<String> words_tofilter = new ArrayList<String>();
		String[] words = message.split(" ");
		Collections.addAll(words_tofilter, words); 
		if(this.prepareWords(words_tofilter) > 0.0){
			
			return "Positive";
			
		}
		else if(this.prepareWords(words_tofilter) < 0.0){
			
			return "Negative";
			
		}
		else{
			
			return "Neutral";
			
		}
		
	}
	
	public double prepareWords(ArrayList<String> words){
		
		ArrayList<String> filtered_words = this.englishDictionary.filterStopWords(words);
		double unigram_score = assignNgramScores(filtered_words, 1, 0);
		double bigram_score = assignNgramScores(filtered_words, 2, unigram_score);
		double final_score = (Math.abs(unigram_score) >= Math.abs(bigram_score)) ? unigram_score : bigram_score;
		this.englishDictionary.updateListPolarity(final_score, words);
		return final_score;
	}
	
	public double assignNgramScores(ArrayList<String> _words, int nCount, double base_score){
		
		double total_sent_score = 0.0;
		double calculated_score = 0.0;
		boolean boast = false;
		double multiplier = 1.0;
		//if empty word return 0.0;
		if(_words.size() == 0)
			return 0.0;
		ArrayList<String> words = ListUtils.extractElementsForN(_words, nCount);
		
		for(String word : words){
			
			String word_lowercase = word.toLowerCase(); 
			word_lowercase = StringUtils.filterPunctuations(word_lowercase);
			if(this.englishDictionary.conjuction_words.containsKey(word_lowercase))
				total_sent_score *= .8;
			if(boast){multiplier *= 2;}
			boast = this.englishDictionary.boast_words.containsKey(word_lowercase);
			if(boast){continue;}
			if(this.englishDictionary.words.containsKey(word_lowercase)){
				
				Word input_word = this.englishDictionary.words.get(word_lowercase);
				System.out.println(word+" positive : "+input_word.getPositive_context());
				System.out.println(word+" Negative : "+input_word.getNegative_context());
				total_sent_score += multiplier * input_word.getPolarity();
				boast = false;
				multiplier = 1.0;
			}
			else{
				
				this.englishDictionary.noun_words.put(word_lowercase, new Word(word_lowercase, base_score));
				
			}
			
		}
		
	    calculated_score = ((total_sent_score / words.size()) == 0) ? base_score : (total_sent_score / words.size());
		return calculated_score;
	}
	
	public static void main(String[] args){
		
		Analyser analyser = new Analyser();
		analyser.addWordList("English","positive-words.txt", "negative-words.txt", "english-stop.txt", "boasterwords.txt", "conjuction.txt");
		Scanner scan = new Scanner(System.in);
		while(true){
			System.out.println("enter your sentence to check its sentiment");
			System.out.println(analyser.getSentiment(scan.nextLine()));
		}
		
	}
	
}
