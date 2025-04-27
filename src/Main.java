import java.util.Scanner;

// Do not change the name of the class
public class Main {

    public static void main(String[] args) {
        String yesNo;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Your date: ");
            String date = scan.nextLine();
            if (check(date)) {
                System.out.println(date + " is valid");
            } else {
                System.out.println(date + " is invalid");
            }
            System.out.println("Do you want to enter an new date? (y/n)");
            yesNo = scan.nextLine();
        } while (!yesNo.equals("n") || !yesNo.equals("N"));
    }

    // Do not change the prototype of this method
    public static boolean check(String input) {
        DFA dfa = new DFA();
        return dfa.processString(input);
    }

}