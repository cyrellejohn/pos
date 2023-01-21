import javax.swing.JOptionPane;

public class StartHere {
   public static void main(String [] args) {
      String adminUser = System.getProperty("user.name");
      Login login = new Login();
      
      JOptionPane.showMessageDialog(null, "Admin Username: " + adminUser);
      JOptionPane.showMessageDialog(null, "Admin Password: default");
      JOptionPane.showMessageDialog(null, "Employee Username: employee");
      JOptionPane.showMessageDialog(null, "Employee Password: default");
      login.run();
   }
}