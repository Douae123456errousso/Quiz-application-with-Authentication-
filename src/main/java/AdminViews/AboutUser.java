package AdminViews;
import Excel_sheet.CreateExcel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;
import static dao.UserDAO.TotalScore;
import static dao.UserDAO.UserDetails;

public class AboutUser {
    public AboutUser() throws SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Check any user Detail");
        System.out.println("Press 1 -> User Details");
        System.out.println("Press 2 -> See a Detail of Any User");
        System.out.println("Press 3 -> Add Question Quiz");
        System.out.println("Press 4 -> Add User detail in the Excel sheet ");
        System.out.println("Press 5 -> Back");
        System.out.println("Press 0 to exit");
        int choice = 0;
        try{
            choice = Integer.parseInt(br.readLine());
        }catch(Exception e ){
            System.out.println("Wrong input");
            new AboutUser();
        }
        switch(choice){
            case 1 -> getUserDetail();
            case 2 -> DetailOfUser();
            case 3 -> Add();
            case 4 -> Excel();
            case 5 -> new AView1();
            case 0 -> System.exit(0);
        }
    }

    public void Excel() throws SQLException {
        new CreateExcel();
        Back("x");
    }

    public void getUserDetail() throws SQLException {
        System.out.println("Here is the Detail of the User ");
        UserDetails("users");
        Back("x");
    }

    // See the Detail of the Particular user
    public void DetailOfUser() throws SQLException {
        System.out.println("Enter the Email of the User");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        TotalScore(email);
        Back("x");
    }
    public static void Add() throws SQLException {
        System.out.println("Enter Table Name ");
        System.out.println("Press 1 -> Java");
        System.out.println("Press 2 -> JS");
        System.out.println("Press 3 -> Webdev");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        try{
            choice = Integer.parseInt(br.readLine());
        }catch(Exception e ){
            System.out.println("Wrong input");
            new AboutUser();
        }
        switch(choice){
            case 1 -> new AddQuestion("Java");
            case 2 -> new AddQuestion("js");
            case 3 -> new AddQuestion("webdev");
            case 0 -> System.exit(0);
        }
        Back("a");
    }
    public static void Back(String a ) throws SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        if(Objects.equals(a, "a")){
            System.out.println("Check any user Detail");
            System.out.println("Press 1 -> Go Back ");
            System.out.println("Press 2 -> Add next question ");
            System.out.println("Press 0 to exit");
        }else{
            System.out.println("Check any user Detail");
            System.out.println("Press 1 -> Go Back ");
            System.out.println("Press 0 to exit");
        }
        int choice = 0;
        try{
            choice = Integer.parseInt(br.readLine());
        }catch(Exception e ){
            System.out.println("Wrong input");
            new AboutUser();
        }
        switch(choice){
            case 1 -> new AboutUser();
            case 2 -> Add();
            case 0 -> System.exit(0);
        }
    }
    }

