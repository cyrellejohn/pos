import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class TransMethod extends Method {
   private String line;
   private int lineCount;
   private static final String N = System.getProperty("line.separator");

   @Override
   public void delete(File file) {
      int orderNum;
      
      System.out.print("Enter order number: ");
      orderNum = input.nextInt();
            
      while(orderNum != 0) {
         deleteOrder(file, orderNum);
               
         if(file.length() == 0)
            break;
                  
         else {
            firstHeader();
            readAllLine(file);
            System.out.println(N + String.format("%95s", "T O T A L : " + getTotalCost()) + N);     
         }
         System.out.print(N + N + "Enter order number or 0 to exit: ");
         orderNum = input.nextInt();
      }
   }

   @Override
   public void firstHeader() {
      System.out.println(N + String.format("%26s %22s %22s", "ITEM NAME", "QUANTITY", "PRICE") + N);
   }
   
   @Override
   public void readAllLine(File dir) {
      String count;
      String numberFormat;
      String item;
      String quantity;
      String price;
      String itemFormat;
      String cost;
   
      try {
         BufferedReader br = new BufferedReader(new FileReader(dir));
         line = br.readLine();
         System.out.println(N + String.format("%26s %22s %22s %22s", "ITEM", "QUANTITY", "PRICE", "COST") + N);
         
         while(line != null) {
            lineCount++;
            count = "[" + lineCount + "]";
            numberFormat = String.format("%7s", count);
            
            String arr[] = line.split(",");
            item = String.join("", arr[0]);
            quantity = String.join("", arr[1]);
            price = String.join("", arr[2]);
            cost = String.join("", arr[3]);
            
            itemFormat = String.format("%19s %22s %22s %22s", item, quantity, price, cost);
            System.out.println(numberFormat +  itemFormat);
            line = br.readLine();
         }
         br.close();
      }
      catch(Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      } 
   }
   
   @Override
   public void secondHeader() {
      System.out.println(N + N + String.format("%31s %18s %21s", "[1]", "[2]", "[3]"));
      System.out.println(String.format("%31s %18s %21s", "Add Order", "Order Info", "Exit"));
   }
   
   public void add(File dir) {
      int itemNum;
      int quantity;
      setLineCount(dir);
         
      System.out.print("Item number: ");
      itemNum = input.nextInt();
      while(itemNum > getLineCount()) {
         System.out.print("Item number: ");
         itemNum = input.nextInt();
      }
         
      while(itemNum != 0) {
         System.out.print("Quantity: ");
         quantity = input.nextInt();
         
         addOrder(dir, itemNum, quantity);
               
         if(dir.length() == 0)
            break;
                  
         else {
            firstHeader();
            super.readAllLine(dir);     
         }
      
         System.out.print(N + N + "Item number (0 to exit): ");
         itemNum = input.nextInt();
         
         while(itemNum > getLineCount()) {
            System.out.print("Item number (0 to exit): ");
            itemNum = input.nextInt();
         }
      }
   }
   
   public void addOrder(File dir, int itemNum, int quantity) {
      int origQuan;
      double price;
      String item;
      String origQuanString;
      String remainQuanString;
      int remainQuan;
      double cost;
      int lineCount = 0;
      Transaction tran = new Transaction();
      
      try {
      
         BufferedReader br = new BufferedReader(new FileReader(dir));
         setFindDir("temp.txt");
         File temp = new File(getFindDir());
         FileWriter fw = new FileWriter(temp);
         
         while ((line = br.readLine()) != null) {
            lineCount++;
            if(lineCount == itemNum) {
               String arr[] = line.split(",");
               item = String.join("", arr[0]);
               origQuan = Integer.parseInt(arr[1]);
               price = Double.parseDouble(arr[2]);
               
               while(quantity > origQuan) {
                  System.out.println("Not enough stocks!" + N + "Try again!");
                  System.out.print("Quantity (0 to exit): ");
                  quantity = input.nextInt();
                  
                  if(quantity == 0) 
                     tran.run();
                  
               }
               remainQuan = origQuan - quantity;
               cost = quantity * price;
               addOrderInfo(item, quantity, price, cost);
               origQuanString = Integer.toString(origQuan);
               remainQuanString = Integer.toString(remainQuan);
               fw.write(line.replace(origQuanString, remainQuanString) + N);
               fw.flush();  
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
      replaceFile(dir);
   }
   
   public void addOrderInfo(String item, int quantity, double price, double cost) {
      String save;
      String delim = ",";
      
      setFindDir("orderinfo.txt");
      File orderInfoDir = new File(getFindDir());

      try {
         FileWriter fw = new FileWriter(orderInfoDir, true);
         BufferedWriter bw = new BufferedWriter(fw);
         
         save = item + delim + quantity + delim + price + delim + cost;
         bw.write(save, 0, save.length());
         bw.newLine();
         bw.close();
      }
      catch(Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
   }
   
   public void deleteOrder(File dir, int itemNum) {
      int lineCount = 0;
      String itemName;
      int quantity;
      String price;
      
      try {
         BufferedReader br = new BufferedReader(new FileReader(dir));
         setFindDir("temp.txt");
         File temp = new File(getFindDir());
         FileWriter fw = new FileWriter(temp);
         
         setFindDir("inventory.txt");
         File invDir = new File(getFindDir());
         while ((line = br.readLine()) != null) {
            lineCount++;
            if(lineCount == itemNum) {
               String arr[] = line.split(",");
               itemName = String.join("", arr[0]);
               quantity = Integer.parseInt(arr[1]);
               price = String.join("", arr[2]);
               returnOrder(invDir, itemName, quantity, price);
               if(dir.length() == 0)
                  break;
                  
               else
                  super.readAllLine(dir);
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
      replaceFile(dir);
   }
   
   public void setLineCount(File dir) {
      lineCount = 1;
      try {
         BufferedReader br = new BufferedReader(new FileReader(dir));
         while (br.readLine() != null)
            lineCount++;
            
         br.close();
      }
      catch(Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
   }
   
   public int getLineCount() {
      return lineCount;
   }
   
   public double getTotalCost() {
      double totalCost = 0;
      setFindDir("orderinfo.txt");
      File orderInfo = new File(getFindDir());
      
      try {
         BufferedReader br = new BufferedReader(new FileReader(orderInfo));
         String line = br.readLine();
         while (line != null) {
            String arr[] = line.split(",");
            String cost = arr[3];
            totalCost += Double.parseDouble(cost);
            line = br.readLine();
         }
         br.close();
      }
      catch(Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
      return totalCost;
   }
   
 
   public void returnOrder(File dir, String itemName, int quantity, String price) {
      int lineCount = 0;
      int remainQuan;
      int origQuan;
      String remainQuanString;
      String origQuanString;
      
      try {
      
         BufferedReader br = new BufferedReader(new FileReader(dir));
         setFindDir("temp2.txt");
         File temp = new File(getFindDir());
         FileWriter fw = new FileWriter(temp);
         
         while ((line = br.readLine()) != null) { 
            lineCount++;
            
            if(line.contains(itemName) && line.contains(price)) {
               
               String arr[] = line.split(",");
               remainQuan = Integer.parseInt(arr[1]);
               origQuan = remainQuan + quantity;
               remainQuanString = Integer.toString(remainQuan);
               origQuanString = Integer.toString(origQuan);
               fw.write(line.replace(remainQuanString, origQuanString) + N);
               fw.flush();
            }
            
            
            else {
               fw.write(line + N);
               fw.flush();
            }
         }
         fw.close();
         br.close();
      }
      catch(Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
      replaceFile2(dir);
   }
   
   public void setSales(File dir) {
      try {
         BufferedReader br = new BufferedReader(new FileReader(dir));
         setFindDir("sales.txt");
         File sales = new File(getFindDir());
         FileWriter fw = new FileWriter(sales, true);
         
         while ((line = br.readLine()) != null) {
            fw.write(line + N);
            fw.flush(); 
         }
         br.close();
         fw.close();
      }
      catch(Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
   }
}