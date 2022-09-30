import java.util.HashMap;

class Question {
    // 1 = multiple choice question, 2 = single choice question
    private int type;

    // Stores question's String text
    private String questionText;

    // Stores answers to questions of both types
    private HashMap<Integer, String> answers;

    Question(int t, String qtxt, HashMap<Integer, String> ans) {
        type = t;
        questionText = qtxt;
        answers = ans;
    }

    public int getType() {
        return type;
    }

    public String getQuestionText() {
        return questionText;
    }

    public HashMap<Integer, String> getAnswers() {
        return answers;
    }
}
