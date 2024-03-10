package AdminViews;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

import static dao.UserDAO.UserDetails;

public class AboutAdmin {
    public AboutAdmin() throws SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose ");
        System.out.println("Press 1 -> See all Admin");
        System.out.println("Press 2 -> Add New Admin");
        System.out.println("Press 3 -> Delete Admin ");
        System.out.println("Press 4 -> Back ");
        System.out.println("Press 0 to exit");
        int choice = 0;
        try{
            choice = Integer.parseInt(br.readLine());
        }catch(Exception e ){
            System.out.println("Wrong input");
            new AdminViews.AView1();
        }
        switch(choice){
            case 1 -> SeeAllAdmin();
            case 2 -> new AddNewAdmin();
            case 3 -> new DeleteAdmin();
            case 4 -> new AView1();
            case 0 -> System.exit(0);
        }
    }
    public static void SeeAllAdmin() throws SQLException {
        UserDetails("admin");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Press 1 -> Back");
        System.out.println("Press 0 to exit");
        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());

        } catch (Exception e) {
            System.out.println("Wrong input");

            new AboutAdmin();
        }
        switch (choice) {
            case 1 -> new AboutAdmin();
            case 3 -> System.exit(0);
        }
    }
}
