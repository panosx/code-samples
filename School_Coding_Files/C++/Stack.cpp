/* 
 * Stack.cpp
 *
 * Description: Implementation of an int sequence with push/pop ...
 * Class Invariant: ... in a LIFO order
 *
 * Author: Panos Xenakis (301256714)
 * Date: Feb 18 2022
 */
 
#include <cstddef>  // For NULL
#include "Stack.h"

using namespace std;

// Description:  Constructor
// Postcondition:  Stack is empty
Stack::Stack(){
    head = NULL;
    tail = NULL;
}

// Description: Deconstructor 
// Postcondition: Memory allocated for stack is freed
Stack::~Stack(){
    StackNode* ptr = head;
    StackNode* next;
    while(ptr!=NULL){
        next = ptr->next;
        delete ptr;
        ptr = next;
    }
}

// Description:  Insert element x to the top of the stack.
// Postcondition: Tail points to StackNode containing x
void Stack::push(int x){
   if(this->head==NULL){//checks if nothing else exists in stack
       this->head = new StackNode();
       this->head->data = x;
       this->tail = this->head;
   } else{
   StackNode* ptr = this->head;
   StackNode* prev;
   while(ptr!=NULL){//traverses list to find tail position
       prev = ptr;
       ptr = ptr->next;
   }
   ptr = new StackNode();//creates new node at end of list
   ptr->next = NULL;
   ptr->data = x; 
   this->tail = ptr;//sets tail to new node
   prev->next = ptr;
   }
}

// Description:  Remove and return element at the top of the stack.
// Precondition:  Stack is not empty
// Postcondition:  Element at top of stack (tail) is removed and returned
int Stack::pop(){
    int ret = this->tail->data;
    StackNode* ptr = this->head;
    if(this->head==this->tail){//checks if only one element exists in list
        this->head = NULL;
        this->tail = NULL;
        return ret;
    }
    StackNode* prev;
    while(ptr!=this->tail){//traverses list to tail position
        prev = ptr;
        ptr = ptr->next;
    }
    this->tail = prev;//sets tail to previous node
    this->tail->next = NULL; //detaches former tail from list
    return ret;
}

// Description:  Return the topmost element of the stack.
// Precondition: Stack is not empty  
// Postcondition:  Stack remains unchanged
int Stack::peek() const{
    return this->tail->data;
}

// Description:  Indicates whether stack is empty or not
// Postcondition: Boolean returns true if no elements are in stack, false otherwise
bool Stack::isEmpty() const{
    if(this->head == NULL){
        return true;
    }
    else{
        return false;
    }
}

