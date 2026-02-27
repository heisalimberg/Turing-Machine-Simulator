package tm;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * The main class for the Turing Machine Simulator.
 * It reads the input file, creates the machine, and runs the simulation.
 * * @author Abdulalim Ciftci
 */
public class TMSimulator {

    /**
     * The main entry point of the program.
     * * @param args Command line arguments. args[0] should be the filename.
     */
    public static void main(String[] args) {
        // Check if the file name is provided
        if (args.length < 1) {
            System.out.println("Error: No input file provided.");
            return;
        }

        String filename = args[0];
        TMState[] states = null;
        String inputLine = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            // Line 1: Total number of states
            String line = br.readLine();
            int numStates = Integer.parseInt(line.trim());

            // Line 2: Number of symbols in the alphabet
            line = br.readLine();
            int numSymbols = Integer.parseInt(line.trim());

            // Initialize the state array
            states = new TMState[numStates];
            for (int i = 0; i < numStates; i++) {
                states[i] = new TMState(i, numSymbols);
            }

            // Read transitions from the file.
            // We loop through states and then symbols.
            // The halting state (last one) does not have transitions.
            for (int i = 0; i < numStates - 1; i++) {
                for (int j = 0; j <= numSymbols; j++) {
                    line = br.readLine();
                    if (line != null) {
                        String[] parts = line.trim().split(",");
                        
                        int nextSt = Integer.parseInt(parts[0]);
                        int writeSym = Integer.parseInt(parts[1]);
                        String dir = parts[2];
                        
                        // Add the rule to the current state
                        states[i].addRule(j, nextSt, writeSym, dir);
                    }
                }
            }

            // Last line: The input string for the tape
            inputLine = br.readLine();
            if (inputLine == null) {
                inputLine = "";
            }
            
            br.close();

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Create and run the Turing Machine
        TM tm = new TM(states, inputLine.trim());
        tm.run();
        tm.print();
    }
}