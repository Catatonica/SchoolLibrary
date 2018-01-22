	
	Class «SchoolLibraryLauncher» is a launcher class(contains method «main»). 

	Class «SchoolLibraryHelper» – the main class where all significant methods are invoked. 

	Package src contains file «pupils.txt» (in which all information about pupils is saved) and 4 others, which one of them contains different kinds of literature, that are in school library: «books.txt», «journals.txt», «articles.txt» and «newspapapers.txt». Default delimeter is TAB.

	In method «printLiteratureList» information about all presented kinds of literature gathers simultaniously from different files and then prints.

	In methods «printFirstPupilsList»/«printSecondPupilsList» all suitable information about pupils gathers using method «getPupilsList» (either for the 2nd or the 3rd task) and then prints.

	I’ve also decided to create classes «Book», «Article», «Journal», «Newspaper» and «Pupil» for some reasons:
	-if it will be needed in future to choose some of these objects and do some operations with them(such as sort, send or ath else), you can easily do it using Java’s features;
	-if you’d like to create a file, containg only suitable objects, and then read it, you can do it with the help of serialization/deserialization.
	Class «Literature» units common features of classes «Book», «Article», «Journal» and «Newspaper» and is a parent class(or superclass) for them.