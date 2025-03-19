package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Student implements IApp {

    private String studentId;
    private String studentName;
    private Date birthday;
    private String phoneNumber;
    private boolean sex;
    private String email;
    private String major;
    private String className;
    private float gpa;
    private byte status;

    public Student() {
    }

    // Getters and setters
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.println("Nhập mã sinh viên: ");
        this.studentId =  sc.nextLine();
        while (this.studentId.trim().isEmpty()) {
            System.err.println("Mã sinh viên không được để trống!");
            System.out.print("Nhập lại mã sinh viên: ");
            this.studentId = sc.nextLine();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Nhập tên sinh viên: ");
        this.studentName = sc.nextLine();
        while (this.studentName.trim().isEmpty()) {
            System.err.println("Tên sinh viên không được để trống!");
            System.out.print("Nhập lại tên sinh viên: ");
            this.studentName = sc.nextLine();
        }

        System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
        while (true) {
            String birthdayStr = sc.nextLine();
            try {
                this.birthday = sdf.parse(birthdayStr);
                break;
            } catch (ParseException e) {
                System.err.println("Ngày sinh không đúng định dạng (dd/MM/yyyy)!");
                System.out.print("Nhập lại ngày sinh: ");
            }
        }

        System.out.print("Nhập số điện thoại: ");
        this.phoneNumber = sc.nextLine();
        while (!this.phoneNumber.matches("039\\d{7}")) {
            System.err.println("Số điện thoại không đúng định dạng!");
            System.out.print("Nhập lại số điện thoại: ");
            this.phoneNumber = sc.nextLine();
        }

        System.out.print("Nhập giới tính (true-Nam, false-Nữ): ");
        this.sex = Boolean.parseBoolean(sc.nextLine());

        System.out.print("Nhập email: ");
        this.email = sc.nextLine();
        while (!this.email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
            System.err.println("Email không đúng định dạng gmail!");
            System.out.print("Nhập lại email: ");
            this.email = sc.nextLine();
        }

        System.out.print("Nhập ngành học: ");
        this.major = sc.nextLine();
        while (this.major.trim().isEmpty()) {
            System.err.println("Ngành học không được để trống!");
            System.out.print("Nhập lại ngành học: ");
            this.major = sc.nextLine();
        }

        System.out.print("Nhập tên lớp: ");
        this.className = sc.nextLine();
        while (this.className.trim().isEmpty()) {
            System.err.println("Tên lớp không được để trống!");
            System.out.print("Nhập lại tên lớp: ");
            this.className = sc.nextLine();
        }

        System.out.print("Nhập điểm GPA: ");
        this.gpa = Float.parseFloat(sc.nextLine());
        while (this.gpa < 0) {
            System.err.println("GPA phải lớn hơn hoặc bằng 0!");
            System.out.print("Nhập lại GPA: ");
            this.gpa = Float.parseFloat(sc.nextLine());
        }
    }

    @Override
    public void displayData() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Mã sinh viên: " + this.studentId);
        System.out.println("Tên sinh viên: " + this.studentName);
        System.out.println("Tuổi: " + getAge());
        System.out.println("Giới tính: " + (this.sex ? "Nam" : "Nữ"));
        System.out.println("Số điện thoại: " + this.phoneNumber);
        System.out.println("Tên lớp: " + this.className);
        System.out.println("Trạng thái: " + getStatusString());
    }

    public int getAge() {
        Date now = new Date();
        long diff = now.getTime() - birthday.getTime();
        return (int) (diff / (1000L * 60 * 60 * 24 * 365));
    }

    public String getStatusString() {
        switch (this.status) {
            case 1:
                return "Đang theo học";
            case 2:
                return "Bảo lưu";
            case 3:
                return "Đã nghỉ học";
            default:
                return "Không xác định";
        }
    }
}