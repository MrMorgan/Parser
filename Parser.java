   
import java.util.*;
import java.io.*;

public class Parser {

   public static void main(String[] args) {
      
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("File name:");
		String filename = keyboard.next();
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
      
	    int maxLine = 0;
        
	      // Add all lines to code Array
	    while(scan.hasNextLine()) {
	        String next = scan.nextLine();
	           
   	       code.addLine(next, maxLine);
   	       maxLine++;
	      }
	    
	      System.out.println("Max: "+maxLine);
	      scan.close();
	      keyboard.close();
	    
       String error = "Error at Line number "; 
       
       for(int i = 0; i < maxLine ; i++ ) {
          
          Line current = code.getLine(i);

          
          
          if(current.getLine().startsWith("/*"))
          {
             while(!current.getLine().contains("*/"))
             {
                i++;
                current = code.getLine(i);
             }
             i++;
          }
          if(!current.getLine().startsWith("//"))
          {
          code.findIndent(i);   
          //if(!code.braces(current)) {
          //  System.out.println(error+i+": No Optional Braces");
          //
          if(!code.getLine(i).caseNames())
          {
             System.out.println(error+i+": Invalid Name Capitalization");
          }
          
          
          }

       }   

	}
   
   
   // Method populate
   // Method to create a new Block and add lines 
   // A line is not delineated by newline, but by either semi-colon or curly braces
   // Parameter: name - User inputed name of file
   
   private static ArrayList<Line> populate (String name) throws FileNotFoundException {
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
	



	
	
}
