/*
James Morgan
Case errors, bracket placement errors
Constant double incorrect, Area and circumference methods incorrect
AreA should only be corrected to areA, double bracket at end should be fixed
*/

public class James_Multiple1{
   static double pi = 3.14159 ;

   public static void main(String[] args){
      int Radius = 3 ;

      if(args.length == 0){
         System.out.println(AreA(Radius));
         System.out.println(Circumference(Radius));}
      else if(args[0].equals("area")){
         System.out.println(AreA(Radius));}
      else if(args[0].equals("circumference"))
      {
         System.out.println(Circumference(Radius));
         }
      else{
         System.out.println("invalid option");}
         }

   public static double AreA(float radius){
      return pi * radius * radius ;}

   public static double  Circumference(float radius){
      return pi * radius * 2 ;
}}
