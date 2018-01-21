import java.io.Serializable;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Pupil implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int readBooksNum;
	private LocalDate birthDate;
	
	Pupil(String name, int readBooksNum){
		setName(name);
		setReadBooksNum(readBooksNum);
	}
	
	String getName() {
		return name;
	}
	
	void setName(String name) {
		Pattern p = Pattern.compile("[a-zA-Z]+[\\.]*[\\s]*|[a-zA-Z]+[\\.]*[\\s]*[a-zA-Z]+[\\.]*[\\s]*|[a-zA-Z]+[\\.]*[\\s]*[a-zA-Z]+[\\.]*[\\s]*[a-zA-Z]+[\\.]*[\\s]*");
		Matcher m = p.matcher(name);
		if(!m.matches()){
			name="no name";
		}
		this.name = name;
	}
	
	int getReadBooksNum() {
		return readBooksNum;
	}
	
	void setReadBooksNum(int readBooksNum) {
		if(readBooksNum<0){
			readBooksNum=0;
		}
		this.readBooksNum = readBooksNum;
	}
	
	LocalDate getBirthDate() {
		return birthDate;
	}
	
	void setBirthDate(LocalDate birthDate) {
		if(birthDate==null){
			
		}
		this.birthDate = birthDate;
	}
}
