//----------------------------------------------------------------------------
// LLNode.java                by Dale/Joyce/Weems                    Chapter 2
//
// Implements <T> nodes for a Linked List.
//----------------------------------------------------------------------------
package question1;

public class PolyNode
{
  protected PolyNode link;
  protected int coefficient;
  protected int exponent;
  
  public PolyNode(int coefficient, int exponent)
  {
    this.coefficient = coefficient;
    this.exponent = exponent;
    link = null;
  }
 
  public void setCoefficient(int newCo){ this.coefficient = newCo;}
  public int getCoefficient(){ return coefficient; }
  public void setExponent(int newExp){ this.exponent = newExp;}
  public int getExponent(){ return exponent; }
  public void setLink(PolyNode link){this.link = link;}
  public PolyNode getLink(){ return link;}
}
 
 