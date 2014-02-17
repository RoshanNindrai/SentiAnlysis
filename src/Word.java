
public class Word {
	
	//the actual word as string
	private String content;
	//contains the overall polarity of the word
	private Double polarity = 0.0;
	// # of document where the word is used in positive context
	private Double positive_context = 0.0;
	// # of document where the word is used in negative context 
	private Double negative_context = 0.0;
	//total # of documents where the word is used
	private int total_occurance = 0; 
	//list of symbols to be omitted
	//private String symbols = "[{!@#$%^&*()_+-=|}]':;?/>.<,";
	
	public Word(String _content, Double _polarity){
		
		this.content = _content;
	    this.updatePolarity(_polarity);
		
	}
	
	public Double getPolarity() {
		return polarity;
	}

	public void setPolarity(Double polarity) {
		this.polarity = polarity;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getPositive_context() {
		return positive_context;
	}

	public void setPositive_context(Double positive_context) {
		this.positive_context = positive_context;
	}

	public Double getNegative_context() {
		return negative_context;
	}

	public void setNegative_context(Double negative_context) {
		this.negative_context = negative_context;
	}

	public int getTotal_occurance() {
		return total_occurance;
	}

	public void setTotal_occurance(int total_occurance) {
		this.total_occurance = total_occurance;
	}
	
	public void updatePolarity(double polarity){
		
		this.total_occurance++;
		
		if(polarity > 0)
			this.positive_context++;
		else if(polarity < 0)
			this.negative_context++;
		
		double positive_probability = (this.positive_context  / this.total_occurance);
		double negative_probability = (this.negative_context  / this.total_occurance);
		
		this.polarity = ( positive_probability > negative_probability)? positive_probability : -negative_probability;
		
	}
	
	public String toString(){
		
		return this.content+"- Total Polarity : "+this.polarity+" , # of positive messages : "+this.positive_context+" , # of negative messages : "+this.negative_context+" , # of total messages "+this.total_occurance;
		
	}
	
}
