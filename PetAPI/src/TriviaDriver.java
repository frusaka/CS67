import java.util.List;
import java.util.Scanner;

public class TriviaDriver {
    public static void main(String[] args) {
        TriviaAPI.initiate(false);
        boolean continueLoop = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Trivia Game!");
        while (continueLoop) {
            System.out.println("Choose an option:");
            System.out.println("1. Start a trivia game with all questions");
            System.out.println("2. List all trivia questions");
            System.out.println("3. Get trivia question by ID");
            System.out.println("4. Get trivia questions by keyword");
            System.out.println("5. Get custom trivia questions");

            System.out.println("6. Add a new trivia question");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    List<Trivia> allTrivia = TriviaAPI.getAllTrivia();
                    gameLoop(allTrivia, scanner);
                    break;
                case "2":
                    List<Trivia> triviaList = TriviaAPI.getAllTrivia();
                    for (Trivia trivia : triviaList) {
                        System.out.println(trivia);
                    }
                    break;
                case "3":
                    System.out.print("Enter the ID of the trivia question: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Trivia triviaById = TriviaAPI.getTriviaById(id);
                    if (triviaById != null) {
                        triviaById.askQuestion(scanner);
                    } else {
                        System.out.println("No trivia found with ID: " + id);
                    }
                    break;
                case "4":
                    System.out.print("Enter a keyword to search for trivia questions: ");
                    String keyword = scanner.nextLine();
                    gameLoop(TriviaAPI.getTriviaByKeyword(keyword), scanner);
                    break;
                case "5":
                    gameLoop(TriviaAPI.getCustomTrivia(), scanner);
                    break;
                case "6":
                    System.out.print("Enter the question for the new trivia: ");
                    String question = scanner.nextLine();
                    System.out.print("Enter option A: ");
                    String optionA = scanner.nextLine();
                    System.out.print("Enter option B: ");
                    String optionB = scanner.nextLine();
                    System.out.print("Enter option C: ");
                    String optionC = scanner.nextLine();
                    System.out.print("Enter option D: ");
                    String optionD = scanner.nextLine();
                    System.out.print("Enter the correct answer (A-D): ");
                    String correctAnswer = scanner.nextLine();
                    Trivia newTrivia = new Trivia(question, optionA, optionB, optionC, optionD, correctAnswer);
                    TriviaAPI.addTrivia(newTrivia);
                    System.out.println("Trivia question added successfully!");
                    break;
                case "7":
                    continueLoop = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
        TriviaAPI.closeConnection();
    }
    private static void gameLoop(List<Trivia> triviaList, Scanner scanner) {
        System.out.print("Maximum number of questions to ask (or press Enter for all): ");
        String maxQuestionsInput = scanner.nextLine();
        int maxQuestions = triviaList.size();
        if (!maxQuestionsInput.isEmpty()) {
            while (true) {
                try {
                    maxQuestions = Integer.parseInt(maxQuestionsInput);
                    if (maxQuestions < 1 || maxQuestions > triviaList.size()) {
                        System.out.println("Invalid number. Please enter a number between 1 and " + triviaList.size() + ": ");
                        maxQuestionsInput = scanner.nextLine();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number: ");
                    maxQuestionsInput = scanner.nextLine();
                }
                break;
            }
        }
        int score = 0;
        for (Trivia trivia : triviaList.subList(0, maxQuestions)) {
            score += trivia.askQuestion(scanner) ? 1 : 0;
        }
        System.out.println("Game over! Your score is: " + score + " out of " + maxQuestions);
    }
}
