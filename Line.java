public class Line {

   private String line ;
   private char[] words ;
   private int indentation ;             // indentation level, should be increased every time there is a new body
   private int lineNum ;                 // line num, the number of the current line being checked
   
   Line(String s, int number){
      line = s; 
      words = line.toCharArray();
      lineNum = number;      
   }
   
   // Spaces around operators
   public boolean operators () {
        for(int i=0; i < line.length(); i++) {
           if(line.charAt(i) == '*' || line.charAt(i) == '+' || line.charAt(i) == '-' || line.charAt(i) == '/') {
              if(line.charAt(i-1) == ' ' && line.charAt(i+1) == ' ') {
                 return true;
              }
           }
        }
        return false;
   }
   
   // Explicit Operator precedence
   public boolean precedence () {
      
      return true;     
   }
   

   
   // Class name case
   // Class names begin with an uppercase letter; method names begin with a lowercase letter, 
   // constants are all uppercase.  Camel case is ignored (and therefore underscores are ignored in constants).
   public boolean caseNames () 
   {
      
      int locate = 0;               // location of the name declaration in line
      boolean variable = isVariable();          // boolean whether it's a variable declaration
      
      for(int i=0; i < line.length() ; i++) 
      { // Find the location of the name declaration
         if(variable) 
         {
            if(words[0] == '=') { // If there is a space before =
               locate = i-1;
               break;
            }
            else {              // If there is not a space before =
               locate = i;
               break;
            }
            
         }
         else
         {
            // If There is a space before the arguments, or class declaration
            if(words[0] == '(' || words[( words.length - 1) ] == '{' || i == line.length()-1 ) { 
               locate = i-1;
               break;
            }
            else {                  // No space between method name and argument
               locate = i;
               break;
            }
         }

      } // end for, word location set.
         
      if(variable && words.length == 0) 
      {
         return Character.isLowerCase(words[locate]);
      }
      else 
      {
         return Character.isUpperCase(words[locate]);
      }     
   }
   
   
   // Helper function to determine if line is a variable declaration or class/function declaration
   private boolean isVariable() 
   {
      if(words.length == 0)
      {
         return false;
      }
      if(contains(';') || words[words.length -1 ] == ',') {
         return true;
      }
      else 
      {
         return false;
      }
   }
   
   // Getters and Setters
   
   public void setIndent(int level) {
      indentation = level;
   }
   
   public int getIndent() {
      return indentation;
   }
   
   public void setLine(String text) {
      line = text;
   }
   
   public String getLine() {
      return line;
   }
   
   public int getNum() {
      return lineNum;
   }
   
   public char getChar(int position) {
      if(line.length() > position)
      {
         return line.charAt(position);
      }

      return '@';
   }

   
   public boolean contains(char check) {
      for(int i = 0; i < words.length; )
      {
         if(words[i] == check ) 
         {
            return true;
         }
      }
         
      
      return false;
      
   }
}
