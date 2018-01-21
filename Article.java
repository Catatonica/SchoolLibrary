import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Article extends Literature{
	
	Article(String name, String category, String language) {
		super(name, category, language);
	}

	private String resource;
	private LocalDate date;

	String getResource() {
		return resource;
	}

	void setResource(String resource) {
		Pattern p = Pattern.compile("[a-zA-Z]+[\\s]*|[a-zA-Z]+[\\s]*[a-zA-Z]+[\\s]*|[a-zA-Z]+[\\s]*[a-zA-Z]+[\\s]*[a-zA-Z]+[\\s]*");
			
		Matcher m = p.matcher(resource);
		if(!m.matches()){
			resource="undefined";
		}
		this.resource = resource;
	}

	String getDate() {
		String stringDate=String.valueOf(date);
		if(date==null){
			stringDate="undefined";
		}
		return stringDate;
	}

	void setDate(LocalDate date) {
		this.date = date;
	}
	
}
