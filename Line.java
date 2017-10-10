public class Line {

   private String line ;
   private String[] words ;
   private int indentation ;             // indentation level, should be increased every time there is a new body
   private int lineNum ;                 // line num, the number of the current line being checked
   
   Line(String s, int number){
      line = s; 
      words = line.split("\\s+");
      lineNum = number;      
   }
   
   // Spaces around operators
   public boolean operators () {
        for(int i=0; i < line.length(); i++) {
           // checks for unary operators
           if(line.charAt(i) == '*' || line.charAt(i) == '+' || line.charAt(i) == '-' || line.charAt(i) == '/') {
              // Makes sure they are only an operator
              if(line.charAt(i+1) == '+' || line.charAt(i+1) == '=' || line.charAt(i+1) == '-' || line.charAt(i+1) == ';' || line.charAt(i+1) == '.') 
              {
                 return true; 
              }
              else if(line.charAt(i-1) == ' ' && line.charAt(i+1) == ' ') 
              {
                 return true;
              }
              else
              {
                 return false;
              }
           }
        }
        return true;
   }
   
   // Explicit Operator precedence
   public boolean precedence () {
      
      return true;     
   }
   

   
   // Class name case
   // Class names begin with an uppercase letter; method names begin with a lowercase letter, 
   // constants are all uppercase.  Camel case is ignored (and therefore underscores are ignored in constants).
   public boolean caseNames() 
   {
      
      int locate = declared();               // location of the name declaration in line
      

      
      if(locate < 0)
      {
         return true;
      }
      
      
      if (words[locate].equals("class"))
      {
         locate++;
         if(Character.isUpperCase(words[locate].charAt(0))) // Check first character of word after class
         {
            return true;
         }
         else
         {
            return false;
         }                    
      }
      else if (words[locate].equals("final"))
      {
         while(!words[locate].contains("=") && !words[locate].contains(";")) // find '=' sign for declaration or end
         {
            locate++;
         }
         
         if(words[locate].charAt(0) == '=' || words[locate].charAt(0) == ';') // check for space before '=' sign  
         {
         locate--;          // location of name
         }
         
         String name = words[locate]; // name of constant
         
         for(int i = 0; i < name.length() && name.charAt(i) != '='; i++)
         {
            if(Character.isLowerCase(name.charAt(i)))
            {
               return false;
            }
         }
         
         return true;       // name is all uppercase
         
      }
      else if (words[locate].toLowerCase().equals("public") || words[locate].toLowerCase().equals("private") )
      {
         while(!words[locate].contains("(")) // find '=' sign for declaration
         {
            locate++;
         }
         
         if(words[locate].charAt(0) == '(') // check for space before arguments 
         {
         locate--;          // location of name
         }
         
         if(Character.isLowerCase(words[locate].charAt(0)))
         {
            return true;
         }
         else
         {
            return false;
         }
      }
      
         return true; // not a method, constant or class.
      
   }
   
   
   // Helper function to determine if line is a constant declaration or class/function declaration
   private int declared() 
   {
      int location = -1;
      for(int i = 0; i < words.length; i++) 
      {
         if(words[i].toLowerCase().equals("class"))
         {
            location = i;
         }
         else if(words[i].toLowerCase().equals("final"))
         {
            location =  i;
         }
         else if(words[i].toLowerCase().equals("public") || words[i].toLowerCase().equals("private") )
         {
           if(line.contains("="))
            {
               location =  -1;
            }
            else
            {
               location =  i;
            }
         }
      }
      
      return location;
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
   
   public String getText() {
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
      char[] chars = line.toCharArray();
      for(int i = 0; i < words.length; )
      {
         if(chars[i] == check ) 
         {
            return true;
         }
      }
         
      
      return false;
      
   }
   
   public String[] getWords() {
      return words;
   }
}
