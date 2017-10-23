package Control;

import Model.Teacher;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class TeacherController {

    public static ArrayList<Teacher> searchTeachersNames() {
        
        
        ArrayList<Teacher> teacherNamesIDs = new ArrayList<Teacher>();
        String teacherName = null;
        String teacherID = null;
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(" SELECT fullname,nic FROM teacher " );
            
            while(rs.next()) { 
               teacherName = rs.getString("fullname");
               teacherID = rs.getString("nic");
               
               
               Teacher teacher = new Teacher();
               
               teacher.setTeacherName(teacherName);
               teacher.setTeacherNIC(teacherID);

               teacherNamesIDs.add(teacher);
             
            }
            
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
        
        return teacherNamesIDs;
    }


    public static int addTeacherDetails(Teacher teacher) {
        String tName = teacher.getTeacherName();
        String tID = teacher.getTeacherNIC();
        int tTel = teacher.getTeacherTel();
        String tGender = teacher.getTeacherGender();
        String tDegree = teacher.getTeacherDegree();
        int result=0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = "INSERT INTO teacher (fullname, telephone, nic, gender, degree ) VALUES ('"+tName+"', '"+tTel+"','"+tID+"','"+tGender+"','"+tDegree+"')";
            
            if(stmt.executeUpdate(sql)>0) result=1;
            else result=0;
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result; 
        
    }

    public static String searchteacherName(String teacherNIC) {
        String result = null;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT fullname FROM teacher WHERE nic = '"+teacherNIC+"' ");
            
            while(rs.next()) { 
               result = rs.getString("fullname");
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
       
        return result;
    }

    public static String searchTeacherID(String teacherName) {
        
        String result = null;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT nic FROM teacher WHERE fullname = '"+teacherName+"' ");
            
            while(rs.next()) { 
               result = rs.getString("nic");
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
       
        return result;
    }

    public static ArrayList<String> searchTeacherNamesOnly() {
        ArrayList<String> teacherNames = new ArrayList<String>();
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(" SELECT fullname FROM teacher " );
            
            while(rs.next()) { 
                teacherNames.add(rs.getString("fullname"));
            }
            
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
        
        return teacherNames;
        
    }

    public static Teacher searchTeacherDetails(String teacherID) {
        Teacher teacher = new Teacher();
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(" SELECT * FROM teacher WHERE nic = '"+teacherID+"' " );
            
            while(rs.next()) { 
               String tName = rs.getString("fullname");
               String tID = rs.getString("nic");
               int tTel = Integer.parseInt(rs.getString("telephone"));
               String tGen = rs.getString("gender");
               String tDegree = rs.getString("degree");
               
               teacher.setTeacherName(tName);
               teacher.setTeacherNIC(tID);
               teacher.setTeacherDegree(tDegree);
               teacher.setTeacherTel(tTel);
               teacher.setTeacherGender(tGen);
               
               
       
            }
            
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
  
        return teacher;
    }

    public static int updateTeacherDetails(Teacher teacher) {
        String tName = teacher.getTeacherName();
        String tID = teacher.getTeacherNIC();
        int tTel = teacher.getTeacherTel();
        String tGender = teacher.getTeacherGender();
        String tDegree = teacher.getTeacherDegree();
        int result=0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = "UPDATE  teacher SET fullname='"+tName+"',telephone='"+tTel+"', gender='"+tGender+"', degree='"+tDegree+"' WHERE nic='"+tID+"' ";
            
            if(stmt.executeUpdate(sql)>0) result=1;
            else result=0;
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result; 
    }

    public static int deleteTeacher(String teacherNIC) {
        
        int result=0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = "DELETE  FROM teacher  WHERE nic='"+teacherNIC+"' ";
            String sqll = "DELETE  FROM subjectteacher  WHERE teacherID='"+teacherNIC+"' ";
            
            if(stmt.executeUpdate(sql)>0 && stmt.executeUpdate(sqll)>0) result=1;
            else result=0;
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result; 
    }

    
    
}
