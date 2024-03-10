package AdminViews;

import model.User;
import servies.GenerateOTP;
import servies.SendOTPService;
import servies.UserServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import static dao.AdminDAO.saveAdmin;

public class AddNewAdmin {
    public AddNewAdmin() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name :");
        String name = scanner.nextLine();
        String message = ("Hey"+name+",\n Here is your OTP to Add as a admin in the Quiz Game");
        System.out.println("Enter email :");
        String email = scanner.nextLine();
        System.out.println("Enter Password for the New Admin");
        String Password = scanner.nextLine();
        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sentOTP(email, genOTP,message);
        System.out.println("Enter the otp");
        String otp = scanner.nextLine();
        if (otp.equals(genOTP)) {
           saveAdmin(name,email,Password);
            System.out.println("Admin is  save successfully");

        }
        else {
            System.out.println("Wrong OTP");
        }
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Press 1 ->Add Other Admin");
            System.out.println("Press 2 -> Back");
            System.out.println("Press 0 to exit");
            int choice = 0;
            try {
                choice = Integer.parseInt(br.readLine());

            } catch (Exception e) {
                System.out.println("Wrong input");

                new AboutAdmin();
            }
            switch (choice) {
                case 1 -> new AddNewAdmin();
                case 2 -> new AboutAdmin();
                case 3 -> System.exit(0);
            }
        }

   public static void main (String[] args) throws SQLException {
       new AddNewAdmin();
   }
}
