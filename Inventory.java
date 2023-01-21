import java.io.File;
import java.util.Scanner;

public class Inventory {
   private static final String N = System.getProperty("line.separator");

   public static void main(String [] args) {
      Inventory inv = new Inventory();
      inv.run();
   }
   
   public void run() {
      int invOption;
      
      Method method = new Method();
      method.setFindDir("inventory.txt");
      File file = new File(method.getFindDir());
      Scanner invInput = new Scanner(System.in);
      InvMethod invMethod = new InvMethod();
      MainMenu menu = new MainMenu();
      
      try {
      
         if(!file.exists())
            file.createNewFile();
            
         if(file.length() == 0) 
            System.out.println(N + String.format("%58s", "Current inventory is empty"));
            
         else {
            method.firstHeader();
            method.readAllLine(file);
         }
         
         method.secondHeader();
         System.out.print("Select: ");
         while(!invInput.hasNextInt()) {
            System.out.print("Select: ");
            invInput.next();
         }
         invOption = invInput.nextInt();
      
         if(invOption == 1) {
            invMethod.createContentOf(file);
            System.out.println();
            run();
         }
         
         else if(invOption == 2) {
            method.delete(file);
            run();  
         }
         
         else if(invOption == 3) {
            menu.run();
         }
         
         else 
            run();
      }
      catch(Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
   }
}