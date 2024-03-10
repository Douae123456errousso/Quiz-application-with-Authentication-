package Excel_sheet;

import db.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExcelDAO {
    public static String[][] ExcelDetail() throws SQLException {
        Connection connection = Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT users.Email, users.Name, score.Name AS ScoreName, score.Lang, score.Score FROM users LEFT JOIN score ON users.Email = score.Email");

        ResultSet rs = ps.executeQuery();

        List<String[]> resultList = new ArrayList<>();
        while (rs.next()) {
            String email = rs.getString("Email");
            String userName = rs.getString("Name");
            String scoreName = rs.getString("ScoreName");
            String lang = rs.getString("Lang");
            String score = rs.getString("Score");

            // If the score-related variables are null, set them to empty strings
            if (scoreName == null) {
                scoreName = "null";
                lang = "null";
                score = "null";
            }

            resultList.add(new String[]{email, userName, scoreName, lang, score});
        }

        return resultList.toArray(new String[0][]);
    }



}
