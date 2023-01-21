import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class SalesMethod extends Method {
   private static final String N = System.getProperty("line.separator");

   @Override
   public void readAllLine(File dir) {
      String itemFormat;
      String line;
   
      try {
         BufferedReader br = new BufferedReader(new FileReader(dir));
         line = br.readLine();
         
         while(line != null) {
            String arr[] = line.split(",");
            itemFormat = String.format("%26s %22s %22s", arr[0], arr[1], arr[3]);
            System.out.println(itemFormat);
            line = br.readLine();
         }
         br.close();
      }
      catch(Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
   }
   
   public void firstHeader() {
      System.out.println(N + String.format("%26s %22s %22s", "ITEM NAME", "QUANTITY", "COST") + N);
   }
}