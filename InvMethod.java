import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class InvMethod {
   private String itemName;
   private int quantity;
   private String save;
   private String line;
   private double price;
   
   Scanner input = new Scanner(System.in);
   private final String BACK = "0";
   private String delim = ",";
   private static final String N = System.getProperty("line.separator");
   private int lineCount = 0;
   
   public void createContentOf(File dir) {
      try {
         FileWriter fw = new FileWriter(dir, true);
         BufferedWriter bw = new BufferedWriter(fw);
         System.out.print("Item Name: ");
         itemName = input.nextLine();
         itemName = itemName.trim();
         
         while(!itemName.equals(BACK)) {
         
            System.out.print("Quantity: ");
            while(!input.hasNextInt()) {
               System.out.print("Quantity: ");
               input.next();
            }
            quantity = input.nextInt();
            
            System.out.print("Price: ");
            while(!input.hasNextDouble()) {
               System.out.print("Price: ");
               input.next();
            }
            price = input.nextDouble();
            
            save = itemName + delim + quantity + delim + price;
            bw.write(save, 0, save.length());
            bw.newLine();
            input.nextLine();
           
            System.out.print(N + "Item Name (" + BACK + " to exit): ");
            itemName = input.nextLine();
            itemName = itemName.trim();
         }
         bw.close();
      }
      catch(Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
   }
   
   public void deleteItem(File dir, int itemNum) {
      Method method = new Method();
      
      try {
         BufferedReader br = new BufferedReader(new FileReader(dir));
         method.setFindDir("temp.txt");
         File temp = new File(method.getFindDir());
         FileWriter fw = new FileWriter(temp);
         
         while ((line = br.readLine()) != null) {
            lineCount++;
            if(lineCount == itemNum) {
            
            }
            else {
               fw.write(line + N); 
               fw.flush(); 
            }
         }
         br.close();
         fw.close(); 
      }
      catch(Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
      method.replaceFile(dir);
   }
}