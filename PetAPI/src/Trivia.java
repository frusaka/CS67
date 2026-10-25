import java.util.Scanner;

public class Trivia {
    private String question;
    private String[] options;

    private String answer;

    public Trivia(String question, String a, String b, String c, String d, String answer) {
        this.question = question;
        this.options = new String[]{a, b, c, d};
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean askQuestion(Scanner scanner) {
        System.out.println(question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Enter your answer (1-4): ");
        int userAnswerIndex = Integer.parseInt(scanner.nextLine()) - 1;
        while (userAnswerIndex < 0 || userAnswerIndex >= options.length) {
            System.out.println("Invalid answer. Please enter a number between 1 and 4.");
            userAnswerIndex = Integer.parseInt(scanner.nextLine()) - 1;
        }
        String userAnswer = options[userAnswerIndex];
        if (userAnswer.equals(answer)) {
            System.out.println("Correct!");
            return true;
        } else {
            System.out.println("Incorrect! The correct answer is: " + answer);
            return false;
        }
    }

    @Override
    public String toString() {
        return "Trivia{" +
                "question='" + question + '\'' +
                ", options=" + java.util.Arrays.toString(options) +
                ", answer='******'" +
                '}';
    }
}
