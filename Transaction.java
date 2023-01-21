import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Transaction {
   private static final String N = System.getProperty("line.separator");
   MainMenu menu = new MainMenu();

   public static void main(String [] args) {
      Transaction tran = new Transaction();
      tran.run();
   }
   
   public void exit() {
      menu.run();
   }
   
   public void run() {
      int transOption;
      
      TransMethod transMethod = new TransMethod();
      Method method = new Method();
      Scanner transInput = new Scanner(System.in);
      transMethod.setFindDir("inventory.txt");
      File file = new File(transMethod.getFindDir());
      transMethod.setFindDir("orderinfo.txt");
      File orderInfoDir = new File(transMethod.getFindDir());
      
      try {
      
         if(!file.exists())
            file.createNewFile();
            
         if(file.length() == 0)
            showMessage();
         
         else {
            System.out.println(N + String.format("%52s", "I T E M L I S T"));
            transMethod.firstHeader();
            method.readAllLine(file);
         }
      
         transMethod.secondHeader();
         System.out.print("Select: ");
         while(!transInput.hasNextInt()) {
            System.out.print("Select: ");
            transInput.next();
         }
         transOption = transInput.nextInt();
      
         if(transOption == 1) {
            transMethod.add(file);
            run();
         }
         
         else if(transOption == 2) {
            int option;
            Scanner input = new Scanner(System.in);
            
            if(!orderInfoDir.exists())
               orderInfoDir.createNewFile();    
            if(orderInfoDir.length() == 0) {
               System.out.println(N + String.format("%58s", "No current orders"));
                 
            }
            else {
               System.out.println(N + String.format("%68s", "O R D E R I N F O") + N);
               transMethod.readAllLine(orderInfoDir);
               System.out.println(N + String.format("%95s", "T O T A L : " + transMethod.getTotalCost()) + N);
            }
         
            System.out.println(N + N + String.format("%28s %23s %23s %22s", "[1]", "[2]", "[3]", "[4]"));
            System.out.println(String.format("%28s %23s %23s %22s", "Add Order", "Delete Order", "Finalize Order", "Exit"));
         
            System.out.print("Select: ");
            while(!input.hasNextInt()) {
               System.out.print("Select: ");
               input.next();
            }
            option = input.nextInt();
         
            if(option == 1) {
               System.out.println(N + String.format("%52s", "I T E M L I S T") + N);
               method.readAllLine(file);
               System.out.println(N);
               transMethod.add(file);
            }
            
            else if(option == 2) {
               transMethod.delete(orderInfoDir);
               run();
            }
            
            else if(option == 3) {
               double totalCost = transMethod.getTotalCost();
               Scanner inputFinalize = new Scanner(System.in);
               double change;
               double cash;
               
               System.out.println(N + "T O T A L : " + totalCost);
               System.out.print(N + "C A S H : ");
               cash = inputFinalize.nextDouble();
               change = cash - totalCost;
               System.out.println(N + "C H A N G E : " + change);
               method.setFindDir("orderinfo.txt");
               File orderInfo = new File(method.getFindDir());
               transMethod.setSales(orderInfo);
               orderInfo.delete();
            }
            
            else if(option == 4) 
               run();
            
            else {
               System.out.println("Try again!");
               run();
            }
         }
         
         else if(transOption == 3)
            exit();
         
         else {
            System.out.println("Try again!");
            run();
         }
      }
      catch(Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
      
   }

   public void showMessage() {
      Inventory inv = new Inventory();
      JOptionPane.showMessageDialog(null, "Current Inventory is empty." + N + "Transferring.");
      inv.run();
   }
}