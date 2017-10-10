   
import java.util.*;
import java.io.*;

public class Parser {

   public static void main(String[] args) {
      
		if(args.length < 1) 
		{
		   System.out.println("No File");
		   System.exit(0);
		}

		String filename = args[0];
		Block code = new Block();
		
		File f = new File(filename);
	    Scanner scan = null;
         try {
            scan = new Scanner(f);
         } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Invalid File Name");
            e.printStackTrace();
         }
         
	    int maxLine = 1;
        
	      // Add all lines to code Array
	    while(scan.hasNextLine()) {
	        String next = scan.nextLine();
	           
   	       code.addLine(next, maxLine);
   	       maxLine++;
	      }
	      maxLine--;           // increments 1 too many times.
	    
	      scan.close();
	    
	      // Output header
          String error = "Error at Line number "; 
          
          System.out.println("Style report by Jimmy Morgan");
          
          int comment = 0;                          // first comment
          Line current = code.getLine(comment);
          
          // Assumes code is commented by assignment specifications.
          // Gets program Author and error types in file
          if(current.getText().startsWith("/*"))
          {
             comment++;   
             current = code.getLine(comment);
             System.out.println("Test program author: "+current.getText());
             comment++;
             current = code.getLine(comment);
             System.out.println("Error(s) checked: "+ current.getText());
             System.out.println("Style errors found:");
             
             while(!current.getText().contains("*/"))
             {
                comment++;
                current = code.getLine(comment);
             }
             comment++;
          }
          
           
          // Check each read line for errors
          for(int i = comment; i < maxLine ; i++ ) 
          {
             
             current = code.getLine(i);
             
             // Pass comments that shouldn't be there 
             if(current.getText().trim().startsWith("/*"))
             {
                while(!current.getText().contains("*/"))
                {
                   i++;
                   current = code.getLine(i);
                }
                i++;
             }
             
             
             if(current.getText().trim().isEmpty()) { // Ignore empty 
                
             }
             if(!current.getText().trim().startsWith("//"))
             {
                code.findIndent(i);   
                //if(!code.braces(i)) {
                //  System.out.println(error+i+": No Optional Braces");
                //}
                if(!code.getLine(i).caseNames())
                {
                   System.out.println(error+current.getNum()+": Invalid Name Capitalization");
                }
                if(!code.getLine(i).operators())
                {
                   System.out.println(error+current.getNum()+": Operators must be padded with spaces");
                }
                if(!code.braces(i)) 
                {
                   System.out.println(error + current.getNum() + ": Optional Braces are Mandatory");
                }
                if(!code.checkIndent(i))
                {
                   System.out.println(error + current.getNum() + ": Indentation is incorrect");
                }
             
             
             }


       }   

	}
   
   
   // Method populate
   // Method to create a new Block and add lines 
   // A line is not delineated by newline, but by either semi-colon or curly braces
   // Parameter: name - User inputed name of file
   
  /* private static ArrayList<Line> populate (String name) throws FileNotFoundException {
      File f = new File(name);
      Scanner scan = new Scanner(f);
      int lineNumber = 1;
      Line line;
      ArrayList<Line> chunk = new ArrayList<Line>();
      
      // Add all lines to code Array
      while(scan.hasNextLine()) {
         String next = scan.nextLine();
         line = new Line(next, lineNumber++);
         chunk.add(line);
      }
      
      scan.close();
      return chunk;
      
   }
*/



	
	
}
