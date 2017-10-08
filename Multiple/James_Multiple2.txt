/*
James Morgan
Indentation errors, optional braces errors, binary operator space errors.
No indentaion in main class, random indentaion in area and circumference classes
Otional braces removed in if/else cascade
no spaces around operators in area and circumference methods
*/

public class James_Multiple2
{
static double PI=3.14159 ;

public static void main(String[] args)
{
int radius = 3 ;

if(args.length == 0)
{
System.out.println(area(radius));
System.out.println(circumference(radius));
}
else if(args[0].equals("area"))

System.out.println(area(radius));

else if(args[0].equals("circumference"))

System.out.println(circumference(radius));

else

System.out.println("invalid option");


}

               public static double area(float radius)
           {
                               return PI*radius*radius ;
           }

                public static double  circumference(float radius)
    {
                          return PI*radius*2 ;
 }

}
