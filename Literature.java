import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Literature {
	
	private String name;
	private String category;
	private String language;
	
	Literature(String name, String category, String language){
		setName(name);
		setCategory(category);
		setLanguage(language);
	}
	
	String getName() {
		return name;
	}
	
	void setName(String name) {
		name=String.format("%s"+name+"%s", "\"", "\"");
		this.name = name;
	}
	
	String getCategory() {
		return category;
	}
	
	void setCategory(String category) {
		Pattern p = Pattern.compile("[a-zA-Z]+[\\,]*[\\s]*|[a-zA-Z]+[\\,]*[\\s]*[a-zA-Z]+[\\,]*[\\s]*|[a-zA-Z]+[\\,]*[\\s]*[a-zA-Z]+[\\,]*[\\s]*[a-zA-Z]+[\\,]*[\\s]*");
		Matcher m = p.matcher(category);
		if(!m.matches()){
			category="no name";
		}
		this.category = category;
	}
	
	String getLanguage() {
		return language;
	}
	
	void setLanguage(String language) {
		Pattern p = Pattern.compile("[a-zA-Z]+[\\s]*|[a-zA-Z]+[\\s]*[a-zA-Z]+[\\s]*|[a-zA-Z]+[\\s]*[a-zA-Z]+[\\s]*[a-zA-Z]+[\\s]*");
		Matcher m = p.matcher(language);
		if(!m.matches()){
			language="no name";
		}
		this.language = language;
	}
}
