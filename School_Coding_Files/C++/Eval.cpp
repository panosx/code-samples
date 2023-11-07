//File: Eval.cpp
//Purpose: Implements infix evaluation algorithm to perform arithmetic operations on given input and print results
//Edited By: Panos Xenakis (301256714) on Feb 18 2022


#include "Scanner.h"
#include "Stack.h"  // GENERIC STACK

#include <iostream>
using namespace std;

//Description: Performs arithmetic operation corresponding to given token type
//Parameters: Two integers involved in arithmetic operation and token representing operation type
//Postcondition: Returns value of corresponding operation
int operation(int num1, int num2, Token op){
    switch (op.tt)//switch to determine what operation to perform
    {
    case pltok:
        return num1+num2;
        break;

    case mitok:
        return num1-num2;
        break;

    case asttok:
        return num1*num2;
        break;

    case slashtok:
        return num1/num2;
        break;

    default:
        return 0;
        break;
    }
    return 0;
}

//Description: Pretty printer for displaying operations in terminal
//Parameter: Token to be converted and printed as string
//Postcondition: Terminal output displays token as string
void prettyPrinter(Token t){
    if (t.tt == integer || t.tt == lptok || t.tt == rptok) {
            cout << t;
        } else if (t.tt == pltok || t.tt == mitok || 
                   t.tt == asttok || t.tt == slashtok) {
            cout << ' ' << t << ' ';
        }

}



int main () {
    
    
    Scanner S(cin);
    Token t;

    Stack<Token> numstack, opstack;  // 2x Stacks of type Token
    

    t = S.getnext();

    while(t.tt!=eof||!opstack.isEmpty()){


        if(t.tt==integer){//pushes integers to numstack
            numstack.push(t);
            prettyPrinter(t);
            t = S.getnext();
        }
        else if(t.tt==lptok){//pushes left parentheses to opstack
            opstack.push(t);
            prettyPrinter(t);
            t = S.getnext();
        }
        else if(t.tt==rptok){//checks if token is right parentheses
            if(opstack.peek().tt==lptok){//pops left parentheses from opstack and prints token
                opstack.pop();
                prettyPrinter(t);
                t = S.getnext();
            }
            else{//performs arithmetic operation and pushes resulting token to numstack
                int num1, num2;
                num2 = numstack.pop().val;
                num1 = numstack.pop().val;
                Token op = opstack.pop();
                int result = operation(num1, num2, op);
                Token pusher;
                pusher.text = to_string(result);
                pusher.val = result;
                pusher.tt = integer;
                numstack.push(pusher);
            }
        }
        else if(t.tt==pltok||t.tt==mitok||t.tt==eof){//checks if token is +, -, or eof
            if(!opstack.isEmpty()&&(opstack.peek().tt==pltok||opstack.peek().tt==mitok||opstack.peek().tt==asttok||opstack.peek().tt==slashtok)){//checks operator stack is not empty and has arithmetic operation on top
                int num1, num2; //performs arithmetic operation and pushes resulting token to numstack
                num2 = numstack.pop().val;
                num1 = numstack.pop().val;
                Token op = opstack.pop();
                int result = operation(num1, num2, op);
                Token pusher;
                pusher.text = to_string(result);
                pusher.val = result;
                pusher.tt = integer;
                numstack.push(pusher);
            }
            else{//pushes token to opstack and prints token
                opstack.push(t);
                prettyPrinter(t);
                t = S.getnext();
            }
        }
        else if(t.tt==asttok||t.tt==slashtok){//checks if token is division or multiplication operator
            if(!opstack.isEmpty()&&(opstack.peek().tt==asttok||opstack.peek().tt==slashtok)){//checks if opstack is empty and if top token is * or /
                int num1, num2;//performs arithmetic operation and pushes resulting token to numstack
                num2 = numstack.pop().val;
                num1 = numstack.pop().val;
                Token op = opstack.pop();
                int result = operation(num1, num2, op);
                Token pusher;
                pusher.text = to_string(result);
                pusher.val = result;
                pusher.tt = integer;
                numstack.push(pusher);
            }
            else{//pushes token to opstack and prints token
                opstack.push(t);
                prettyPrinter(t);
                t = S.getnext();
            }
        }

    }

    cout << " = " << numstack.pop().val;//prints result of input equation after eof is reached and arithmetic operations complete

    

    cout << endl;
    // End pretty printer coding demo.

    return 0;
}
