package Control;

import Model.Student;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class StudentController {

    public static int addStudent(Student student) {
        
        int result = 0;
        String stuID = student.getStuID();
        String regDate = student.getRegDate();
        String fName = student.getfName();
        String DoB = student.getDoB();
        String gender = student.getGender();
        String grade = student.getGrade();
        String address = student.getAddress();
        String school = student.getSchool();
        String ForMName = student.getForMName();
        int ForMTel = student.getForMTel();
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = "INSERT INTO student (stuID , regdate , stuName,stuDoB,stuGender,stuGrade,stuAddress,stuSchool,stuFoMName,stuFoMTel ) VALUES ('"+stuID+"', '"+regDate+"','"+fName+"','"+DoB+"','"+gender+"','"+grade+"','"+address+"','"+school+"','"+ForMName+"','"+ForMTel+"')";
            //System.out.println(sql);
            if(stmt.executeUpdate(sql)>0) result=1;
            else result=0;
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    public static ArrayList<String> getStudentNames(String selectedGrade) {
        ArrayList<String> arrayList = new ArrayList<String>();
        
        //System.out.println(selectedGrade);
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT stuName FROM student WHERE stuGrade ='"+selectedGrade+"' ");
            while(rs.next()) { 
               //System.out.println(rs.getString("stuName"));
               arrayList.add(rs.getString("stuName"));
               
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
 
        return arrayList;
    }

    public static Student searchStudentByName(String selectedStuName) {
        Student student = new Student();
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT * FROM student WHERE stuName ='"+selectedStuName+"' ");
            
            while(rs.next()) { 
                
                student.setfName(rs.getString("stuName"));
                student.setStuID(rs.getString("stuID"));
                student.setSchool(rs.getString("stuSchool"));
                student.setRegDate(rs.getString("regdate"));
                student.setGrade(rs.getString("stuGrade"));
                student.setGender(rs.getString("stuGender"));
                student.setForMTel(rs.getInt("stuFoMTel"));
                student.setForMName(rs.getString("stuFoMName"));
                student.setDoB(rs.getString("stuDoB"));
                student.setAddress(rs.getString("stuAddress"));
                  
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
        
        return student;
    }

    public static Student searchStudentByID(String stuID) {
        
        Student student = new Student();
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT * FROM student WHERE stuID = '"+stuID+"' ");
            
            while(rs.next()) { 
                
                student.setfName(rs.getString("stuName"));
                student.setStuID(rs.getString("stuID"));
                student.setSchool(rs.getString("stuSchool"));
                student.setRegDate(rs.getString("regdate"));
                student.setGrade(rs.getString("stuGrade"));
                student.setGender(rs.getString("stuGender"));
                student.setForMTel(rs.getInt("stuFoMTel"));
                student.setForMName(rs.getString("stuFoMName"));
                student.setDoB(rs.getString("stuDoB"));
                student.setAddress(rs.getString("stuAddress"));
                  
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
        
        return student;
    }

    public static ArrayList<String> searchStudentByNameLike(String searchName) {
        ArrayList<String> searchresult =  new ArrayList<String>();

        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT stuName FROM student WHERE stuName LIKE '%"+searchName+"%' ");
            
            while(rs.next()) { 
               searchresult.add(rs.getString("stuName"));
               //System.out.println(rs.getString("subjectid"));
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
       
        return searchresult;
    }

    public static int updateStudent(Student student) {
        
        int result = 0;
        String stuID = student.getStuID();
        String regDate = student.getRegDate();
        String fName = student.getfName();
        String DoB = student.getDoB();
        String gender = student.getGender();
        String grade = student.getGrade();
        String address = student.getAddress();
        String school = student.getSchool();
        String ForMName = student.getForMName();
        int ForMTel = student.getForMTel();
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = "UPDATE  student SET stuID='"+stuID+"' , regdate='"+regDate+"' , stuName='"+fName+"',stuDoB='"+DoB+"',stuGender='"+gender+"',stuGrade='"+grade+"',stuAddress='"+address+"',stuSchool='"+school+"',stuFoMName='"+ForMName+"',stuFoMTel='"+ForMTel+"' ";
            //System.out.println(sql);
            if(stmt.executeUpdate(sql)>0) result=1;
            else result=0;
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    public static int deleteStudent(String stuID) {
        int result=0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = " DELETE FROM student WHERE stuID= '"+stuID+"' ";
            
            
            if(stmt.executeUpdate(sql)>0) result=1;
            else result=0;
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    
    

    
}
