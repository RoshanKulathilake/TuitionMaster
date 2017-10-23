package Model;


public class Teacher {

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherNIC() {
        return teacherNIC;
    }

    public void setTeacherNIC(String teacherNIC) {
        this.teacherNIC = teacherNIC;
    }

    public int getTeacherTel() {
        return teacherTel;
    }

    public void setTeacherTel(int teacherTel) {
        this.teacherTel = teacherTel;
    }

    public String getTeacherGender() {
        return teacherGender;
    }

    public void setTeacherGender(String teacherGender) {
        this.teacherGender = teacherGender;
    }

    public String getTeacherDegree() {
        return teacherDegree;
    }

    public void setTeacherDegree(String teacherDegree) {
        this.teacherDegree = teacherDegree;
    }
    private String teacherName;
    private String teacherNIC;
    private int teacherTel;
    private String teacherGender;
    private String teacherDegree;
}
