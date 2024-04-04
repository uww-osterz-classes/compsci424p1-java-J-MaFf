/* COMPSCI 424 Program 1
 * Name: Joey Maffiola
 * File: Program1.java
 */
package compsci424.p1.java;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Main class for this program. The required steps have been copied
 * into the main method as comments. Feel free to add more comments to
 * help you understand your code, or for any other reason. Also feel
 * free to edit this comment to be more helpful for you.
 */
public class Program1 {
    // Declare any class/instance variables that you need here.
    static LinkedList<String> actions = new LinkedList<String>();

    /**
     * @param args command-line arguments, which can be ignored
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Ask the user to enter commands of the form "create N",
        // "destroy N", or "end", where N is an integer between 0
        // and 15.

        System.out.println("Enter commands of the form \"create N\", \"destroy N\", or \"end\".");
        System.out.println("N is an integer between 0 and 15.");
        String command = scanner.nextLine().toLowerCase();
        actions.add(command);

        // 2. While the user has not typed "end", continue accepting
        // commands. Add each command to a list of actions to take
        // while you run the simulation.

        // 3. When the user types "end" (or optionally any word that
        // is not "create" or "destroy"), stop accepting commands
        // and complete the following steps.
        //
        // Hint: Steps 2 and 3 refer to the same loop. ;-)

        while (!command.equalsIgnoreCase("end")) {
            System.out.println("Enter another command or type end to run your commands: ");
            command = scanner.nextLine().toLowerCase();
            actions.add(command);
        }

        // 4. Create an object of the Version 1 class and an object of
        // the Version 2 class.

        Version1 v1Obj = new Version1();
        Version2 v2Obj = new Version2();

        // 5. Run the command sequence once with the Version 1 object,
        // calling its showProcessTree method after each command to
        // show the changes in the tree after each command.

        runCommands(v1Obj);
        // 6. Repeat step 5, but with the Version 2 object.
        runCommands(v2Obj);
        // 7. Store the current system time in a variable
        long startTime = System.currentTimeMillis();
        // ... then run the command sequence 200 times with Version 1.
        for (int i = 0; i < 200; i++) {
            // System.out.println("Iteration " + i + ":\n");
            runCommands(v1Obj);
        }
        // ... After this, store the new current system time in a second variable.
        long endTime = System.currentTimeMillis();

        // Subtract the start time from the end time to get the Version 1 running time,
        // then display the Version 1 running time.

        long runTime = endTime - startTime;
        System.out.println("Version 1 running time for 200 iterations: " + runTime + " milliseconds.");

        // 8. Repeat step 7, but with the Version 2 object.
        startTime = System.currentTimeMillis();

        for (int i = 0; i < 200; i++) {
            // System.out.println("Iteration " + i + ":\n");
            runCommands(v2Obj);
        }

        endTime = System.currentTimeMillis();
        runTime = endTime - startTime;
        System.out.println("Version 2 running time for 200 iterations: " + runTime + " milliseconds.");

        scanner.close();
        // This line is here just to test the Gradle build procedure.
        // You can delete it.
        System.out.println("Builds without errors and runs to completion.");
    }

    /**
     * Executes a series of commands on the given Version1 object.
     *
     * @param v1Obj the Version1 object on which the commands will be executed
     */
    private static void runCommands(Version1 v1Obj) {
        LinkedList<String> localActions = actions;
        while (!localActions.isEmpty() && !localActions.peek().equalsIgnoreCase("end")) {
            String currentCommand = localActions.remove();
            String[] parts = currentCommand.split(" ");
            int TargetPID = Integer.parseInt(parts[1]);
            switch (parts[0]) {
                case "create":
                    v1Obj.create(TargetPID);
                    break;

                case "destroy":
                    v1Obj.destroy(TargetPID);
                    break;

                default:
                    System.out.println("Unknown Command: " + parts[0] + "\nSkipping command...");
                    break;
            }
            v1Obj.showProcessInfo();
        }

    }

    /**
     * Runs a series of commands on the given Version2 object.
     *
     * @param v2Obj the Version2 object on which the commands will be executed
     */
    private static void runCommands(Version2 v2Obj) {
        LinkedList<String> localActions = actions;
        while (!localActions.isEmpty() && !localActions.peek().equalsIgnoreCase("end")) {
            String currentCommand = localActions.remove();
            String[] parts = currentCommand.split(" ");
            int TargetPID = Integer.parseInt(parts[1]);
            switch (parts[0]) {
                case "create":
                    v2Obj.create(TargetPID);
                    break;

                case "destroy":
                    v2Obj.destroy(TargetPID);
                    break;

                default:
                    System.out.println("Unknown Command: " + parts[0] + "\nSkipping command...");
                    break;
            }
            v2Obj.showProcessInfo();
        }
    }
}
