package question1;

public class PolyStack implements PolyStackInterface<Integer>
{
  protected PolyNode top;

  public PolyStack()
  {
    top = null;
  }

  public void push(int coefficient, int exponent)
  { 
    PolyNode newNode = new PolyNode(coefficient, exponent);
    newNode.setLink(top);
    top = newNode;
  }     

  public void pop()
  {                  
    if (isEmpty())
      throw new StackUnderflowException("Pop attempted on an empty stack.");
    else
      top = top.getLink();
  }

  public PolyNode top()
  
  {                 
    if (isEmpty())
      throw new StackUnderflowException("Top attempted on an empty stack.");
    else
      return top;
  }

  public boolean isEmpty()
  {              
    return (top == null); 
  }

  public boolean isFull()
  {              
      return false;
  }
  
  public void printPoly() {
	  boolean next = true;
	  PolyNode currNode = top;
	  if(currNode.getCoefficient() < 0) {
		  System.out.print("-");
		  // Checks if the first term is negative, if so adds a negative symbol before the string
	  }
	  while (next) {
		  String coeff = "";
		  String exp = "";
		  String oper = "";
		  // The string is made up of three elements, the coefficient, exponent, and the operator before the following term
		  if (currNode.getCoefficient() < 0 || currNode.getCoefficient() > 1) {
			  coeff = coeff + Math.abs(currNode.getCoefficient());
			  // Only display a coefficient if it is greater than one, and only display the absolute value
		  }
		  
		  if (currNode.getExponent() == 1) {
			  exp = "x";
			  // if the exponent is 1, do not display a caret
		  }
		  if (currNode.getExponent() > 1) {
			  exp = "x^" + currNode.getExponent();
			  // if the exponent is 0, no exponent is displayed at all
		  }
		  
		  if (currNode.link != null) {
			  if (currNode.link.getCoefficient() > 0) {
				  oper = " + ";
				  // look at the next term, if it is positive, add an addition operator
			  }
			  if (currNode.link.getCoefficient() < 0) {
				  oper = " - ";
				  // if next term is negative, add a subtraction operator
				  // if there is no next term, no operator is added
			  }
		  }

		  if (currNode.getCoefficient() != 0) {
			  System.out.print(coeff + exp + oper);
			  // combine all three elements into a final printed string
		  }
		  if (currNode.link == null) {
			  next = false;
		  }
		  else {
			  currNode = currNode.link;
			  // move on to the next term
		  }
	  }
	  System.out.println();
	  
  }
  
  public PolyStack addPoly() {
	  // adds the polynomial to itself (can be modified to accept two different polynomials and add them together)
	  PolyStack result = new PolyStack();
	  boolean next = true;
	  PolyNode currNode = top;
	  while (next) {
		  result.push(currNode.getCoefficient(), currNode.getExponent());
		  // push each term from the original polynomial onto the new stack
		  if (currNode.link == null) {
			  next = false;
		  }
		  else {
			  currNode = currNode.link;
		  }
	  }
	  currNode = top;
	  next = true;
	  while (next) {
		  result.push(currNode.getCoefficient(), currNode.getExponent());
		  // push each term from the "second" polynomial onto the new stack
		  // no need to do addition here, the sort and combine methods already take care of this
		  if (currNode.link == null) {
			  next = false;
		  }
		  else {
			  currNode = currNode.link;
		  }
	  }
	  sortPoly(result.top);
	  // sorts the terms in descending order by exponent
	  combineTerms(result.top);
	  // combines like terms
	  return result;
  }
  
  public PolyStack multPoly() {
	  // multiplies a polynomial by itself (can be modified to multiply two different polynomials of variable length)
	  PolyStack result = new PolyStack();
	  PolyNode currNode1 = top;
	  PolyNode currNode2 = top;
	  boolean next1 = true;
	  boolean next2 = true;
	  
	  while (next1) {
		  // outer loop, iterates through each term, multiplying it by each term in the "other" polynomial
		  currNode2 = top;
		  next2 = true;
		  while (next2) {
			  // iterates through each term in the "other" polynomial
			  result.push(currNode1.getCoefficient() * currNode2.getCoefficient(), currNode1.getExponent() + currNode2.getExponent());
			  // multiply coefficients and add exponents, then push the resulting PolyNode term onto the result stack
			  if (currNode2.link == null) {
				  next2 = false;
			  }
			  else {
				  currNode2 = currNode2.link;
			  }
		  }
		  if (currNode1.link == null) {
			  next1 = false;
		  }
		  else {
			  currNode1 = currNode1.link;
		  }
	  }
	  sortPoly(result.top);
	  // sort the result stack in descending order by exponent
	  combineTerms(result.top);
	  // combine like terms from the sorted stack
	  return result;
  }
  public PolyNode sortPoly(PolyNode top) {
	  // uses a recursive selection sort to arrange the polynomial terms in descending order by exponent
	  if (top.link != null) {
	  }
	  if(top.link == null) {
		  return top;
	  }
	  PolyNode max = top;
	  PolyNode pointer = top;
	  while(pointer.link != null) {
		  if(max.getExponent() < pointer.link.getExponent()) {
			  max = pointer.link;
			  // compare the current maximum with the next term on the stack, if next is greater, reset max to next
		  }
		  
		  if(max.getExponent() >= top.getExponent()) {
			  swapPoly(top, max);
			  // if the current maximum is >= the first term's exponent, swap the values
			  max = top;
		  }
		  pointer = pointer.link;
	  }
	  // once the initial loop is complete, the top of the stack should hold the highest exponent
	  top.link = sortPoly(top.link);
	  // call the function again, starting with the second node in the stack
	  return top;
  }
	  
	  
  public void swapPoly(PolyNode node1, PolyNode node2) {
	  // simple method to swap values between nodes
	  	int tempCo = node1.getCoefficient();
		int tempExp = node1.getExponent();
		node1.setCoefficient(node2.getCoefficient());
		node1.setExponent(node2.getExponent());
		node2.setCoefficient(tempCo);
		node2.setExponent(tempExp);
	}
  
  public PolyNode combineTerms(PolyNode top) {
	  // NOTE: This only works if the list has been sorted first!!
	  // assuming all like terms are adjacent in the stack, this method recursively adds like terms and removes nodes with duplicate exponents
	  if (top == null || top.link == null) {
		  return top;
	  }
	  while (top.link != null && top.getExponent() == top.link.getExponent()) {
		  // if the current node's exponent is the same as the next node's exponent, add coefficients together and remove the next node from the stack
		  top.setCoefficient(top.getCoefficient() + top.link.getCoefficient());
		  top.setLink(top.link.link);
	  }
	  if(top.link != null) {
	  combineTerms(top.link);
	  // call the function again on the next term in the stack
	  }
	  return top;
  }
}

