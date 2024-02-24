/* COMPSCI 424 Program 1
 * Name: Joey Maffiola
 */
package compsci424.p1.java;

/**
 * Main class for this program. The required steps have been copied
 * into the main method as comments. Feel free to add more comments to
 * help you understand your code, or for any other reason. Also feel
 * free to edit this comment to be more helpful for you.
 */
public class Program1 {
    // Declare any class/instance variables that you need here.

    /**
     * @param args command-line arguments, which can be ignored
     */
    public static void main(String[] args) {

        // 1. Ask the user to enter commands of the form "create N",
        // "destroy N", or "end", where N is an integer between 0
        // and 15.

        // 2. While the user has not typed "end", continue accepting
        // commands. Add each command to a list of actions to take
        // while you run the simulation.
        // 3. When the user types "end" (or optionally any word that
        // is not "create" or "destroy"), stop accepting commands
        // and complete the following steps.
        //
        // Hint: Steps 2 and 3 refer to the same loop. ;-)

        // 4. Create an object of the Version 1 class and an object of
        // the Version 2 class.

        // 5. Run the command sequence once with the Version 1 object,
        // calling its showProcessTree method after each command to
        // show the changes in the tree after each command.

        // 6. Repeat step 5, but with the Version 2 object.

        // 7. Store the current system time in a variable

        // ... then run the command sequence 200 times with Version 1.

        // ... After this, store the new current system time in a
        // second variable. Subtract the start time from the end
        // time to get the Version 1 running time, then display
        // the Version 1 running time.

        // 8. Repeat step 7, but with the Version 2 object.

        // This line is here just to test the Gradle build procedure.
        // You can delete it.
        System.out.println("Builds without errors and runs to completion.");
    }
}
