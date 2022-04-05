/*
    name : mohanad ayman ibrahim khaled 
    ID : 20100259
    name : khaled ezz eldin younis
    ID : 20100252
*/
import java.util.* ;
import java.io.*;
public class Main {
   public static void main ( String[] args){
      AVLTree tree = new AVLTree();
      File file = new File("data.txt");
         try {
            FileReader reader = new FileReader(file);
            BufferedReader input = new BufferedReader(reader) ;
            
            try{
               String data = input.readLine();
               while (data != null ){
                  String[] s = data.split(" > ");
                  data = input.readLine() ;
                  tree.insertTreeNode(s);
               }
               input.close();
            }
            catch(IOException o){
               System.out.println("file is empty");
            }
         }catch (FileNotFoundException e ){
            System.out.println("file not found");
         }

         System.out.println("\"Indexing Websites\"");
         System.out.println("Your file has been loaded :) ");

         System.out.println("~.~.~.~.~.~.~  MENU  ~.~.~.~.~.~.~");
         System.out.println("1 - Display the full index");
         System.out.println("2 - Searc for URL ");
         System.out.println("3 - Search for an IP address");
         System.out.println("4 - Add an extra page ");
         System.out.println("5 - exit");
         System.out.println("~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~");

         Scanner input = new Scanner(System.in);
         System.out.print("option : ");
         int answer = input.nextInt();

         while (answer != 5){
            switch(answer){
               case 1 : 
                  tree.inorder();
                  break ;
               case 2 : 
                  System.out.println("Enter page URl : ");
                  String page = input.next();
                  if ( tree.searchForURl(page) == null ){
                     System.out.println("Page not found :( ");
                  }
                  else {
                     tree.searchForURl(page).value.display();
                  }
                  System.out.println("~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~");
                  break ;
               case 3 :
                  Boolean x = true ;
                  while (x == true){
                     System.out.println("Enter IP address : ");
                     String IP = input.next();
                     String regex = "(\\d{1,2}|(0|1)\\d{2}|2[0-4]\\d|25[0-5])"; 
                     String pattern = regex + "\\."+ regex + "\\."+ regex + "\\."+ regex; 
                     if(IP.matches(pattern)){
                        if ( tree.searchForIP(IP) == null ){
                           System.out.println("IP not found :( ");
                        }
                        else {
                           tree.searchForIP(IP).value.display();
                        }
                        x = false;
                     }
                     else{
                        System.out.println("IP has to in this format : xxx.xxx.xxx.xxx");
                        x = true ;
                     }
                  }
                  break ;

               case 4 : 
                  String[] NewPage = new String[2] ;
                  System.out.print("enter page URL : ");
                  NewPage[0] = input.next() ;
                  System.out.println("enter page IP : ");
                  NewPage[1] = input.next() ;
                  tree.insertTreeNode(NewPage);
                  
                  System.out.println("~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~");
            }

            System.out.println("~.~.~.~.~.~.~  MENU  ~.~.~.~.~.~.~");
            System.out.println("1 - Display the full index");
            System.out.println("2 - Searc for URL ");
            System.out.println("3 - Search for an IP address");
            System.out.println("4 - Add an extra page ");
            System.out.println("5 - exit");
            System.out.println("~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~");
            
            System.out.print("option : ");
            answer = input.nextInt();

         }
         input.close();

   }
}

