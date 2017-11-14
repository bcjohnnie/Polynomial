//----------------------------------------------------------------------------
// StackInterface.java           by Dale/Joyce/Weems                 Chapter 2
//
// Interface for a class that implements a stack of <T>.
// A stack is a last-in, first-out structure.
//----------------------------------------------------------------------------
package question1;

public interface PolyStackInterface<T>
{
  void push(int coefficient, int exponent) throws StackOverflowException;
  // Throws StackOverflowException if this stack is full,
  // otherwise places element at the top of this stack.

  void pop() throws StackUnderflowException;
  // Throws StackUnderflowException if this stack is empty,
  // otherwise removes top element from this stack.
  
  PolyNode top() throws StackUnderflowException;
  // Throws StackUnderflowException if this stack is empty,
  // otherwise returns top element of this stack.
  
  boolean isEmpty();
  // Returns true if this stack is empty, otherwise returns false.

  boolean isFull();
  // Returns true if this stack is full, otherwise returns false.
}
