import static java.lang.System.out;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

class SchoolLibraryHelper {
	
	private  ArrayList<Book> booksList;
	private  ArrayList<Journal> journalsList;
	private  ArrayList<Article> articlesList;
	private  ArrayList<Newspaper> newspapersList;
	private  ArrayList<Pupil> pupilsList;


//-----------------------------------------2nd TASK---------------------------------------------	
	void printFirstPupilsList(){
		//if we can get list of pupils(if it exists, not null and contains useful for the 2nd task information ), we get it and then print
		if(getPupilsList(2)){
			out.println("\n\tDo you want to read objects from file .ser?[yes/ath]");
			Scanner scanner=new Scanner(System.in);
			switch(scanner.next()){
				case "yes":
					out.println("\n\t**************** PUPILS WHO HAVE READ MORE THAN 1 BOOK ****************\t"); //2,3,4... books
					printPupilsListFromSerializedFile(2);
					break;
				default:
					out.println("\n\t**************** PUPILS WHO HAVE READ MORE THAN 1 BOOK ****************\t"); //2,3,4... books
					printPupilsList(2);
					break;
			}	
		}
	}	
	

//-----------------------------------------END OF THE 2nd TASK---------------------------------------------	

	
//-----------------------------------------3rd TASK---------------------------------------------	
	void printSecondPupilsList() {
		//if we can get list of pupils(if it exists, not null and contains useful for the 3rd task information ), we get it and then print
		if(getPupilsList(3)){
			out.println("\n\tDo you want to read objects from file .ser?[yes/ath]");
			Scanner scanner=new Scanner(System.in);
			switch(scanner.next()){
			case "yes":
				out.println("\n\t**************** PUPILS WHO HAVE READ LESS THAN OR EQUAL TO 2 BOOK ****************\t"); //0,1,2 books
				printPupilsListFromSerializedFile(3);
				break;
			default:
				out.println("\n\t**************** PUPILS WHO HAVE READ LESS THAN OR EQUAL TO 2 BOOK ****************\t"); //0,1,2 books
				printPupilsList(3);
				break;
		}	
		}
	}
//-----------------------------------------END OF THE 3rd TASK---------------------------------------------	

	
//This method gets all useful(only useful) information for the 2nd/3rd tasks
//return false if it couldn't do it, true - vice versa
	private boolean getPupilsList(int taskNum) {
		pupilsList=new ArrayList<>(); //will contain those pupils, who'll fit the conditions
		
		//trying open file, either FNF-exception throws
			try {
				File pupilsFile=new File("src"+File.separator+"pupils.txt"); //using separator for different OS
				Scanner scanner = new Scanner(pupilsFile);
				
				if(pupilsFile.length()==0){ //if the file is empty, stop method
					out.println("File \"pupils.txt\" exists, but it is empty :(");
					scanner.close();
					return false;
				}
				
				int lineNum=0; //can be used in catch-block to show where exactly a mistake was
				while (scanner.hasNextLine()) {
					lineNum++;
					String line=scanner.nextLine();
					if(line.isEmpty()) continue; //  skip empty lines
			        String[] pupilInfo = line.split("\t");
			        
			        if(pupilInfo.length<2){ //if some necessary information is missed, skip line
					      continue;
					} 
			        if(pupilInfo.length<3 && taskNum==3){
					      continue;
					}
			         			        
			        int booksCount=0;
			        //try to get how many books a pupil has read
			        //if takes place a wrong input, NFE-catcher shows you where exactly in file it has happened
			        try{
			        	booksCount=Integer.parseInt(pupilInfo[1]);
			        	if(booksCount<0) continue;
			        	
			        	switch(taskNum){
			        		case 2:
				        		if(booksCount>1){	//add only those pupils who've read more than 1 book : 2,3,4,...
						        	pupilsList.add(new Pupil(pupilInfo[0],booksCount));	//pupilInfo[0] – name of the pupil
						        }
				        		break;
			        		case 3:
			        			  if(booksCount<=2){	//add only those pupils who've read less then or equal to 2 book : 0,1,2
			  			        	Pupil pupil=new Pupil(pupilInfo[0],booksCount);
			  			        	pupil.setBirthDate(LocalDate.parse(pupilInfo[2], DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.US)));
			  			        	pupilsList.add(pupil); 
			  			        	}
			        			  break;	
			        	} 
			        }
			        	catch(NumberFormatException ex){
			        		// if I were in Android Studio I would rather use logs instead
			        		out.println("Check your input "+ex.getMessage()+" at line "+lineNum+" in file");
			        	}
			        /*	catch(ArrayIndexOutOfBoundsException ex){
			        		out.println("Check your input, you have \"ArrayIndexOutOfBoundsException\" at index "
			        		+ex.getMessage()+" at line "+lineNum+" in file");
			        	} */
				}
				scanner.close();
			} 
				catch (FileNotFoundException e) {
					out.println(" Unfortunately, file \"pupils.txt\" wasn't found :(");
				    	   return false;
				} 

			//if the file isn't empty, but contains only empty lines f.e.
			if(pupilsList.isEmpty()){
				out.println(" File doesn't contain the right information about pupils, check it.");
				return false;
			}
			
			switch(taskNum){
				case 2:
					sortListForTheSecondTask();
					break;
				case 3:
					sortListForTheThirdTask();	
					break;
				}
			
			out.println("\n\tDo u want to serialize lists?[yes/ath]");
			Scanner scanner=new Scanner(System.in);
			switch(scanner.next()){
				case "yes":
					serializeList();
					break;
				default: 
					break;
			}
		return true;
	}
	
	private void serializeList(){
			try {
				ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src"+File.separator+"serializedList.ser"));
				for(Pupil p: pupilsList){
                os.writeObject(p);
				}
        		os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
	}

//method that sorts list for the 2nd task using Comparator
	private void sortListForTheSecondTask(){
		Collections.sort(pupilsList, new Comparator<Pupil>() { 
			@Override
			public int compare(Pupil o1, Pupil o2) {
				if( o1.getReadBooksNum() > o2.getReadBooksNum()){
					return 1;
				}
				if( o1.getReadBooksNum() < o2.getReadBooksNum() ){
					return -1;
				}
				return 0;
			}
		});
	}

//method that sorts list for the 3rd task using two Comparators :)
	private void sortListForTheThirdTask(){
		Collections.sort(pupilsList, new Comparator<Pupil>() {  
			@Override
			public int compare(Pupil o1, Pupil o2) {
				if( o1.getReadBooksNum() < o2.getReadBooksNum()){	
					return 1;
				}
				if( o1.getReadBooksNum() > o2.getReadBooksNum() ){
					return -1;
				}
				if(o1.getReadBooksNum()==o2.getReadBooksNum()){
					return compareBirthDates(o1, o2);
				}
				return 0;
			}
		});
	}
	
//method that returns the result of date comparison of two pupils who have identical number of books read	
	private int compareBirthDates(Pupil p1, Pupil p2){
		Comparator<Pupil> comp=new Comparator<Pupil>() {
			@Override
			public int compare(Pupil o1, Pupil o2) {
				return p1.getBirthDate().compareTo(p2.getBirthDate());
		}
		};
		return comp.compare(p1, p2);
	}

	private void printPupilsListFromSerializedFile(int taskNum){
		try {
			ObjectInputStream 	is= new ObjectInputStream(new FileInputStream("src"+File.separator+"serializedList.ser"));
			int i=pupilsList.size();
		while(i>0){
			Pupil p = (Pupil)is.readObject();
			switch(taskNum){
			case 2:
				out.println("\t"+p.getName()+": "+p.getReadBooksNum()); break;
			case 3:
				out.println("\t"+p.getName()+",  "+p.getBirthDate()+": "+p.getReadBooksNum());
				break;

			}
			i--;
		}
			is.close();
			
		}
		catch(FileNotFoundException ex){
			out.println("File wasn't find, printing default method: ");
			printPupilsList(taskNum);
			//ex.printStackTrace();
		}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private void printPupilsList(int taskNum){
		for(Pupil pupil: pupilsList){
			switch(taskNum){
			case 2:
				out.println("\t"+pupil.getName()+": "+pupil.getReadBooksNum()); break;
			case 3:
				out.println("\t"+pupil.getName()+", "+pupil.getBirthDate()+": "+pupil.getReadBooksNum());
				break;

			}
		}
	}


//-----------------------------------------1st TASK---------------------------------------------		
	 void printLiteratureList() {
		 
		 	getLiteratureList();
		
			out.println("\n\t **************** LIBRARY'S AVAILABLE LITERATURE ****************\t");
			out.println("	Books:");
			for(Book book: booksList){
				out.println("\t\t"+book.getName()+" by "+book.getAuthor()+", category: "+book.getCategory()+
						", language: "+book.getLanguage()+", year: "+book.getYear());
			}
			out.println("	Journals:");
			for(Journal journal: journalsList){
				out.println("\t\t"+journal.getName()+", category: "+journal.getCategory()+
						", language: "+journal.getLanguage()+", date: "+journal.getDate()+", frequency: "+
						journal.getFrequency()+", publication history: "+journal.getPublicationHistory());
			}
			out.println("	Articles:");
			for(Article article: articlesList){
				out.println("\t\t"+article.getName()+",  category: "+article.getCategory()+
						",  language: "+article.getLanguage()+",  resource: "+article.getResource()+",  date: "+article.getDate()); 
			} 
			out.println("	Newspapers:");
			for(Newspaper newspaper: newspapersList){
				out.println("\t\t"+newspaper.getName()+",  category: "+newspaper.getCategory()+
						",  language: "+newspaper.getLanguage()+",  date: "+newspaper.getDate()+
						",  published: "+newspaper.getPublished()+",  format: "+newspaper.getFormat());
			}
	}
	
//Using ExecutorService helps to parallelize tasks 
//it can be helpful if sizes of files are huge
	private void getLiteratureList(){
		
		ExecutorService pool = Executors.newFixedThreadPool(5); //pool that may contain 5 threads
		
		pool.execute(this::getBooksList);
        pool.execute(this::getJournalsList);
        pool.execute(this::getArticlesList);
        pool.execute(this::getNewspapersList); 
        
        pool.shutdown(); //ExecutorService stops taking new tasks 
       
        try {
            if (!pool.awaitTermination(900, TimeUnit.MILLISECONDS)) { // waits up to a 900 ms period of time for all tasks to be completed. 
                pool.shutdownNow();									  //If that time expires, the execution is stopped immediately
            } 
        } catch (InterruptedException e) {
            pool.shutdownNow();
        } 
	}
	
	private boolean fileIsNotEmpty(File file){
		if(file.length()==0){
			out.println("File \""+file.getName()+"\" exists, but it is empty :(");
			return false;
		}
		return true;
	}
	
	private void getNewspapersList() {
		newspapersList=new ArrayList<>();
		try{
			File newspapersFile=new File("src"+File.separator+"newspapers.txt");
			Scanner scanner=new Scanner(newspapersFile);
			if(fileIsNotEmpty(newspapersFile)){
				int lineNum=0;
				while (scanner.hasNextLine()) {
					lineNum++;
					String line=scanner.nextLine();
					if(line.equals("")) continue;
			        String[] newspaperInfo = line.split("\t");
			        if(newspaperInfo.length<3) continue;
					Newspaper newspaper=new Newspaper(newspaperInfo[0],newspaperInfo[1],newspaperInfo[2]);
					if(newspaperInfo.length>=4) newspaper.setPublished(newspaperInfo[3]);
					if(newspaperInfo.length>=5) newspaper.setFormat(newspaperInfo[4]);
					if(newspaperInfo.length>=6){
						try{
						newspaper.setDate(LocalDate.parse(newspaperInfo[5], DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.US)));
						}catch(DateTimeParseException ex){
							out.println("Wrong date format at line "+lineNum+" in file \"newspapers.txt\"");
						}
					}
					newspapersList.add(newspaper);
			    }
			}
			scanner.close();
	    }
	       catch(FileNotFoundException fnfEx){
	    	   out.println("!!! Unfortunately, file \"newspapers.txt\" wasn't found !!!");
	       }
		
	}

	private void getArticlesList() {
		articlesList=new ArrayList<>();
		try{
			File articlesFile=new File("src"+File.separator+"articles.txt");
			Scanner scanner=new Scanner(articlesFile);
			if(fileIsNotEmpty(articlesFile)){
				int lineNum=0;
				while (scanner.hasNextLine()) {
					lineNum++;
					String line=scanner.nextLine();
					if(line.equals("")) continue;
			        String[] articleInfo = line.split("\t");
			        if(articleInfo.length<3) continue;
			        Article article=new Article(articleInfo[0],articleInfo[1],articleInfo[2]); 
			        if(articleInfo.length>=4) article.setResource(articleInfo[3]);		    
			        if(articleInfo.length>=5){
			        	try{
			        		article.setDate(LocalDate.parse(articleInfo[4], DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.US)));
			        	}
			        		catch(DateTimeParseException ex){
							out.println("Wrong date format at line "+lineNum+" in file articles.txt");
			        		}
			        }
			        articlesList.add(article);
			    }
			}
			scanner.close();
	    }
			catch(FileNotFoundException fnfEx){
	    	   out.println("!!! Unfortunately, file \"articles.txt\" wasn't found !!!");
	       }
		
	}

	private void getJournalsList() {
		journalsList=new ArrayList<>();
		try{
			File journalsFile=new File("src"+File.separator+"journals.txt");
			Scanner scanner=new Scanner(journalsFile);
			if(fileIsNotEmpty(journalsFile)){
				int lineNum=0;
				while (scanner.hasNextLine()) {
					lineNum++;
					String line=scanner.nextLine();
					if(line.equals("")) continue;
			        String[] journalInfo = line.split("\t");
			        if(journalInfo.length<3) continue;
			        Journal journal=new Journal(journalInfo[0],journalInfo[1],journalInfo[2]);
			        if(journalInfo.length>=4) journal.setFrequency(journalInfo[3]);
			        if(journalInfo.length>=5) journal.setPublicationHistory(journalInfo[4]);
			        if(journalInfo.length>=6){
			        	try{
			        	journal.setDate(LocalDate.parse(journalInfo[5], DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.US)));
			        	}catch(DateTimeParseException ex){
							out.println("Wrong date format at line "+lineNum+" in file journals.txt");
		        		}
			        }

			        
			        journalsList.add(journal);
			    }
			}
			scanner.close();
	    }
			catch(FileNotFoundException fnfEx){
	    	   out.println("!!! Unfortunately, file \"journals.txt\" wasn't found !!!");
	       }
	}
	

	private void getBooksList(){
		booksList=new ArrayList<>();
		try{
			File booksFile=new File("src"+File.separator+"books.txt");
			Scanner scanner=new Scanner(booksFile);
			if(fileIsNotEmpty(booksFile)){
				int lineNum=0;
				while (scanner.hasNextLine()) {
					lineNum++;
					String line=scanner.nextLine();
					if(line.equals("")) continue;
			        String[] bookInfo = line.split("\t");
			        if(bookInfo.length<3) continue;
					Book book=new Book(bookInfo[0],bookInfo[1],bookInfo[2]);
			        if(bookInfo.length>=4) book.setAuthor(bookInfo[3]);
			        if(bookInfo.length>=5){
			        	try{
				        	book.setYear(Integer.valueOf(bookInfo[4]));
			        	}catch(NumberFormatException ex){
			        		out.println("Check your input "+ex.getMessage()+" at line "+lineNum+" in file \"books.txt\" ");
			        	}
			        }
			        booksList.add(book);
			    }
			}
			scanner.close();
	    }
			catch(FileNotFoundException fnfEx){
	    	   out.println("!!! Unfortunately, file \"books.txt\" wasn't found !!!");
	       }
	}
//-----------------------------------------END OF THE 1st TASK---------------------------------------------		

}