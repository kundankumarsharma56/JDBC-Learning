package Assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class SalaryHike {

    private  static final String url = "jdbc:mysql://localhost:3306/employeesDetails";
    private static final String db_userId = "root";
    private static final String db_passcode = "Xlim@152009";
    private static final String sql = "UPDATE employeesDB SET salary = salary + (salary * ?) / 100 WHERE department_name = ?";

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the salary hike % based on dep: ");
        double salaryHike = sc.nextDouble();

        sc.nextLine();

        System.out.print("Enter the department name: ");
        String department = sc.nextLine();



        Connection con2 = DriverManager.getConnection(url,db_userId,db_passcode);
        PreparedStatement pre2 = con2.prepareStatement(sql);

        pre2.setDouble(1,salaryHike);
        pre2.setString(2,department);


        int re = pre2.executeUpdate();
        System.out.println("Row updates: "+re);

        pre2.close();
        con2.close();
    }
}
