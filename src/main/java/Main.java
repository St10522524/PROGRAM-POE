
import java.util.Scanner;

class Login {
     String username;
     String password;
     String cellPhoneNumber;
     String firstName;
     String lastName;

     
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    
    public boolean checkPasswordComplexity() {
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            hasCapital = hasCapital || Character.isUpperCase(ch);
            hasNumber = hasNumber || Character.isDigit(ch);
            hasSpecial = hasSpecial || !Character.isLetterOrDigit(ch);
        }

        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }

     
    public boolean checkCellPhoneNumber() {
        return cellPhoneNumber.startsWith("+27") && cellPhoneNumber.length() == 12;
    }

    
    public String registerUser() {
        String userMsg = checkUserName() 
            ? "Username successfully captured." 
            : "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";

        String passMsg = checkPasswordComplexity() 
            ? "Password successfully captured." 
            : "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";

        String cellMsg = checkCellPhoneNumber() 
            ? "Cell phone number successfully added." 
            : "Cell phone number incorrectly formatted or does not contain international code.";

        return checkUserName() && checkPasswordComplexity() && checkCellPhoneNumber()
            ? userMsg + "\n" + passMsg + "\n" + cellMsg
            : (!checkUserName() ? userMsg : (!checkPasswordComplexity() ? passMsg : cellMsg));
    }

    
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

     
    public String returnLoginStatus(boolean isLoggedIn) {
        return isLoggedIn 
            ? "Welcome " + firstName + " " + lastName + " it is great to see you again." 
            : "Username or password incorrect, please try again.";
    }
}

 
public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Login user = new Login();
            
            System.out.print("Enter first name: ");
            user.firstName = sc.nextLine();
            
            System.out.print("Enter last name: ");
            user.lastName = sc.nextLine();
            
            System.out.print("Enter username: ");
            user.username = sc.nextLine();
            
            System.out.print("Enter password: ");
            user.password = sc.nextLine();
            
            System.out.print("Enter cell phone number: ");
            user.cellPhoneNumber = sc.nextLine();
            
            
            System.out.println(user.registerUser());
            
             
            System.out.println("\n--- Login ---");
            System.out.print("Enter username: ");
            String loginUser = sc.nextLine();
            
            System.out.print("Enter password: ");
            String loginPass = sc.nextLine();
            
            boolean isSuccess = user.loginUser(loginUser, loginPass);
            System.out.println(user.returnLoginStatus(isSuccess));
        }
    }
}