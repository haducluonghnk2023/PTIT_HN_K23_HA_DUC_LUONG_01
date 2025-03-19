package ra.presentation;

import java.util.Scanner;
import ra.bussiness.StudentBussiness;

public class StudentApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentBussiness studentBusiness = new StudentBussiness();

        while (true) {
            System.out.println("***********************Student Menu***********************");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Chỉnh sửa thông tin sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Tìm kiếm sinh viên");
            System.out.println("6. Sắp xếp");
            System.out.println("0. Thoát chương trình");
            System.out.println("**************************************************************");
            System.out.print("Nhập lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    studentBusiness.displayAllStudents();
                    break;
                case 2:
                    studentBusiness.addNewStudent(sc);
                    break;
                case 3:
                    studentBusiness.editStudent(sc);
                    break;
                case 4:
                    studentBusiness.deleteStudent(sc);
                    break;
                case 5:
                    studentBusiness.searchStudent(sc);
                    break;
                case 6:
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}