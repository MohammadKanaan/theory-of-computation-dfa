import java.util.Scanner;

// Do not change the name of the class
public class Main {

    public static void main(String[] args) {
        // Run tests first
        runTests();
        
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
        } while (!yesNo.equals("n") && !yesNo.equals("N"));
    }

    // Do not change the prototype of this method
    public static boolean check(String input) {
        DFA dfa = new DFA();
        return dfa.processString(input);
    }
    
    /**
     * Test method to validate the DFA against known test cases
     */
    public static void runTests() {
        System.out.println("Running test cases for date validation:");
        System.out.println("=====================================");
        
        // Test cases with expected results
        String[][] testCases = {
            {"01/01/2024", "y"},
            {"13/15/2023", "n"},
            {"12/31/1999", "y"},
            {"02/29/2020", "y"},
            {"1/01/2024", "n"},
            {"10/10/9999", "y"},
            {"01/1/2024", "n"},
            {"12/00/2024", "n"},
            {"03/05/2001", "y"},
            {"00/10/2024", "n"},
            {"11/30/0000", "n"},
            {"01-01-2024", "n"},
            {"09/09/2009", "y"},
            {"01012024", "n"},
            {"12/32/2024", "n"}
        };
        
        int passedTests = 0;
        
        for (String[] testCase : testCases) {
            String input = testCase[0];
            boolean expected = testCase[1].equals("y");
            boolean result = check(input);
            
            boolean passed = (result == expected);
            
            if (passed) {
                passedTests++;
            }
            
            System.out.printf("Test: %s - Expected: %s - Result: %s - %s%n", 
                input,
                expected ? "valid" : "invalid",
                result ? "valid" : "invalid",
                passed ? "PASSED" : "FAILED");
        }
        
        System.out.println("Test Results: " + passedTests + "/" + testCases.length + " tests passed");
    }
}