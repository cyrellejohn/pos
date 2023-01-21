import javax.swing.JOptionPane;

public class TransEmployee extends Transaction {
   private static final String N = System.getProperty("line.separator");
   
   public static void main(String [] args) {
      TransEmployee transEmp = new TransEmployee();
      transEmp.run();
   }
   
   @Override
   public void exit() {
      Login login = new Login();
      login.run();
   }
   
   @Override
   public void showMessage() {
      JOptionPane.showMessageDialog(null, "Current Inventory is empty." + N + "Please try again later.");
      System.exit(0);
   }
}