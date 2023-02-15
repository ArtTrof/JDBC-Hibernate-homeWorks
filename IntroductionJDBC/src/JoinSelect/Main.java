package JoinSelect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/myjoinsdb";
        String login = "root";
        String password = "2285";

        registerDriver();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(url, login, password);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select s.date_of_birth,sc.phone from employee_info_add s left join employee_info sc on s.employee_id=sc.id where family_status in \n" +
                    "(Select family_status from employee_info_add where family_status='Single')");
            List<SecondSelection> secondSelectionList = new ArrayList<>();
            while (resultSet.next()) {
                Date date = resultSet.getDate(1);
                String phone = resultSet.getString(2);
                SecondSelection secondSelection = new SecondSelection(phone, date);
                secondSelectionList.add(secondSelection);
            }
            System.out.println("Second selection task:");
            for (SecondSelection ss : secondSelectionList) {
                System.out.println(ss.getDate() + " " + ss.getPhone());
            }

            resultSet = statement.executeQuery("select s.id,s.name,sc.residence from employee_info s left join employee_info_add sc on id=employee_id; ");

            List<FirstSelection> firstSelectionList = new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String residence = resultSet.getString(3);
                firstSelectionList.add(new FirstSelection(id, name, residence));
            }
            System.out.println("first selection task:");
            for (FirstSelection fs : firstSelectionList) {
                System.out.println(fs.getId() + " " + fs.getName() + " " + fs.getResidence());
            }

            resultSet=statement.executeQuery("select\temployee_pos.job_pos,employee_info_add.date_of_birth,employee_info.phone from employee_pos \n" +
                    "inner join employee_info_add on employee_pos.employee_id=employee_info_add.employee_id\n" +
                    "inner join employee_info on employee_pos.employee_id=employee_info.id\n" +
                    "where job_pos in(Select job_pos from employee_pos where job_pos='Manager')" );

            List<SecondSelection> thirdSelectionList = new ArrayList<>();
            while (resultSet.next()) {
                Date date = resultSet.getDate(2);
                String phone = resultSet.getString(3);
                SecondSelection thirdSelection = new SecondSelection(phone, date);
                thirdSelectionList.add(thirdSelection);
            }
            System.out.println("third selection task:");
            for (SecondSelection ss : thirdSelectionList) {
                System.out.println(ss.getDate() + " " + ss.getPhone());
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

class SecondSelection {
    private String phone;
    private Date date;

    public SecondSelection(String phone, Date date) {
        this.phone = phone;
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

class FirstSelection {
    private int id;
    private String name, residence;

    public FirstSelection(int id, String name, String residence) {
        this.id = id;
        this.name = name;
        this.residence = residence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
