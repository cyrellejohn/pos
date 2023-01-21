import java.io.File;

public class Sales {
   private static final String N = System.getProperty("line.separator");

   public static void main(String [] args) {
      Sales sales = new Sales();
      sales.run();
   }
   
   public void run() {
      Method method = new Method();
      SalesMethod salesMethod = new SalesMethod();
      TransMethod transMethod = new TransMethod();
      method.setFindDir("sales.txt");
      File dir = new File(method.getFindDir());
   
      try {
         
         if(!dir.exists())
            dir.createNewFile();
            
         if(dir.length() == 0) 
            System.out.println(N + String.format("%58s", "Current inventory is empty"));
            
         else {
            salesMethod.firstHeader();
            salesMethod.readAllLine(dir);
            System.out.println(N + N + String.format("%71s", transMethod.getTotalCost()));
            System.out.println(N + String.format("%74s", "T  O  T  A  L") + N);
         }
         
      }
      catch(Exception e) {
         System.out.println("Please contact the programmer.");
         e.printStackTrace();
      }
   }
}