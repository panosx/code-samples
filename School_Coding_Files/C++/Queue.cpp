/* 
 * Queue.cpp
 *
 * Description: Implementation of an int sequence with enqueue/dequeue ...
 * Class Invariant: ... in FIFO order
 *
 * Author: Panos Xenakis (301256714)
 * Date: Feb 18 2022
 */
 
#include "Queue.h"
#include <iostream>

using namespace std;


// Description:  Constructor
Queue::Queue() : elementCount(0), capacity(INITIAL_CAPACITY), frontindex(0), backindex(0), actualSize(0) {
    elements = new int[INITIAL_CAPACITY];
} 

Queue::~Queue(){
    delete this->elements;
}


// Description:  Inserts element x at the back (O(1))
void Queue::enqueue(int x) {
    if(actualSize==capacity){//checks if actual number of elements in array is greater than capacity
        int* replacement = new int[capacity*2];//creates new array double size of original
        for(unsigned int i=0; i<capacity; i++){//copies elements from original array to replacement
            replacement[i] = elements[i];
        }
        delete [] elements;//deletes original array of elements
        this->elements = replacement;//sets Queues element array to larger replacement array
        capacity *= 2;//sets capacity to size of new array
    }
    actualSize++;
    elementCount++;
    this->elements[backindex] = x;
    backindex = (backindex + 1);
} 


// Description:  Removes the frontmost element (O(1))
// Precondition:  Queue not empty
void Queue::dequeue() {
    if(elementCount==capacity/4&&capacity/2>=INITIAL_CAPACITY){//checks if element count is 1/4 size of capacity and 1/2 capacity is greater than initial capacity (6)
        int* replacement = new int[capacity/2];//creates new array half the size of original
        for( unsigned int i=0; i<elementCount; i++){//adds elements within indices to new smaller array
            replacement[i] = elements[frontindex+i];
            actualSize = i+1;//updates actual size to number of elements copied to new array
        }
        for( unsigned int i=actualSize; i<capacity/2; i++){//fills remaining indices of new array with zeroes for consistency
            replacement[i] = 0;
        }
        delete [] elements;//deletes original element array
        this->elements = replacement;//sets Queue element array to new array
        capacity /= 2;//updates capacity
        frontindex = 0;//resets front index
        backindex = actualSize;//repositions backindex
    }
    elementCount--;
    frontindex = (frontindex + 1);
} 


// Description:  Returns a copy of the frontmost element (O(1))
// Precondition:  Queue not empty
int Queue::peek() const {
    return elements[frontindex];
} 


// Description:  Returns true if and only if queue empty (O(1))
bool Queue::isEmpty() const {
    return elementCount == 0;
}

