package Control;

import Model.Payment;
import Model.Student;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class PaymentController {

    public static int addPayment(Student student, Payment payment) {
            String sID = student.getStuID();
            String sName = student.getfName();
            double fee = payment.getFee();
            String date = payment.getPaymentDate();
            int year = payment.getYear();
            String month = payment.getMonth();
            int result=0;
            
            try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = "INSERT INTO payments (studentID, studentName, year, month, date, fee ) VALUES ('"+sID+"', '"+sName+"','"+year+"','"+month+"','"+date+"','"+fee+"')";
            
            if(stmt.executeUpdate(sql)>0) result=1;
            else result=0;
            
        }catch(Exception e){
            System.out.println(e);
        }
            
            
            
            
            
            
            
            return result;
    }

    public static ArrayList<String> getPaymentYears() {
        
        ArrayList<String> years = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT year FROM payments");
            
            while(rs.next()) { 
               years.add(rs.getString("year"));
               
                
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return years;
    }

    public static ArrayList<String> getPaymentDates(String searchID, String selectedYear) {
        
        ArrayList<String> dates = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT month,date,fee FROM payments WHERE studentID = '"+searchID+"' AND year = '"+selectedYear+"' ");
            
            while(rs.next()) { 
               dates.add(rs.getString("month")+" - ["+rs.getString("date")+"] - Rs: "+rs.getString("fee"));
               
                
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return dates;
    }
    
}
