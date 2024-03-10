package Detail;

import model.UserScore;
import views.LanguageScreen;
import views.LoginView;
import views.View2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

import static dao.UserDAO.LanguageScore;
import static dao.UserDAO.TotalScore;

public class accountDetail {
    static String Lang;

    public accountDetail(String email) throws SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Press 1 -> Detail of Particular Field");
        System.out.println("Press 2 -> get Whole Detail");
        System.out.println("Press 3 -> exit");

        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());

        } catch (Exception e) {
            System.out.println("Wrong input");
        }
        switch (choice) {
            case 1 -> ScoreLanguage(email);
            case 2 -> FullDetail(email);
            case 3 -> System.exit(0);

        }

    }
    static void ScoreLanguage(String email) throws SQLException {
        System.out.println("Press 1 -> Java");
        System.out.println("Press 2 -> JavaScript");
        System.out.println("Press 3 -> Web Development");
        System.out.println("Press 4 -> to exit");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());

        } catch (Exception e) {
            System.out.println("Wrong input");
        }
        switch (choice) {
            case 1 -> Lang = "Java";
            case 2 -> Lang = "JS";
            case 3 -> Lang = "Webdev";
            case 4 -> System.exit(0);
        }

        System.out.println("Enter the name of Language");
        System.out.println("Email :"+email);
        System.out.println("Language :"+ Lang);
        LanguageScore(email,Lang);

        GetBack(email);
    }

    static  void FullDetail(String email) throws SQLException {
        TotalScore(email);

        new GetDetail(email);
    }

    public static void GetBack(String email) throws SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose :");
        System.out.println("Press 1 -> Start Play again ");
        System.out.println("Press 2 ->  Back");
        System.out.println("Press 3 -> exit");

        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());

        } catch (Exception e) {
            System.out.println("Wrong input");
        }
        switch (choice) {
            case 1 -> new LoginView(email);
            case 2 -> new accountDetail(email);
            case 3 -> System.exit(0);

        }
    }

/***write a front end for this java file where all the name of the name of the variables and function should remain same as
 * per the given
 *System.out.println("Press 1 -> Detail of Particular Field");
 *         System.out.println("Press 2 -> get Whole Detail");
 *         System.out.println("Press 3 -> exit");
 *          System.out.println("Press 1 -> Java");
 *         System.out.println("Press 2 -> JavaScript");
 *         System.out.println("Press 3 -> Web Development");
 *         System.out.println("Press 4 -> to exit");
 *           System.out.println("Choose :");
 *         System.out.println("Press 1 -> Start Play again ");
 *         System.out.println("Press 2 ->  Back");
 *         System.out.println("Press 3 -> exit"); this statements and all the statements look this this
 *         should shown as button of the screen
 *         button should be attrative with the hover effect
 *         and all the function have same functionally per the define does not change it
 *         */
}
