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
   public boolean braces (int num) {
      
      String trimmed = instructions.get(num).getText().trim();
      // If end of body
      if(trimmed.contains("}")) {
        return true;
      }
      
      if(isBody(num))
      {   
         String next = instructions.get(num+1).getText().trim();
         // Brace on Same Line
         if( trimmed.contains("{"))
         {
            return true;
         }
         // Brace on next line         
         else if(next.contains("{"))
         {
            return true;
         }
         return false;
      }
      return true;
   }

   // Method: alignment
   // Deals with: Brace Alignment
   // Braces must be on their own line, and aligned with the above line
   // Arguments: line line of code with brace on it
   public boolean alignment (Line line) {
      if(line.getText().trim().length() > 1) 
      {
         return false;
      }
      else if(!checkIndent(line.getNum()-1))
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
   public boolean checkIndent (int num) {
      Line line = instructions.get(num);
      if(line.getText().isEmpty())
      {
         return true;
      }
      for(int i = 0; i < line.getIndent() ; i++) {
         if(line.getChar(i) != ' ') // check for not enough spaces
         {
            return false;
         }
      }
      if(line.getChar(indent) == ' ')
      {
         return false;
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
      Line current = instructions.get(num);     
      if(isBody(num))       // New body, increase indent after this
         {
           current.setIndent(indent);
           indent += 3; 

         }
      else if(isEnd(num)) // End of body, reduce indent after 
      {
         indent -= 3;
         current.setIndent(indent);     
      }
      else // No change in indent.
      {
         current.setIndent(indent);
      }
               
   }
   
   // Method: isBody
   // Determine if the line of code indicates a new body
   private boolean isBody(int num) 
   {
      String line = instructions.get(num).getText(); 
      // Ignore comments, empty lines and end of body
      if(line.trim().startsWith("//") || line.contains("*/") || line.trim().startsWith("/*") || line.contains("}") || line.trim().isEmpty())
      {
         return false;
      }

      // if new open bracket, it's a new body
      if(line.trim().contains("{"))
      {
         return true;
      }
      // all types of loops, extra long gross line. 
      else if(line.trim().startsWith("if") || line.trim().startsWith("else") || line.trim().startsWith("while") || line.startsWith("do") || line.trim().startsWith("for") || line.trim().contains("class ") ) 
      {
         if(isBody(num+1))  // next line has open bracket, change indent later
         {
            return false; 
         }
         return true;
      }
      // method bodies, don't end with ; and not blank
      else if( (!line.trim().endsWith(";") || !line.trim().endsWith(","))  && line.isEmpty() ) { 
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
      
      String line = instructions.get(num).getText(); 
      String prev = instructions.get(num-1).getText();
      if(line.contains("}"))
         {
            return true;
         }
      else if(prev.trim().startsWith("if") || prev.trim().startsWith("else") || prev.trim().startsWith("while") || prev.startsWith("do") || prev.trim().startsWith("for") )
      {
         if(!prev.contains("{") || !line.contains("{")) 
         {
            return true;
         }
      }
      
      return false;
   }
    
    // Method: blanks
    // Deals with: Blank spaces between methods
   // Parameters: num - line number to check
    public boolean blanks(int num) {
       String line = instructions.get(num).getText().trim();
       
       if(isEnd(num-1) && line.length() > 0) {
          return false;
       }
       
      return true; 
       
    }
    
    // Multiple variable declarations
    // Checks if a line has multiple variable declarations 
    public boolean multipleVariable(int number) 
    {
       String line = instructions.get(number).getText();  // covers multiple declarations with newlines. 
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
    
    

}