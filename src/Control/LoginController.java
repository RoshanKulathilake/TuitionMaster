package Control;

import Model.Administrator;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginController {

    public static int adminLogin(Administrator admin) {
        String pword = admin.getPassword();
        String uname = admin.getUsername();
        
        String tname = "";
        String tpword = "";
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select * from admin where username ='"+uname+"' " );
            
            while(rs.next()) { 
                tname = rs.getString("username");
                tpword = rs.getString("password");
                //System.out.println(tname);
                //System.out.println(tpword);
                
                
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
        
        if(uname.equals(tname) && pword.equals(tpword)){
            return 1;
        }else return 2;

    }
    
    
}
