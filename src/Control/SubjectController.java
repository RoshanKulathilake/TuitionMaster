package Control;

import Model.Subject;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class SubjectController {
    
    public static int addSubject(Subject subject){
        
        String subjectName = subject.getSubjectName();
        String subjectID = subject.getSubjectID();
        String subjectYear = subject.getSubjectYear();
        double subjectFee = subject.getSubjectFee();
        int result=0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = "INSERT INTO subjects (subjectcategory, subjectid, subjectfee, subjectyear ) VALUES ('"+subjectName+"', '"+subjectID+"','"+subjectFee+"','"+subjectYear+"')";
            
            if(stmt.executeUpdate(sql)>0) result=1;
            else result=0;
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result;  
    }

    public static Subject searchSubjectByID(String subNameID) {
        
        //System.out.println(subNameID);
        String subjectNameOrID = subNameID;
        String subID = null;
        String subName = null;
        String subYear = null;
        double subFee = 0.0;
        Subject subject = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select * from subjects where subjectid ='"+subjectNameOrID+"' " );
            
            while(rs.next()) { 
               subName = rs.getString("subjectcategory");
               subID = rs.getString("subjectid");
               subFee = rs.getDouble("subjectfee");
               subYear  = rs.getString("subjectyear");
               
               //System.out.println(subName+subID+subFee+subYear);
                 
            }
            
            subject = new Subject(subName,subID,subYear,subFee);
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return subject;
    }

    public static ArrayList<String> searchSubjectNames() {
        ArrayList<String> subNames = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT subjectcategory FROM subjects");
            
            while(rs.next()) { 
               subNames.add(rs.getString("subjectcategory"));
               
                
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return subNames;
    }

    public static ArrayList<String> searchSubjectIDs(String selectedSubject) {
        String selectedSub = selectedSubject;
        ArrayList<String> subIDs = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT subjectid FROM subjects WHERE subjectcategory ='"+selectedSub+"' AND subjectid IN (SELECT subjectid FROM subjectteacher) ");
            
            while(rs.next()) { 
               subIDs.add(rs.getString("subjectid"));
               //System.out.println(rs.getString("subjectid"));
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
       
        return subIDs; 
    }

    public static ArrayList<String> searchSubjectByName(String searchSubjectName) {
        ArrayList<String> searchresult =  new ArrayList<String>();
        String getSearch = searchSubjectName;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT subjectid FROM subjects WHERE subjectcategory LIKE '%"+getSearch+"%' ");
            
            while(rs.next()) { 
               searchresult.add(rs.getString("subjectid"));
               //System.out.println(rs.getString("subjectid"));
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
       
        return searchresult;
    }

    public static int addSubjectTeacherDetails(String teacherNIC, ArrayList<String> tempArrList) {
        
        int result=0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = "INSERT INTO subjectteacher (teacherid, subjectid ) VALUES ";
            
            for(int i=0;i<tempArrList.size();i++){
                sql+= "('"+teacherNIC+"', '"+tempArrList.get(i)+"'),";
            }
            sql = sql.substring(0,sql.length()-1);
            //System.out.println(sql);
            if(stmt.executeUpdate(sql)>0) result=1;
            else result=0;
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result; 
    }

    public static String searchTeacherID(String selectedSubject) {
        String result = null;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT teacherID FROM subjectteacher WHERE subjectID = '"+selectedSubject+"' ");
            
            while(rs.next()) { 
               result = rs.getString("teacherID");
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
       
        return result;
    }

    public static int updateSubject(Subject subject) {
        String subjectName = subject.getSubjectName();
        String subjectID = subject.getSubjectID();
        String subjectYear = subject.getSubjectYear();
        double subjectFee = subject.getSubjectFee();
        int result=0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = " UPDATE subjects SET subjectcategory = '"+subjectName+"', subjectfee = '"+subjectFee+"',  subjectyear = '"+subjectYear+"' WHERE subjectid = '"+subjectID+"' ";
            
            
            if(stmt.executeUpdate(sql)>0) result=1;
            else result=0;
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result;  
    }

    public static int updateSubjectTeacher(String subID, String teacherID) {
        
        int result=0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = " UPDATE subjectteacher SET teacherID = '"+teacherID+"' WHERE subjectID= '"+subID+"' ";
            
            
            if(stmt.executeUpdate(sql)>0) result=1;
            else result=0;
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    public static int deleteSubject(String subID) {
        
        int result=0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = " DELETE FROM subjects WHERE subjectid = '"+subID+"' ";
            
            
            if(stmt.executeUpdate(sql)>0) result=1;
            else result=0;
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result;  
    }

    public static int deleteSubjectTeacher(String subID, String teacherID) {
        int result=0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = " DELETE FROM subjectteacher WHERE subjectID= '"+subID+"' ";
            
            
            if(stmt.executeUpdate(sql)>0) result=1;
            else result=0;
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    public static ArrayList<String> searchSubjectIDsNot(String selectedSubject) {
        
        String selectedSub = selectedSubject;
        ArrayList<String> subIDs = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT subjectid FROM subjects WHERE subjectcategory ='"+selectedSub+"' AND subjectid NOT IN (SELECT subjectid FROM subjectteacher) ");
            
            while(rs.next()) { 
               subIDs.add(rs.getString("subjectid"));
               //System.out.println(rs.getString("subjectid"));
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
       
        return subIDs; 
    }

    public static ArrayList<String> SearchSubjectIDsFromTeacher(String teacherID) {
        
        ArrayList<String> subIDs = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT subjectid FROM subjectteacher WHERE teacherid ='"+teacherID+"' ");
            
            while(rs.next()) { 
               subIDs.add(rs.getString("subjectid"));
               //System.out.println(rs.getString("subjectid"));
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
       
        return subIDs; 
    }

    public static int updateSubjectTeacherDetails(String updtTeacherNIC, ArrayList<String> tempArrList) {
        
        int result=0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = " DELETE FROM subjectteacher WHERE teacherid = '"+updtTeacherNIC+"' ";
            
            String sqll = "INSERT INTO subjectteacher (teacherid, subjectid ) VALUES ";
            
            for(int i=0;i<tempArrList.size();i++){
                sqll+= "('"+updtTeacherNIC+"', '"+tempArrList.get(i)+"'),";
            }
            sqll = sqll.substring(0,sqll.length()-1);
            
            if(stmt.executeUpdate(sql)>0 && stmt.executeUpdate(sqll)>0) result=1;
            else result=0;
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    public static ArrayList<String> getSubjectGrades() {
        
        ArrayList<String> subGrades = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(" SELECT DISTINCT subjectyear FROM subjects ");
            
            while(rs.next()) { 
               subGrades.add(rs.getString("subjectyear"));
               //System.out.println(rs.getString("subjectid"));
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
       
        return subGrades; 
    }

    public static ArrayList<String> searchSubjectIDsFromGrade(String selectedGrade) {
        
        ArrayList<String> subIDs = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT subjectid FROM subjects WHERE subjectyear ='"+selectedGrade+"' AND subjectid IN (SELECT subjectid FROM subjectteacher) ");
            
            while(rs.next()) { 
               subIDs.add(rs.getString("subjectid"));
               //System.out.println(rs.getString("subjectid"));
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
       
        return subIDs; 
    }

    public static int addSubjectStudent(String stuID, ArrayList<String> tempArrList) {
        
        int result=0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = "INSERT INTO subjectstudent (StudentID,subjectID) VALUES ";
            
            for(int i=0;i<tempArrList.size();i++){
                sql+= "('"+stuID+"', '"+tempArrList.get(i)+"'),";
            }
            sql = sql.substring(0,sql.length()-1);
            //System.out.println(sql);
            if(stmt.executeUpdate(sql)>0) result=1;
            else result=0;
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result; 
        
    }

    public static ArrayList<String> searchSubjectStudentByStudentID(String stuID) {
        
        ArrayList<String> arraList = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(" SELECT subjectID FROM subjectstudent WHERE studentID ='"+stuID+"' ");
            
            while(rs.next()) { 
               arraList.add(rs.getString("subjectid"));
               //System.out.println(rs.getString("subjectid"));
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
       
        return arraList; 
    }

    public static int updateSubjectStudent(String stuID, ArrayList<String> tempArrList) {
        int result=0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = " DELETE FROM subjectstudent WHERE studentID = '"+stuID+"' ";
            
            String sqll = "INSERT INTO subjectstudent (studentID, subjectID ) VALUES ";
            
            for(int i=0;i<tempArrList.size();i++){
                sqll+= "('"+stuID+"', '"+tempArrList.get(i)+"'),";
            }
            sqll = sqll.substring(0,sqll.length()-1);
            
            if(stmt.executeUpdate(sql)>0 && stmt.executeUpdate(sqll)>0) result=1;
            else result=0;
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    public static int deleteSubjectStudent(String stuID) {
        int result=0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            String sql = " DELETE FROM subjectstudent WHERE studentID= '"+stuID+"' ";
            
            
            if(stmt.executeUpdate(sql)>0) result=1;
            else result=0;
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    public static ArrayList<String> searchSubjectIDsFromSubjectStudent(String stuID,String selGrade) {
        
        
        ArrayList<String> subIDs = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT subjectid FROM subjects WHERE subjectyear ='"+selGrade+"' AND subjectid NOT IN (SELECT subjectID FROM subjectstudent WHERE studentID = '"+stuID+"') ");
            
            while(rs.next()) { 
               subIDs.add(rs.getString("subjectid"));
               //System.out.println(rs.getString("subjectid"));
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
       
        return subIDs;
    }

    public static double getFee(String searchID) {
        
        double totalFee=0.0;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            java.sql.Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/tuitionmaster","root","");  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT SUM(subjectfee) AS Total FROM subjects WHERE subjectid IN (SELECT subjectID FROM subjectstudent WHERE studentID = '"+searchID+"') ");
            
            while(rs.next()) { 
               
               //System.out.println(rs.getDouble("Total"));
               totalFee = rs.getDouble("Total");
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return totalFee;
    }

    
}
