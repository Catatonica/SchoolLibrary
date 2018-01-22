# SchoolLibrary

MAIN INFO

  Class «SchoolLibraryLauncher» is a launcher class(contains method «main»). 

	Class «SchoolLibraryHelper» – the main class where all significant methods are invoked. 

	Package src contains file «pupils.txt» (in which all information about pupils is saved) and 4 others,
  which one of them contains different kinds of literature, that are in school library: 
  «books.txt», «journals.txt», «articles.txt» and «newspapapers.txt». Default delimeter is TAB.

	In method «printLiteratureList» information about all presented kinds of literature gathers simultaniously
  from different files and then prints.

	In methods «printFirstPupilsList»/«printSecondPupilsList» all suitable information about pupils gathers using
  method «getPupilsList» (either for the 2nd or the 3rd task) and then prints.

	I’ve also decided to create classes «Book», «Article», «Journal», «Newspaper» and «Pupil» for some reasons:
	-if it will be needed in future to choose some of these objects and do some operations with them(such as sort,
  send or ath else), you can easily do it using Java’s features;
	-if you’d like to create a file, containg only suitable objects, and then read it, you can do it with the help of
  serialization/deserialization.
  
	Class «Literature» units common features of classes «Book», «Article», «Journal» and «Newspaper» and is a 
  parent class(or superclass) for them.
  
  DETAILS
  
  – concerning files "books.txt" and others :
      Information is presented approximatelly in form of "Robinson Crusoe	fiction	AmEn	D.Defoe	2015"(1), where "Robinson Crusoe"–
      is the name of the book, "fiction" – category, "AmEn"– language, "D.Defoe" – author and "2015" – year of publication.
      TAB is a delimeter between them. If you write (1) via spaces, all of it will be defined as the name. There are several 
      verifications to check out whether you've wrote right information or not. For instance, empty lines are skipped and if u 
      write "20.15" at the place where year requires, you'll get a corresponding message. 
      Also, there's a list of required info to be marked as valid. For "pupils.txt" it is name and count of books read for the 
      2nd task and name,count of books and date of birth for the 3rd. If there is other info – good, isn't – that's all right.
  – technical details:
      I'd like to clarify some – firstly, about reading from files in 4 threads simultaneously – it is not obligatorily to do
      it on small sizes, I've checked time and know that it saves only ~0.1 s, but I believe, this value will be much more 
      higher on huge sizes. 
      And the second thing is sort. I prefered to use Java's internal method Collections.sort(), though I could wrote 
      sort by myself and here's a reason:
      "This implementation is a stable, adaptive, iterative mergesort that requires far fewer than n lg(n) comparisons when the input array is partially sorted, while offering the performance of a 
      traditional mergesort when the input array is randomly ordered. If the input array is nearly sorted, the implementation
      requires approximately n comparisons. Temporary storage requirements vary from a small constant for nearly sorted input
      arrays to n/2 object references for randomly ordered input arrays."(c)–docs.oracle.com.
      
    

    
   
