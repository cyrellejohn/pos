import java.util.Scanner;

public class Login {
   private static final String N = System.getProperty("line.separator");

   public static void main(String [] args) {
      Login login = new Login();
      login.run();
   }
   
   public void run() {
      String username;
      String password;
      Scanner input = new Scanner(System.in);
      Method method = new Method();
      
      System.out.println(N + "Inventory & Sales System");
      System.out.print(N + "Enter username: ");
      username = input.nextLine();
      System.out.print("Enter password: ");
      password = input.nextLine();
      method.verifyLogin(username, password);
   }
}