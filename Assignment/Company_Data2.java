package Assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Company_Data2 {
    private  static final String url = "jdbc:mysql://localhost:3306/employeesDetails";
    private static final String db_userId = "root";
    private static final String db_passcode = "Xlim@152009";

    public static void main(String[] args) throws Exception{

        //Taking input from user side
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the work location: ");
        String workloaction = sc.nextLine();
        System.out.print("Enter employee department: ");
        String emp_Dpt = sc.nextLine();
        System.out.println("Enter the employee gender: ");
        String emp_Gender = sc.nextLine();

        // Database Connections
        Connection con2 = DriverManager.getConnection(url,db_userId,db_passcode);
        PreparedStatement pre;

        if (workloaction.isEmpty() && emp_Dpt.isEmpty()&&emp_Gender.isEmpty()){
            String sql = "select * from employeesDB";
            pre = con2.prepareStatement(sql);
            ResultSet r = pre.executeQuery();

            while (r.next()){
                System.out.println(r.getInt(1) +"---"+ r.getString(2)+"---"
                        +r.getString(3)+"---"+ r.getString(4)+"---"+r.getDouble(5)
                        +"---"+ r.getDate(6)+"---"+r.getString(7)+"---"+r.getString(8));
            }
        }


        else {
            String sql = "select * from employeesDB where worklocation = ? and department_name = ? and gender = ?";
            pre = con2.prepareStatement(sql);
            pre.setString(1,workloaction);
            pre.setString(2,emp_Dpt);
            pre.setString(3,emp_Gender);
            ResultSet r = pre.executeQuery();

            while (r.next()){
                System.out.println(r.getInt(1) +"---"+ r.getString(2)+"---"
                        +r.getString(3)+"---"+ r.getString(4)+"---"+r.getDouble(5)
                        +"---"+ r.getDate(6)+"---"+r.getString(7)+"---"+r.getString(8));
            }
        }
        con2.close();
        pre.close();

    }
}
