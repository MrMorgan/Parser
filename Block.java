import java.util.ArrayList;

public class Block{
   
   private ArrayList<Line> instructions;     // ArrayList of each line of the current block
   private int indent;                       // points to current line number in block
   
   // Separate lines by semi-colons
   // Separates blocks by { }
   
   Block(){
      instructions = new ArrayList<Line>(); 
      indent = 0;
   }
   
   
   // getter and setter to add lines to block
   public void addLine(String s, int num) {
      Line current = new Line(s,num);
      instructions.add(current);     
   }
   
   public Line getLine(int num) {
      return instructions.get(num);
   }
   
   // to return size of block
   public int getSize() {
      return instructions.size();
   }
   
   // Method braces
   // Deals with optional curly braces
   // Check to see if line is an optional curly brace
   // Parameters: line - line to check if it is an optional curly brace
   // Assumption: Line is the beginning of a body. 
   public boolean braces (Line line) {
      Line next = instructions.get(line.getNum());
      if(next.getLine().trim() != "}") {
         return false;
      }
      
     return true;     
   }

   // Method: alignment
   // Deals with: Brace Alignment
   // Braces must be on their own line, and aligned with the above line
   // Arguments: line line of code with brace on it
   public boolean alignment (Line line) {
      if(line.getLine().trim().length() > 1) 
      {
         return false;
      }
      else if(!checkIndent(line))
      {
         return false;
      }
      else
      {
         return true;
      }
   }
   
   // Method checkIndent
   // Deals with: Block Indentation
   // Checks if the line is correctly indented
   // Arguments: line - line which is being checked
   
   private boolean checkIndent (Line line) {
      for(int i = 0; i < line.getIndent() ; i++) {
         if(line.getChar(i) != ' ') 
         {
            return false;
         }
      }
      
      return true;
      
   }
   
   // Method: indentation
   // Deals with: Block indentation
   // Sets the correct number of spaces for the line: 
   // Can only be used after full code is in block.
   // class bodies, method bodies, loop bodies, if-else bodies, instance declarations.
   // Arguments: line - the line of code which is being set
   public void findIndent (int num) {
      if(isBody(num))
         {
           indent += 3; 
           System.out.println("New indent: "+num);
         }
      else if(isEnd(num))
      {
         indent -= 3;
         System.out.println("New indent: "+num);
      }
      instructions.get(num).setIndent(indent);
               
   }
   
   // Method: isBody
   // Determine if the line of code indicates a new body
   // Changes indentation level if it is a new body.
   private boolean isBody(int num) 
   {
      String line = instructions.get(num).getLine(); 
      // if-else and loops
      if(line.startsWith("if ") || line.startsWith("while ") || line.startsWith("do ") || line.startsWith("for ") || line.startsWith("class ") ) 
      {
         return true;
      }
      // class and method bodies
      else if(line.trim().endsWith("{")) { 
         return true;
      }
      else
      {
      return false;  
      }
      
   }
   
   
   // Method: isEnd
   // Deals with: Multiple 
   // Checks to see if line is the end of a block
   private boolean isEnd(int num) {
      
      Line prev = instructions.get(num); 
      String line = prev.getLine();
      
      if(line.startsWith("if ") || line.startsWith("while ") || line.startsWith("do ") || line.startsWith("for ") && !line.contains("}") )
      {
         return true;
      }
      else if(line.contains("}"))
      {
         return true;
      }
      return false;
   }
    
    // Method: blanks
    // Deals with: Blank spaces between methods
   // Parameters: num - line number to check
    public boolean blanks(int num) {
       String line = instructions.get(num-1).getLine().trim();
       
       if(isEnd(num-1) && line.length() > 0) {
          return false;
       }
       
      return true;
       
    }
    
    // Multiple variable declarations
    // Checks if a line has multiple variable declarations 
    public boolean multipleVariable(int number) 
    {
       String line = instructions.get(number).getLine();  // covers multiple declarations with newlines. 
       if(line.startsWith("byte") ||         // not string or character declaration
          line.startsWith("short") || 
          line.startsWith("int") ||
          line.startsWith("long") ||
          line.startsWith("float") ||
          line.startsWith("double") ||
          line.startsWith("boolean")) {
          // If it contains a comma, it contains multiple declarations.s
          if(line.contains(",")) {        
             return false;
          }
          else {
             return true;
          }
       }
       else if(line.startsWith("String") ||    // String or character declaration
               line.startsWith("char"))
       { 
          boolean quoted = false;
          for(int i = 0; i < line.length() ; i++ ) {
             if(quoted) {
                
             }
             else if(line.charAt(i) == ',') {
                return false;
             }
          }
          return true;
       }
      else
      {
      return true; 
      }
    }
    
    
    // ReturnsString: A full line of code ignoring newlines
    // Parameter: Starting: Line number of code.
 /*   private String singleLine(int starting){
       Line single = instructions.get(starting);
       String words = single.getLine();
       while(!single.contains(';') || !single.contains('{') || !single.contains('}')) 
       {
          words += instructions.get(starting).getLine();        
          starting++;
       }
       return words;
    }
*/   
}