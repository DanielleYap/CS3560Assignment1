import java.util.HashMap;
import java.util.HashSet;

class VotingService {
    private Question question;
    // Key: student ID, Value: responses
    private HashMap<String, HashSet<Integer>> responseSubmissions = new HashMap<>();
    private HashSet<Student> submitted = new HashSet<>();

    public void setQuestion(Question question) {
        this.question = question;
    }
    public HashSet<Student> getSubmitted() {
        return submitted;
    }

    /**
     * Accepts Student submissions fron SimulationDrivers
     * @param s - Student submitting answers
     */
    public void acceptSubmissions(Student s) {
        // If Student has submitted before, remove their prior submission
        if (submitted.contains(s)) {
            submitted.remove(s);
        }
        // If it is a multiple choice question, allow multiple submissions
        if (question.getType() == 1) {
            responseSubmissions.put(s.getStudentID(), s.getResponse());
        }
        // If it is a single choice question, ignore further submissions
        else if (question.getType() == 2) {
            if (!responseSubmissions.containsKey(s.getStudentID())) {
                responseSubmissions.put(s.getStudentID(), s.getResponse());
            }
        }
    }

    /**
     * Outputs submission data
     *   - How many votes each answer got
     *   - can optionally print who voted for what
     */
    public void outputSubmissionStats() {
        String qType = (question.getType()==1) ? "Multiple choice" : "Single Choice";

        System.out.println();
        System.out.println(question.getQuestionText());
        for (String i : question.getAnswers().values()) {
            System.out.println(i);
        }
        System.out.println();

        System.out.println("---------------------------------------------------------------------");
        System.out.println("Question type: " + qType);
        System.out.println("Number of students participating: " + responseSubmissions.size());
        System.out.println("---------------------------------------------------------------------");

        System.out.println();

        System.out.println("---------------------------------------------------------------------");
        System.out.println("Response Statistics");
        System.out.println("---------------------------------------------------------------------");
        for (Integer i=0; i< question.getAnswers().size(); i++) {
            System.out.println(question.getAnswers().get(i));
            System.out.println("\t\tNumber voted: " + submissionCounter(i));

        }

        System.out.println();

        /*
         * Used for testing purposes and kept for future testing, however I deemed it unnecessary
         * for the final product.
         *
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Response Results");
        System.out.println("---------------------------------------------------------------------");
        for (String s : responseSubmissions.keySet()) {
            System.out.println("Student: " + s);
            System.out.println("\tVoted: " + responseSubmissions.get(s));
        }
         */

    }

    /**
     * Counts how many times a given option was voted for
     * @param submission - The option being counted
     * @return - How many times submission was voted for
     */
    private int submissionCounter(Integer submission) {
        int count = 0;
        for (HashSet<Integer> i : responseSubmissions.values()) {
            if (i.contains(submission)) {
                count++;
            }
        }
        return count;
    }
}
