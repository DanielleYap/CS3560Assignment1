import java.util.Random;
import java.util.HashMap;
import java.util.UUID;

public class SimulationDriver {
    public static void main(String[] args) {
        Random rand = new Random();
        VotingService service = new VotingService();
        final int TYPE = 1; //makes it easier to switch between modes, 1=multi, 2=single

        /* ********************************************************************************
         * 1) Create a question type and configure the answers
         * ********************************************************************************/
        // Create question text
        String questionText = "1. What is Object Oriented Programming?";

        // Create answers
        HashMap<Integer, String> questionAnswers = new HashMap<>();
        questionAnswers.put(0, "\tA. Organized");
        questionAnswers.put(1, "\tB. Difficult");
        questionAnswers.put(2, "\tC. A way to design programs");
        questionAnswers.put(3, "\tD. Something we are learning");
        questionAnswers.put(4, "\tE. Helpful");

        // Generate Question object, stores question text and answers
        Question question = new Question(TYPE, questionText, questionAnswers);

        /* ********************************************************************************
         * 2) Configure the question for the VotingService
         * *******************************************************************************/
        // Send question to VotingService
        service.setQuestion(question);

        /* ********************************************************************************
         * 3) Randomly generate a number of students and their answers
         * 4) Submit all the students' answers to the VotingService
         * 5) Call the VotingService output function
         * ********************************************************************************/
        // Generate random number of students (15-30)
        int studentCount = rand.nextInt(15)+15;

        // Create an array of Students with unique IDs
        Student[] studentList = new Student[studentCount];

        for (int i=0; i<studentCount; i++) {
            // UUID creates unique IDs for each student
            studentList[i] = new Student (UUID.randomUUID());
        }

        // It's a mess, but it works
        for (int i=0; i<studentCount; i++) {
            // Randomly generate student's submissions
            int answersChosen = rand.nextInt(question.getAnswers().size()-1)+2;
            if (question.getType()==2) {answersChosen=2;}
            // Add Student and their submission(s) to VotingService
            for (int j=1; j<answersChosen; j++) {
                int chosen = question.getAnswers().size();
                studentList[i].getResponse().add(rand.nextInt(chosen));

                service.acceptSubmissions(studentList[i]);
            }

            // Mark Student as submitted
            service.getSubmitted().add(studentList[i]);
        }

        // Call output function
        service.outputSubmissionStats();
    }
}