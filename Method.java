import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;

public class Method {
   private String findDir;
   private String line;
   File temp;
   
   private static final String N = System.getProperty("line.separator");
   private final String ADMIN_USER = System.getProperty("user.name");
   private final String ADMIN_PASS = "default";
   private final String WORKING_DIR = System.getProperty("user.dir");
   private final String SEPARATOR = System.getProperty("file.separator");
   
   Scanner input = new Scanner(System.in);
   InvMethod invMethod = new InvMethod();

   public void delete(File file) {
      int itemNum;
      
      System.out.print("Item number: ");
      itemNum = input.nextInt();
            
      while(itemNum != 0) {
         invMethod.deleteItem(file, itemNum);
               
         if(file.length() == 0)
            break;
                  
         else {
            firstHeader();
            readAllLine(file);     
         }
      
         System.out.print(N + N + "Item number (0 to exit): ");
         itemNum = input.nextInt();
      }
   }

   public void firstHeader() {
      System.out.println(N + String.format("%26s %22s %22s", "ITEM", "QUANTITY", "PRICE") + N);
   }
   
   public void setFindDir(String filename) {
      findDir = WORKING_DIR + SEPARATOR + "info" + SEPARATOR + filename;
   }
   
   public String getFindDir() {
      return findDir;
   }
   
   public void prepareFiles() {
      try {
         setFindDir("employee.txt");
         File emp = new File(getFindDir());
         setFindDir("inventory.txt");
         File inv = new File(getFindDir());
         setFindDir("orderinfo.txt");
         File orderinfo = new File(getFindDir());
         setFindDir("sales.txt");
         File sales = new File(getFindDir());
         
         if(!emp.exists())
            emp.createNewFile();
         
         if(!inv.exists())
            inv.createNewFile();
            
         if(!orderinfo.exists())
            orderinfo.createNewFile();
         
         if(!sales.exists())
            sales.createNewFile();
      }
      catch(Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }   
   }
   
   public void readAllLine(File dir) {
      int lineCount = 0;
      String count;
      String numberFormat;
      String itemFormat;
   
      try {
         BufferedReader br = new BufferedReader(new FileReader(dir));
         line = br.readLine();
         
         while(line != null) {
            lineCount++;
            count = "[" + lineCount + "]";
            numberFormat = String.format("%7s", count);
            String arr[] = line.split(",");
            itemFormat = String.format("%19s %22s %22s", arr[0], arr[1], arr[2]);
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
   
   public void replaceFile(File dir) {
      setFindDir("temp.txt");
      temp = new File(getFindDir());
      
      dir.delete();
      temp.renameTo(dir);
   }
   
   public void replaceFile2(File dir) {
      setFindDir("temp2.txt");
      temp = new File(getFindDir());
      
      dir.delete();
      temp.renameTo(dir);
   }
   
   public void secondHeader() {
      System.out.println(N + N + String.format("%31s %18s %21s", "[1]ADD ITEM", "[2]DELETE", "[3]EXIT"));
   }
   
   public void verifyLogin(String username, String password) {
      prepareFiles();
      setFindDir("employee.txt");
      String employeeDir = getFindDir();
      boolean anEmployee = false;
      Login login = new Login();
      MainMenu menu = new MainMenu();
      TransEmployee transEmp = new TransEmployee();
      
      try {
         BufferedReader br = new BufferedReader(new FileReader(employeeDir));
         while ((line = br.readLine()) != null) {
            if(line.contains(username) && line.contains(password)) 
               anEmployee = true;
         }
         br.close();
      }
      catch(Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
      
      
      if(username.equals(ADMIN_USER) && password.equals(ADMIN_PASS)) {
         JOptionPane.showMessageDialog(null, "Welcome Admin!");
         menu.run();
      }
      
      else if(anEmployee == true) {
         JOptionPane.showMessageDialog(null, "Employee Log in");
         transEmp.run();
      }
      
      else if(username.isEmpty() || password.isEmpty()) {
         JOptionPane.showMessageDialog(null, "One of the fields cannot be empty!");
         login.run();
         
      }
      
      else {
         JOptionPane.showMessageDialog(null, "Try again!");
         login.run();
      } 
   }
}