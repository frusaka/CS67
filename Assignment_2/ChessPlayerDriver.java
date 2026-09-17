/*
From the readings, I was able to revise a bit on format specifiers because it's difficult
to memorize or the different formats.
 */

import java.util.Scanner;

public class ChessPlayerDriver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ChessPlayer magnus = new ChessPlayer("Magnus Carlson", "GM", 2832);
        
        System.out.print("Enter your name: ");
        String name = input.nextLine();

        System.out.print("Enter your title: ");
        String title = input.nextLine();
        title = title.length()>0 ? title: null;

        System.out.print("Enter your Elo: ");
        int elo = input.nextInt();

        ChessPlayer user = new ChessPlayer(name, title, elo);

        System.out.println(user);
        System.out.println(magnus);
        if (elo > 2832)
        {
            System.out.println("Wow, you're better than Magnus!");
        } else 
        {

            System.out.printf("Magnus has %d more elo than you. What a shame...\n", 2832-elo);
        }
        input.close();
    }
}
