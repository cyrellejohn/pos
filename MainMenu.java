import java.util.Scanner;
import javax.swing.JOptionPane;

public class MainMenu {
   private static final String N = System.getProperty("line.separator");
   
   public static void main(String [] args) {
      MainMenu menu = new MainMenu();
      menu.run();
   }
   
   public void run() {
      int menuOption;
      Scanner input = new Scanner(System.in);
      Inventory inv = new Inventory();
      Sales sales = new Sales();
      Transaction trans = new Transaction();
      Login login = new Login();

      System.out.println(N + String.format("%63s", "MAIN MENU") + N + N);
      System.out.println(String.format("%18s %19s %26s %20s %26s", "[1]INVENTORY", "[2]SALES", "[3]TRANSACTION", "[4]EXIT", "[5]LOG OUT") + N + N);
      System.out.print("Select: ");
      while(!input.hasNextInt()) {
         System.out.print("Select: ");
         input.next();
      }
      menuOption = input.nextInt();
      
      if(menuOption == 1) {
         inv.run();
      }
      
      else if(menuOption == 2) {
         sales.run();
      }
      
      else if(menuOption == 3) {
         trans.run();
      }
      
      else if(menuOption == 4) {
         JOptionPane.showMessageDialog(null, "Thank you for using the program!");
         System.exit(0);
      }
      
      else if(menuOption == 5) {
         JOptionPane.showMessageDialog(null, "Admin log out");
         login.run();
      }
      
      else {
         System.out.println("Try again!");
         run();
      }
   }
}