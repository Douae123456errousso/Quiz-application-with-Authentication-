package AdminViews;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class AView1 {
    public AView1() throws SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose ");
        System.out.println("Press 1 -> About Admin");
        System.out.println("Press 2 -> About User");
        System.out.println("Press 0 to exit");
        int choice = 0;
        try{
            choice = Integer.parseInt(br.readLine());
        }catch(Exception e ){
            System.out.println("Wrong input");
            new AView1();
        }
        switch(choice){
            case 1 -> new AboutAdmin();
            case 2 -> new AboutUser();
            case 0 -> System.exit(0);
        }
    }
}

