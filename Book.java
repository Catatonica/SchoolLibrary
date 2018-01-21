import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Book extends Literature{
	
	Book(String name, String category, String language) {
		super(name, category, language);
	}

	private String author;
	private int year;
	
	//регулярные выражения для проверки входных д на наличие букв латинского и кирилл алф
	//getters and setters as package

	String getAuthor() {
		if(author==null){
			author="undefined";
		}
		return author;
	}

	void setAuthor(String author) {
		Pattern p = Pattern.compile("[a-zA-Z]+[\\.]*[\\s]*|[a-zA-Z]+[\\.]*[\\s]*[a-zA-Z]+[\\.]*[\\s]*|[a-zA-Z]+[\\.]*[\\s]*[a-zA-Z]+[\\.]*[\\s]*[a-zA-Z]+[\\.]*[\\s]*");
		Matcher m = p.matcher(author);
		if(!m.matches()){
			author="undefined";
		}
		this.author = author;
	}

	String getYear() {
		String yearString=String.valueOf(year);
		if(year==-1||year==0){
			yearString="undefined";
		}
		return yearString;
	}

	void setYear(int year) {
		if(year<1600||year>2018){
			year=-1;
		}
		this.year = year;
	}
	
}
