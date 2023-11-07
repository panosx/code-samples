/*
*File: Dictionary.cpp
*Description: Implements methods from Dictionary.h for creating and interacting with Dictionary objects
*             Utilizes template interface
*Author: Panos Xenakis (301256714)
*Date: March 9, 2022
*/

#include "Dictionary.h"
#include "WordPair.h"
#include <stdio.h>
#include <iostream>

//Default constructor
template<class ElementType>
Dictionary<ElementType>::Dictionary(){
    keyValuePairs = new BST<ElementType>();
}

//Parameterized constructor
//  Takes elementtype argument to be placed as initial dictionary entry
template<class ElementType>
Dictionary<ElementType>::Dictionary(ElementType& element){
    keyValuePairs = new BST<ElementType>(element);
}

//Destructor
//  Utilizes BST destructor to delete keyvaluepairs BST
template<class ElementType>
Dictionary<ElementType>::~Dictionary(){
    keyValuePairs->~BST();
}

//Description: Returns element count of dictionary
//             Utilizes keyvaluepairs getElementCount BST method
//Return: int representing number of elements in Dictionary
template<class ElementType>
unsigned int Dictionary<ElementType>::getElementCount() const{
    return keyValuePairs->getElementCount();
}

//Description: Method for inserting elements into Dictionary
//             Utilizes BST insert method on keyvaluepairs attribute
//Precondition: Element cannot already exist in Dictionary
//Postcondition: element count of keyvaluepairs increased by one
//               element inserted in correct position with respect to existing elements
//Exception: Propogates ElementAlreadyExists exception from traverseInOrder method
template<class ElementType>
void Dictionary<ElementType>::put(const ElementType& newElement){
    try{
        keyValuePairs->insert(newElement);
    }
    catch(ElementAlreadyExistsException& exc){
        
    }
}

//Description: Retrieves element from dictionary
//             Utilizes retrieve method from BST class
//Precondition: Element exists in Dictionary
//Postcondition: Returns element found in Dictionary
//Exception: Propogates ElementDoesNotExist exception from traverseInOrder method
template<class ElementType>
ElementType& Dictionary<ElementType>::get(const ElementType& targetElement) const{
    return keyValuePairs->retrieve(targetElement);
}


//Description: Traverses entire dictionary element collection
//             Performs visit function on each element
//             visit implied to be some sort of printing method
//Precodition: Dictionary is not empty
//Postcondition: Every element in dictionary has been visited
//Exception: Propogates EmptyDataCollection exception from traverseInOrder method
template<class ElementType>
void Dictionary<ElementType>::displayContent(void visit(const ElementType&)) const{
    keyValuePairs->traverseInOrder(visit);
}