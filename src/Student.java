import java.util.HashSet;
import java.util.UUID;

class Student {
    // Using UUID to generate unique IDs for each student
    private UUID studentID;
    // Stores each student's submissions
    HashSet<Integer> response = new HashSet<>();

    Student (UUID id) {
        studentID = id;
    }

    public String getStudentID() {
        return studentID.toString();
    }

    public HashSet<Integer> getResponse() {
        return response;
    }
}
