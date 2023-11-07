/*
 * BST.cpp
 * 
 * Description: Binary Search Tree data collection ADT class.
 *              Link-based implementation.
 *              Duplicated elements are not allowed.
 *
 * Class invariant: It is always a BST.
 * 
 * Author: Inspired from our textbook, Panos Xenakis (301256714)
 * Date of last modification: March 9, 2022
 */

#include "BST.h"
	
// You can modify the implementation of these methods, but cannot modify their proptotype.
// However, if you modify the implementation of these methods, your code must still 
// satisfy the documentation contract above each of these methods.

/* Constructors and destructor */

	// Default constructor
    template<class ElementType>
	BST<ElementType>::BST(){
		root = NULL;
		elementCount = 0;
	}
	
	// Parameterized constructor
    template<class ElementType>      
    BST<ElementType>::BST(ElementType& element) {
		root = new BSTNode<ElementType>(element);
		elementCount = 1;	
	}               

    // Copy constructor
    template<class ElementType>  
	BST<ElementType>::BST(const BST<ElementType>& aBST){
		root = new BSTNode<ElementType>(aBST.root->element);
		copyTraverse(aBST, *this);
		
	}

	// Destructor 
    template<class ElementType> 
	BST<ElementType>::~BST() {

    	destructTraverse(*this);

    }                     
	
	
	
/* Getters and setters */

    // Description: Returns the number of elements currently stored in the binary search tree.	
	// Time efficiency: O(1)
    template<class ElementType>	
	unsigned int BST<ElementType>::getElementCount() const {		

		return this->elementCount;
	}
	

/* BST Operations */

    // Description: Inserts an element into the binary search tree.
	//              This is a wrapper method which calls the recursive insertR( ).
	// Precondition: "newElement" does not already exist in the binary search tree.
    // Exception: Throws the exception "ElementAlreadyExistsException" 
	//            if "newElement" already exists in the binary search tree.
	// Time efficiency: O(log2 n)	
    template<class ElementType>
	void BST<ElementType>::insert(const ElementType& newElement) {
		
	    // Binary search tree is empty, so add the new element as the root
		if (elementCount == 0) {
			root = new BSTNode<ElementType>(newElement);
			elementCount++;
		}
		else 
		  if ( !insertR(newElement, root) ) {
  	         throw ElementAlreadyExistsException("Element already exists in the data collection.");
  	      }
  	   return;
  	}

    // Description: Recursive insertion into a binary search tree.
	//              Returns true when "anElement" has been successfully inserted into the 
	//              binary search tree. Otherwise, returns false.
    template<class ElementType>
	bool BST<ElementType>::insertR(const ElementType& anElement, BSTNode<ElementType>* current) { 
		
		if(anElement==current->element){//checks if new element is the same as current element
            return false;
        }
		else if(anElement < current->element && current->left==NULL){//checks if value of element is less than current and if currents left child is null
			current->left = new BSTNode<ElementType>(anElement);//attaches element to left of current
            elementCount++;
            return true;
		} else if(anElement > current->element && current->right==NULL){//checks if value of element is greater than current and if currents right child is null
			current->right = new BSTNode<ElementType>(anElement);//attaches element to right of current
            elementCount++;
            return true;
		} else if(anElement < current->element){//traverses to left child and recurses if element is less than current
			insertR(anElement, current->left);
		} else{//traverses to right child and recurses
			insertR(anElement, current->right);
		}
		//return true;

	}

	
    // Description: Retrieves "targetElement" from the binary search tree.
	//              This is a wrapper method which calls the recursive retrieveR( ).
	// Precondition: Binary search tree is not empty.
    // Exception: Throws the exception "EmptyDataCollectionException" 
	//            if the binary search tree is empty.
	// Exception: Throws (propagates) the exception "ElementDoesNotExistException" 
	//            thrown from the retrieveR(...)
	//            if "targetElement" is not in the binary search tree.
	// Time efficiency: O(log2 n)
    template<class ElementType>
    ElementType& BST<ElementType>::retrieve(const ElementType& targetElement) const {
        
	    // Check precondition: If binary search tree is empty
		if (elementCount == 0)  
			throw EmptyDataCollectionException("Binary search tree is empty.");
		
		// Otherwise, search for it		
		ElementType& translated = retrieveR(targetElement, root);

		return translated;
	}

    // Description: Recursive retrieval from a binary search tree.
	// Exception: Throws the exception "ElementDoesNotExistException" 
	//            if "targetElement" is not found in the binary search tree.
    template<class ElementType>
    ElementType& BST<ElementType>::retrieveR(const ElementType& targetElement, BSTNode<ElementType>* current) const {

		if(targetElement==current->element){//base case: target element is found
            return current->element;
        }
        else if(targetElement < current->element && !current->hasLeft()){//first case for element not found, throws exception
            throw ElementDoesNotExistException("Element does not exist in tree.");
		}
		else if(targetElement > current->element && !current->hasRight()){//second case for element not found, throws exception
            throw ElementDoesNotExistException("Element does not exist in tree.");
        }
		else if(targetElement < current->element){//recursive step for left subtree
			retrieveR(targetElement, current->left);
		}
		else{//recursive step for right subtree
			retrieveR(targetElement, current->right);
		}

		//return current->element;

	} // end of retrieveR
				
	
    // Description: Traverses the binary search tree in order.
	//              This is a wrapper method which calls the recursive traverseInOrderR( ).
	//              The action to be done on each element during the traverse is the function "visit".
	// Precondition: Binary search tree is not empty.
    // Exception: Throws the exception "EmptyDataCollectionException" 
	//            if the binary search tree is empty.
	// Time efficiency: O(n)		
    template<class ElementType>
	void BST<ElementType>::traverseInOrder(void visit(const ElementType&)) const {
		
		// Check precondition: If binary search tree is empty
		if (elementCount == 0)  
			throw EmptyDataCollectionException("Binary search tree is empty.");

		traverseInOrderR(visit, root);
		
		return;
	}

    // Description: Recursive in order traversal of a binary search tree.	
	//				traverses in left, visit, right order for desired sorting
	template<class ElementType>
	void BST<ElementType>::traverseInOrderR(void visit(const ElementType&), BSTNode<ElementType>* current) const {
		if(current==NULL){//base case
            return;
        }else{
            traverseInOrderR(visit, current->left);//recursive step into left subtree
			visit(current->element);//visit element after traversing left subtree
            traverseInOrderR(visit, current->right);//recursive step into right subtree after visit
			return;
        }
		
	}


	 // Description: Traverses the binary search tree using Left Right Visit.
	//              Wrapper method which calls the recursive destructTraverseR( ).
	//				Helper method for destructor
	// Time efficiency: O(n)		
	//	Parameters: BST to be deleted
    template<class ElementType>
	void BST<ElementType>::destructTraverse(BST<ElementType>& aBST) {
		
		// Check if binary search tree is empty
		if (aBST.elementCount == 0)  
			return;

		destructTraverseR(aBST.root);
		
		return;
	}

    // Description: Recursive traversal of a binary search tree for deleting nodes.	
	template<class ElementType>
	void BST<ElementType>::destructTraverseR(BSTNode<ElementType>* current){
		if(current==NULL){//base case
            return;
        }else{
            destructTraverseR(current->left);//traverses left subtree
            destructTraverseR(current->right);//traverses right subtree
			delete current;//deletes node after both subtrees are traversed in order to not leave hanging pointers
			return;
        }
		
	}

	 // Description: Traverses the binary search tree using Visit Left Right.
	//              Wrapper method which calls the recursive copyTraverseR( ).
	//				Helper method for copy constructor
	// Time efficiency: O(n)		
	//	Parameters: The original BST to be copied, reference to copy BST
    template<class ElementType>
	void BST<ElementType>::copyTraverse(const BST<ElementType>& original, BST<ElementType>& copy) {
		
		// Check if binary search tree is empty
		if (original.elementCount == 0)  
			return;

		copyTraverseR(original.root, copy);
		
		return;
	}

    // Description: Recursive traversal of a binary search tree for copying BST.	
	template<class ElementType>
	void BST<ElementType>::copyTraverseR(BSTNode<ElementType>* current, BST<ElementType>& copy){
		if(current==NULL){//base case
            return;
        }else{
			try{
			copy.insert(current->element);//inserts current node element into the copy BST
			} catch(ElementAlreadyExistsException& exc){
				//empty catch as to not break recursive loop
			}
            copyTraverseR(current->left, copy);//traverses left subtree
            copyTraverseR(current->right, copy);//traverses right subtree
			return;
        }
		
	}

	