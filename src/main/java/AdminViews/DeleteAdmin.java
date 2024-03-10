package AdminViews;

import servies.GenerateOTP;
import servies.SendOTPService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import static dao.AdminDAO.deleteAdmin;

class DeleteAdmin {

    public DeleteAdmin() throws  SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name :");
        String name = scanner.nextLine();
        String message = ("Hey " + name + ",Warning :: Here is the OTP to delete you as Admin");
        System.out.println("Enter email :");
        String email = scanner.nextLine();
        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sentOTP( email, genOTP,message);
        System.out.println("Enter the otp");
        String otp = scanner.nextLine();
        if (otp.equals(genOTP)) {
                deleteAdmin(email);
                System.out.println("Admin is  deleted successfully");
        } else {
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

            try {
                new AboutAdmin();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        switch (choice) {
            case 1 -> new DeleteAdmin();
            case 2 -> new AboutAdmin();
            case 3 -> System.exit(0);
        }
    }
}

