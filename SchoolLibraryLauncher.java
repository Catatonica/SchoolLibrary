
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.System.out;

import java.io.IOException;

final class SchoolLibraryLauncher {

	
	public static void main(String[] args) {
		
		SchoolLibraryHelper helper=new SchoolLibraryHelper();
		Scanner keyboard=new Scanner(System.in);
		
		cycle:
		while(true){
			out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
					+ "\n	Enter:"
					+ "\n	1 – to view the list of available for reading books, articles, journals and newspapers;"
					+ "\n	2 – to view report on the pupils who have read more than 1 book;"
					+ "\n	3 – to view report on the pupils who have read less than or equal to 2 books"
					+ "\n	-1 – to stop program and exit"
					+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			switch(getInput(keyboard)){
				case 1:
					helper.printLiteratureList();
					break;
				case 2:
					helper.printFirstPupilsList();
					break;
				case 3:
					helper.printSecondPupilsList();
					break;
				case -1:
					break cycle;	
				default: // if the input value wasn't -1/1/2/3
					out.println("Be attentive, your input should be different");
					break;
			}
		}
		keyboard.close();

	}
	
	//method returns input number
	private static int getInput(Scanner keyboard){
		int key=0;
		//doing until right input
		do{
			try{
			 key=keyboard.nextInt();
			 break;
			}
			 catch(InputMismatchException ex){
				out.println("Wrong input, try again");
				keyboard.next();
			}
		}while(true); 
		return key;
	}

}