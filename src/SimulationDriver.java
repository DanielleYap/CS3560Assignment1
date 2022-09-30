import java.util.HashSet;
import java.util.Random;
import java.util.HashMap;
import java.util.UUID;

public class SimulationDriver {
    public static void main(String[] args) {
        Random rand = new Random();
        /* **********************************************************************************************************
         *
         * Simulating multiple choice question
         *
         * **********************************************************************************************************/

        /* *********************************************************
         * 1) Create a question type and configure the answers
         * *********************************************************/
        // Create question text
        String question1Text = "1. What is Object Oriented Programming?";

        // Create answers
        HashMap<Integer, String> question1Answers = new HashMap<Integer, String>();
        question1Answers.put(0, "\tA. Organized");
        question1Answers.put(1, "\tB. Difficult");
        question1Answers.put(2, "\tC. A way to design programs");
        question1Answers.put(3, "\tD. Something we are learning");
        question1Answers.put(4, "\tE. Helpful");

        // Generate MCQ object, stores question text and answers
        Question question1 = new Question(1, question1Text, question1Answers);

        /* *********************************************************
         * 2) Configure the question for the VotingService
         * ********************************************************/
        // Send question to VotingService
        VotingService service = new VotingService();
        service.setQuestion(question1);

        /* *********************************************************
         * 3) Randomly generate a number of students and their answers
         * 4) Submit all the students' answers to the VotingService
         * 5) Call the VotingService output function
         * *********************************************************/
        // Generate random number of students (15-30)
        int studentCount = rand.nextInt(15)+15;

        // Create an array of Students with unique IDs
        Student studentList[] = new Student[studentCount];
        for (int i=0; i<studentCount; i++) {
            // UUID creates unique IDs for each student
            studentList[i] = new Student (UUID.randomUUID());
        }


        // It's a mess, but it works
        HashMap<String, HashSet<Integer>> submissions = new HashMap<String, HashSet<Integer>>();
        for (int i=0; i<studentCount; i++) {
            // Randomly generate student's submissions
            int answersChosen = rand.nextInt(question1.getAnswers().size()-1)+2;
            // Add submission to Student's response HashSet
            for (int j=1; j<answersChosen; j++) {
                studentList[i].getResponse().add(rand.nextInt(question1.getAnswers().size()));
            }
            // Puts ID and submission together in a Hashmap
            submissions.put(studentList[i].getStudentID().toString(), studentList[i].getResponse());
        }

        // Submit answers to VotingService
        service.setResponseSubmissions(submissions);

        // Call output function
        service.outputSubmissionStats();


        /* **********************************************************************************************************
         *
         * Testing single choice question
         *
         * **********************************************************************************************************/


    }
}