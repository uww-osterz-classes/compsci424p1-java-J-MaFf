/* COMPSCI 424 Program 1
 * Name: Joey Maffiola
 * File: Version2.java
 */
package compsci424.p1.java;

/**
 * Implements the process creation hierarchy for Version 2, which does
 * not use linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
public class Version2 {
    // Declare any class/instance variables that you need here.
    private Version2PCB[] pcbArray;

    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed.
     */
    public Version2() {
        this.pcbArray = new Version2PCB[16]; // assuming 16 processes max
        pcbArray[0] = new Version2PCB(-1); // Initial process 0 has no parent
    }

    /**
     * Creates a new child process of the process with ID parentPid.
     * 
     * @param parentPid the PID of the new process's parent
     * @return 0 if successful, -1 if unsuccessful
     */
    int create(int parentPid) {
        if (pcbArray[parentPid] == null) { // If parentPid is not in the process hierarchy, do nothing;
            return -1; // unsuccessful
        }
        // 1. Allocate and initialize a free PCB object from the array of PCB objects
        int newPcbIndex = findFreePcbIndex();
        // 2. Connect the new PCB object to its parent
        pcbArray[newPcbIndex] = new Version2PCB(parentPid);
        // its older sibling (if any), and its younger sibling (if any)
        int firstChild = pcbArray[parentPid].getFirstChild();
        if (firstChild == -1) { // Parent has no first child
            pcbArray[parentPid].setFirstChild(newPcbIndex); // Set first child to new process
            return 0;
        }
        // Parent has a first child
        int nextChild = pcbArray[firstChild].getYoungerSibling();
        while (nextChild != -1) { // Find the last child
            nextChild = pcbArray[nextChild].getYoungerSibling();
        }
        // Set the new process as the last child's younger sibling
        pcbArray[nextChild].setYoungerSibling(newPcbIndex);
        // Set the new process's older sibling to the last child
        pcbArray[newPcbIndex].setOlderSibling(nextChild);
        return 0; // successful
    }

    /**
     * Recursively destroys the process with ID parentPid and all of
     * its descendant processes (child, grandchild, etc.).
     * 
     * @param targetPid the PID of the process to be destroyed
     * @return 0 if successful, not 0 if unsuccessful
     */
    int destroy(int targetPid) {
        // If targetPid is not in the process hierarchy, do nothing;
        // your code may return an error code or message in this case,
        // but it should not halt

        // Assuming you've found the PCB for targetPid in the PCB array:
        // 1. Recursively destroy all descendants of targetPid, if it
        // has any, and mark their PCBs as "free" in the PCB array
        // (i.e., deallocate them)

        // 2. Adjust connections within the hierarchy graph as needed to
        // re-connect the graph

        // 3. Deallocate targetPid's PCB and mark its PCB array entry
        // as "free"

        // You can decide what the return value(s), if any, should be.
        // If you change the return type/value(s), update the Javadoc.
        return 0; // often means "success" or "terminated normally"
    }

    /**
     * Traverse the process creation hierarchy graph, printing
     * information about each process as you go. See Canvas for the
     * *required* output format.
     * 
     * You can directly use "System.out.println" statements (or
     * similar) to send the required output to stdout, or you can
     * change the return type of this function to return the text to
     * the main program for printing. It's your choice.
     */
    void showProcessInfo() {

    }

    /* If you need or want more methods, feel free to add them. */

    /**
     * Finds the index of the first available PCB (Process Control Block) in the
     * pcbArray.
     * 
     * @return The index of the first available PCB, or -1 if no PCB is available.
     */
    int findFreePcbIndex() {
        for (int i = 0; i < pcbArray.length; i++) {
            if (pcbArray[i] == null) {
                return i; // successful
            }
        }
        return -1; // unsuccessful
    }

}
