package ra.bussiness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ra.entity.Student;
import ra.validate.Validator;

public class StudentBussiness {
    private List<Student> students = new ArrayList<>();

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Danh sách sinh viên trống.");
        } else {
            for (Student student : students) {
                student.displayData();
                System.out.println("--------------------");
            }
        }
    }

    public void addNewStudent(Scanner sc) {
        System.out.print("Nhập số lượng sinh viên cần thêm: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            Student student = new Student();
            student.inputData(sc);
            if (!Validator.isEmailUnique(student.getEmail(), students)) {
                System.err.println("Email đã tồn tại!");
                i--;
                continue;
            }
            if (!Validator.isStudentIdUnique(student.getStudentId(), students)) {
                System.err.println("Mã sinh viên đã tồn tại!");
                i--;
                continue;
            }
            students.add(student);
            System.out.println("Thêm sinh viên thành công!");
        }
    }

    public void editStudent(Scanner sc) {
        System.out.print("Nhập mã sinh viên cần sửa: ");
        String studentId = sc.nextLine();
        Student studentToEdit = findStudentById(studentId);
        if (studentToEdit == null) {
            System.out.println("Không tìm thấy sinh viên!");
            return;
        }

        System.out.println("Thông tin sinh viên hiện tại:");
        studentToEdit.displayData();

        System.out.println("Chọn thông tin cần sửa:");
        System.out.println("1. Tên sinh viên");
        System.out.println("2. Ngày sinh");
        System.out.println("3. Số điện thoại");
        System.out.println("4. Giới tính");
        System.out.println("5. Email");
        System.out.println("6. Ngành học");
        System.out.println("7. Tên lớp");
        System.out.println("8. Điểm GPA");
        System.out.println("9. Trạng thái");

        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                System.out.print("Nhập tên mới: ");
                studentToEdit.setStudentName(sc.nextLine());
                break;
            case 2:
                System.out.print("Nhập ngày sinh mới (dd/MM/yyyy): ");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    studentToEdit.setBirthday(sdf.parse(sc.nextLine()));
                } catch (ParseException e) {
                    System.err.println("Ngày sinh không đúng định dạng!");
                }
                break;
            case 3:
                System.out.print("Nhập số điện thoại mới: ");
                studentToEdit.setPhoneNumber(sc.nextLine());
                break;
            case 4:
                System.out.print("Nhập giới tính mới (true-Nam, false-Nữ): ");
                studentToEdit.setSex(Boolean.parseBoolean(sc.nextLine()));
                break;
            case 5:
                System.out.print("Nhập email mới: ");
                String newEmail = sc.nextLine();
                if (!Validator.isEmailUnique(newEmail, students)) {
                    System.err.println("Email đã tồn tại!");
                    return;
                }
                studentToEdit.setEmail(newEmail);
                break;
            case 6:
                System.out.print("Nhập ngành học mới: ");
                studentToEdit.setMajor(sc.nextLine());
                break;
            case 7:
                System.out.print("Nhập tên lớp mới: ");
                studentToEdit.setClassName(sc.nextLine());
                break;
            case 8:
                System.out.print("Nhập GPA mới: ");
                studentToEdit.setGpa(Float.parseFloat(sc.nextLine()));
                break;
            case 9:
                System.out.print("Nhập trạng thái mới (1-đang học, 2-bảo lưu, 3-nghỉ học): ");
                studentToEdit.setStatus(Byte.parseByte(sc.nextLine()));
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }
        System.out.println("Sửa thông tin thành công!");
    }

    public void deleteStudent(Scanner sc) {
        System.out.print("Nhập mã sinh viên cần xóa: ");
        String studentId = sc.nextLine();
        Student studentToDelete = findStudentById(studentId);
        if (studentToDelete == null) {
            System.out.println("Không tìm thấy sinh viên!");
            return;
        }

        System.out.println("Thông tin sinh viên cần xóa:");
        studentToDelete.displayData();
        System.out.print("Bạn có chắc chắn muốn xóa sinh viên này? (y/n): ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            students.remove(studentToDelete);
            System.out.println("Xóa sinh viên thành công!");
        } else {
            System.out.println("Hủy bỏ thao tác xóa.");
        }
    }

    public void searchStudent(Scanner sc) {
        System.out.println("Chọn tiêu chí tìm kiếm:");
        System.out.println("1. Tìm kiếm theo tên");
        System.out.println("2. Tìm kiếm theo lớp");
        System.out.println("3. Tìm kiếm theo khoảng GPA");

        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                System.out.print("Nhập tên sinh viên cần tìm: ");
                String searchName = sc.nextLine();
                List<Student> searchResultsName = new ArrayList<>();
                for (Student student : students) {
                    if (student.getStudentName().toLowerCase().contains(searchName.toLowerCase())) {
                        searchResultsName.add(student);
                    }
                }
                displaySearchResults(searchResultsName);
                break;
            case 2:
                System.out.print("Nhập tên lớp cần tìm: ");
                String searchClass = sc.nextLine();
                List<Student> searchResultsClass = new ArrayList<>();
                for (Student student : students) {
                    if (student.getClassName().toLowerCase().contains(searchClass.toLowerCase())) {
                        searchResultsClass.add(student);
                    }
                }
                displaySearchResults(searchResultsClass);
                break;
            case 3:
                System.out.print("Nhập GPA thấp nhất: ");
                float minGpa = Float.parseFloat(sc.nextLine());
                System.out.print("Nhập GPA cao nhất: ");
                float maxGpa = Float.parseFloat(sc.nextLine());
                List<Student> searchResultsGpa = new ArrayList<>();
                for (Student student : students) {
                    if (student.getGpa() >= minGpa && student.getGpa() <= maxGpa) {
                        searchResultsGpa.add(student);
                    }
                }
                displaySearchResults(searchResultsGpa);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    private void displaySearchResults(List<Student> results) {
        if (results.isEmpty()) {
            System.out.println("Không tìm thấy kết quả phù hợp.");
        } else {
            for (Student student : results) {
                student.displayData();
                System.out.println("--------------------");
            }
        }
    }

    private Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
}