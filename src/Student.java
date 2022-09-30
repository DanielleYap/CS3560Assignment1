import java.util.HashSet;
import java.util.UUID;

class Student {
    private UUID studentID;
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
