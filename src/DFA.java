import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DFA {

    enum Q {
        a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, TRAP
    }

    private final Q startState;
    private final Set<Q> acceptingStates;
    // this map contains consists of a key (the current state) and a value (a map of the transitions for that state)
    // I provided a visualization of this structure
    private final Map<Q, Map<Character, Q>> transitions;

    private void addTransition(Q from, char symbol, Q to) {
        // checking if there is an existing transition for the current state and if not creating a new one, IntelliJ suggested I replace it with computeIfAbsent so I did
        Map<Character, Q> stateTransitions = transitions.computeIfAbsent(from, k -> new HashMap<>());
        stateTransitions.put(symbol, to);
    }

    // adds a range of transitions from startChar to endChar that lead to the same state
    // in my DFA I used A and B but subtracting sets in Java proved to be complicated so I added this helper function to avoid adding each transition by hand.
    private void addRangeTransition(Q from, char startChar, char endChar, Q to) {
        // same as above
        Map<Character, Q> stateTransitions = transitions.computeIfAbsent(from, k -> new HashMap<>());

        for (char symbol = startChar; symbol <= endChar; symbol++) {
            // edge case where the startChar is greater than the endChar (e.g. startChar '9' > endChar = '0')
            if (startChar > endChar) break;
            stateTransitions.put(symbol, to);
        }
    }


    public DFA() {
        startState = Q.a;

        acceptingStates = new HashSet<>();
        acceptingStates.add(Q.o);

        transitions = new HashMap<>();

        // Qa
        addTransition(Q.a, '0', Q.b);
        addTransition(Q.a, '1', Q.c);

        // Qb
        addRangeTransition(Q.b, '1', '9', Q.d);

        // Qc
        addRangeTransition(Q.c, '0', '2', Q.d);

        // Qd
        addTransition(Q.d, '/', Q.e);

        // Qe
        addRangeTransition(Q.e, '1', '2', Q.f);
        addTransition(Q.e, '0', Q.g);
        addTransition(Q.e, '3', Q.h);

        // Qf
        addRangeTransition(Q.f, '0', '9', Q.i);

        // Qg
        addRangeTransition(Q.g, '1', '9', Q.i);

        // Qf
        addRangeTransition(Q.h, '0', '1', Q.i);

        // Qi
        addTransition(Q.i, '/', Q.j);

        // Qj
        addTransition(Q.j, '1', Q.k);
        addRangeTransition(Q.j, '2', '9', Q.l);

        // Qk
        addTransition(Q.k, '9', Q.m);

        // Ql
        addRangeTransition(Q.l, '0', '9', Q.m);

        // Qm
        addRangeTransition(Q.m, '0', '9', Q.n);

        // Qn
        addRangeTransition(Q.n, '0', '9', Q.o);

        // Qo has no transitions
    }

    // this method reads every character in the string and processes it in the DFA
    public boolean processString(String input) {
        Q currentState = startState;
        System.out.println("Starting at state: " + currentState);

        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            System.out.print("Current state: " + currentState + ", Input: '" + symbol + "' -> ");

            // Check if the current state has transitions for the current symbol
            // this map contains the transitions for the current state
            Map<Character, Q> stateTransitions = transitions.get(currentState);

            // if there are no transitions for the current state, we send it to the trap state
            // in the case the input is empty (the input ends on a final state), this won't be a problem since the loop won't run
            if (stateTransitions == null) {
                System.out.println("No transitions defined for state " + currentState);
                currentState = Q.TRAP;
                continue;
            }

            Q nextState = stateTransitions.get(symbol);

            // if there are no transitions for the chosen symbol, we send it to the trap state
            if (nextState == null) {
                System.out.println("No transition for symbol '" + symbol + "' from state " + currentState);
                currentState = Q.TRAP;
                continue;
            }

            currentState = nextState;
            System.out.println("New state: " + currentState);
        }

        System.out.println("Finished processing. Final state: " + currentState);
        boolean accepted = acceptingStates.contains(currentState);
        System.out.println("Is final state accepting? " + accepted);
        return accepted;
    }
}