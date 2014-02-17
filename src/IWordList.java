import java.util.ArrayList;

/**
 * This interface is used to define the functions that is to be
 * added to all the future word list of different language
 */

/**
 * @author roshanbalaji
 *
 */
public interface IWordList {
	
	//to add a list of positive words to the word list from a .txt file
	public boolean addPositiveWords(String filepath);
	//to add a list of negative words to the word list from a .txt file
	public boolean addNegativeWords(String filepath);
	//to add stop words to the word list
	public boolean addStopWords(String filepath);
	//add boaster words
	public boolean addBoasterWords(String filepath);
	//filter stop words
	public ArrayList<String> filterStopWords(ArrayList<String> words);

}
