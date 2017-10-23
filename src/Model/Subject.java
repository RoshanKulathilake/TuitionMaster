package Model;


public class Subject {
    private String subjectName;
    private String subjectID;
    private String subjectYear;
    private double subjectFee;

    public Subject(String subjectName, String subjectID, String subjectYear, double subjectFee) {
        this.subjectName = subjectName;
        this.subjectID = subjectID;
        this.subjectYear = subjectYear;
        this.subjectFee = subjectFee;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectYear() {
        return subjectYear;
    }

    public void setSubjectYear(String subjectYear) {
        this.subjectYear = subjectYear;
    }

    public double getSubjectFee() {
        return subjectFee;
    }

    public void setSubjectFee(double subjectFee) {
        this.subjectFee = subjectFee;
    }
    
}
