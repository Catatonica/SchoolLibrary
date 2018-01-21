import java.time.LocalDate;


class Newspaper extends Literature {
	
	private String format;
	private String published;
	
	enum Published {daily, monday, weekly, monthly, sunday, weekdays,  wednesday, thursday, friday, saturday};
	enum Format {tabloid, broadsheet, compact};
	
	Newspaper(String name, String category, String language) {
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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		boolean bool=false;
		for(Format p: Format.values()){	
			if(format.trim().equalsIgnoreCase(p.name())){
				bool=true;
				format=p.name();
				break;
			}
		}
		if(!bool){
			format="undefined";
		}
		this.format = format;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		boolean bool=false;
		for(Published p: Published.values()){	
			if(published.trim().equalsIgnoreCase(p.name())){
				bool=true;
				published=p.name();
				break;
			}
		}
		if(!bool){
			published="no name";
		}
		this.published = published;
	}
	
}
