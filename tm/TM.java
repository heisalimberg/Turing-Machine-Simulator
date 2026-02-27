package tm;

import java.util.HashMap;

/**
 * This class handles the logic of the Turing Machine.
 * It manages the tape, the head position, and the simulation loop.
 * * @author Abdulalim Ciftci
 */
public class TM {
    
    /** Array of all states in the machine. */
    TMState[] states;
    
    /** * The tape is stored in a HashMap.
     * This allows us to have an infinite tape in both directions.
     */
    HashMap<Integer, Integer> tape; 
    
    /** The current position of the tape head. */
    int head; 
    
    /** The ID of the current state. */
    int currState; 
    
    /** Variables to track the visited range for output. */
    int min;
    int max;

    /**
     * Initializes the Turing Machine with states and input.
     * * @param states The array of state objects.
     * @param input The initial input string for the tape.
     */
    public TM(TMState[] states, String input) {
        this.states = states;
        this.tape = new HashMap<>();
        this.head = 0;
        this.currState = 0;
        this.min = 0;
        
        // Load the input string onto the tape
        if (input != null && input.length() > 0) {
            for (int i = 0; i < input.length(); i++) {
                // Convert char to integer (e.g., '1' becomes 1)
                int val = Integer.parseInt("" + input.charAt(i));
                tape.put(i, val);
            }
            this.max = input.length() - 1;
        } else {
            this.max = 0;
        }
    }

    /**
     * Runs the simulation loop until the machine halts.
     * The machine stops when it reaches the last state.
     */
    public void run() {
        // The halting state is defined as the last state ID.
        int stopState = states.length - 1;

        while (currState != stopState) {
            // 1. Read symbol from tape (default is 0)
            int val = 0;
            if (tape.containsKey(head)) {
                val = tape.get(head);
            }

            // 2. Get the current state object
            TMState s = states[currState];
            
            // 3. Get transition details
            int nextVal = s.next[val];     // Next state ID
            int writeVal = s.write[val];   // Symbol to write
            int moveVal = s.move[val];     // Direction (-1 or +1)

            // 4. Update tape, head, and state
            tape.put(head, writeVal);
            head = head + moveVal;
            currState = nextVal;

            // 5. Update the visited boundaries for the final output
            if (head < min) min = head;
            if (head > max) max = head;
        }
    }

    /**
     * Prints the content of the tape.
     * It prints from the leftmost visited cell to the rightmost visited cell.
     */
    public void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = min; i <= max; i++) {
            if (tape.containsKey(i)) {
                sb.append(tape.get(i));
            } else {
                sb.append("0");
            }
        }
        System.out.println(sb.toString());
    }
}