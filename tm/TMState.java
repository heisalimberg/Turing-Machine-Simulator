package tm;

/**
 * Represents a single state in the Turing Machine.
 * It holds the transition rules for each symbol.
 * * @author Abdulalim Ciftci
 */
public class TMState {
    
    /** The ID number of the state. */
    int id;
    
    /** * Arrays to store the transition rules.
     * We use arrays because symbols are just integers.
     */
    int[] next;
    int[] write;
    int[] move; // -1 for Left, +1 for Right

    /**
     * Creates a new state.
     * * @param id The identifier for this state.
     * @param numSymbols The total number of input symbols.
     */
    public TMState(int id, int numSymbols) {
        this.id = id;
        // We add +1 size because the blank symbol is 0.
        next = new int[numSymbols + 1];
        write = new int[numSymbols + 1];
        move = new int[numSymbols + 1];
    }

    /**
     * Adds a transition rule for a specific symbol.
     * * @param symbol The symbol read from the tape.
     * @param nextState The ID of the state to go to.
     * @param writeSymbol The symbol to write on the tape.
     * @param direction The direction to move ('L' or 'R').
     */
    public void addRule(int symbol, int nextState, int writeSymbol, String direction) {
        next[symbol] = nextState;
        write[symbol] = writeSymbol;
        
        if (direction.equals("L")) {
            move[symbol] = -1;
        } else {
            move[symbol] = 1;
        }
    }
}