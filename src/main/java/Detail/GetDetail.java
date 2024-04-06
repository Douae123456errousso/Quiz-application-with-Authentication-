package Detail;

import servies.SentScoreSelf;
import views.LanguageScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class  GetDetail{
    public GetDetail(String email) throws SQLException {
        System.out.println("Press 1 -> Sent Detail to Your Email ");
        System.out.println("Press 2 -> Play Again");
        System.out.println("Press 3 -> exit");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());

        } catch (Exception e) {
            System.out.println("Wrong input");
        }
        switch (choice) {
            case 1 -> new SentScoreSelf(email);
            case 2 -> new LanguageScreen(email);
            case 3 -> System.exit(0);
        }

    }
}