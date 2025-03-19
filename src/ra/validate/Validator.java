package ra.validate;

import java.util.List;
import ra.entity.Student;

public class Validator {
//    public static boolean validateStudentId(String  studentId) {
//        if (studentId.) {
//
//        }
//    }
    public static boolean isEmailUnique(String email, List<Student> students) {
        for (Student student : students) {
            if (student.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isStudentIdUnique(String studentId, List<Student> students) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return false;
            }
        }
        return true;
    }
}