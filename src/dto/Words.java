package dto;

public class Words {
	private String word_id;
	private String word;
	private String meaning;
	private String sentence;
	private String record_date;
	
	public String getWord_id() {
		return word_id;
	}
	public void setWord_id(String word_id) {
		this.word_id = word_id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public String getRecord_date() {
		return record_date;
	}
	public void setRecord_date(String record_date) {
		this.record_date = record_date;
	}
}
