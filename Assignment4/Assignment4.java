/*
Secret Message #1: EatMoreVegetables
Secret Message #2: FourCharsAfterW
Secret Message #3: tinyurl.com/3s847myv
Got rick-rolled haha 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment4 {
    public static void main(String[] args) {
        File file = new File("Assignment4Input.txt");
        try {
            Scanner stream = new Scanner(file);
            String msg1 = "", msg2 = "", msg3 = "";
            while (stream.hasNext())
            {
                String line = stream.nextLine();
                msg1 += message1(line);
                msg2 += message2(line);
                msg3 += message3(line);
            }
            System.out.println("Secret Message #1: "+msg1);
            System.out.println("Secret Message #2: "+msg2);
            System.out.println("Secret Message #3: "+msg3);
            stream.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred");
        }

    }

    private static String message1(String coded){
        for (int i=0; i<coded.length(); i++)
        {
            if (coded.charAt(i) == 'X'){
                return coded.substring(i+3, i+4);
            }
        }
        return "";
    }

    private static String message2(String coded){
        for (int i=0; i<coded.length(); i++)
        {
            if (coded.charAt(i) == 'Y'){
                return coded.substring(i+5, i+6);
            }
        }
        return "";
    }

    private static String message3(String coded){
        for (int i=0; i<coded.length(); i++)
        {
            if (coded.charAt(i) == 'W'){
                return coded.substring(i+4, i+5);
            }
        }
        return "";
    }

}
