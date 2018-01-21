import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



class Journal extends Literature{
	
	private String frequency;
	private String publicationHistory;
	
	enum Frequency{weekly, monthly};

	Journal(String name, String category, String language) {
		super(name, category, language);
	}

	private LocalDate date;

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

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		boolean bool=false;
		for(Frequency p: Frequency.values()){	
			if(frequency.trim().equalsIgnoreCase(p.name())){
				bool=true;
				frequency=p.name();
				break;
			}
		}
		if(!bool){
			frequency="–";
		}
		this.frequency = frequency;
	}

	public String getPublicationHistory() {
		return publicationHistory;
	}

	public void setPublicationHistory(String publicationHistory) {
		Pattern p = Pattern.compile("[a-zA-Z0-9]+[\\-]*[\\s]*|[a-zA-Z0-9]+[\\-]*[\\s]*[a-zA-Z0-9]+[\\-]*[\\s]*|[a-zA-Z0-9]+[\\-]*[\\s]*[a-zA-Z0-9]+[\\-]*[\\s]*[a-zA-Z0-9]+[\\s]*");
		Matcher m = p.matcher(publicationHistory);
		if(!m.matches()){
			publicationHistory="–";
		}
		this.publicationHistory = publicationHistory;
	}
	
	
}
