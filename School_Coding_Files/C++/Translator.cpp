/*
*File: Translator.cpp
*Description: Creates dictionary object from file input and allows user to search for elements
*             Repurposes testDriver.cpp structure
*Author: Panos Xenakis (301256714)
*Date:March 9, 2022
*/

#include <iostream>
#include <stdio.h>
#include <cstdlib>
#include <string>
#include <cstring>
#include <sstream>
#include <iomanip>
#include <fstream>
#include "BST.h"
#include "WordPair.h"
#include "ElementAlreadyExistsException.h"
#include "ElementDoesNotExistException.h"
#include "EmptyDataCollectionException.h"
#include "Dictionary.h"


void display(const WordPair& anElement) {
  cout << anElement << endl;
}


//Repurposed testdriver file to run translator program
int main(int argc, char *argv[]) {

  Dictionary<WordPair>* spanish = new Dictionary<WordPair>();//creates dictionary object
    
  string aLine = "";
  string aWord = "";
  string englishW = "";
  string translationW = "";
  string filename = "myDataFile.txt";
  string delimiter = ":";
  size_t pos = 0;
  WordPair translated;
 
  
  ifstream myfile (filename);
  if (myfile.is_open()) {
    while ( getline (myfile,aLine) )
    {
       pos = aLine.find(delimiter);
       englishW = aLine.substr(0, pos);
       aLine.erase(0, pos + delimiter.length());
       translationW = aLine;
       WordPair aWordPair(englishW, translationW);
      try{//inserts elements from text file into dictionary
        spanish->put(aWordPair);
      }
      catch(ElementAlreadyExistsException& anException){
         //catches exceptions from duplicate elements (handling not specified)
      }
    }
    myfile.close();


    // If user entered "Display" at the command line...
    if ( ( argc > 1 ) && ( strcmp(argv[1], "Display") == 0) ) {
      try{
      spanish->displayContent(display);//Displays contents of dictionary in order
      }
      catch(EmptyDataCollectionException& exc){
        //catches exceptions from empty dictionary (handling not specified)
      }
    }
    else if (argc == 1) {
       // while user has not entered CTRL+D
       while ( getline(cin, aWord) ) {   

          WordPair aWordPair(aWord);
          try{//attempts to assign aWordPair to get function call and print retrieved element
            aWordPair = spanish->get(aWordPair);
            cout << aWordPair << endl;
          }
          catch(ElementDoesNotExistException& anException){//Alerts user if target element isnt found
            cout << "***Not Found!***" << endl;
          }

       }
    }
    
  }
  else 
    cout << "Unable to open file"; 

    
  return 0;
}