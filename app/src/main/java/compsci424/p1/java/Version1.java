/* COMPSCI 424 Program 1
 * Name: Joey Maffiola
 */
package compsci424.p1.java;

import java.util.LinkedList;

/**
 * Implements the process creation hierarchy for Version 1, which uses
 * linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
public class Version1 {
    // Declare any class/instance variables that you need here.
    private Version1PCB[] pcbArray;

    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed.
     */
    public Version1() {
        this.pcbArray = new Version1PCB[10]; // assuming 10 processes max
        pcbArray[0] = new Version1PCB(-1); // process 0 has no parent
    }

    /**
     * Creates a new child process of the process with parentPid.
     * 
     * @param parentPid the PID of the new process's parent
     * @return 0 if successful, -1 if unsuccessful
     */
    int create(int parentPid) {
        if (pcbArray[parentPid] == null) { // If parentPid is not in the process hierarchy, do nothing;
            System.out.println("Parent process does not exist.");
            return -1; // unsuccessful
        }

        // 1. Allocate and initialize a free PCB object from the array of PCB objects
        Version1PCB newPCB = new Version1PCB(parentPid);
        int newPcbIndex = findFreePcbIndex();
        pcbArray[newPcbIndex] = newPCB;

        // 2. Insert the newly allocated PCB object into parentPid's list of children
        pcbArray[parentPid].addChild(newPcbIndex);

        System.out.println("Process " + newPcbIndex + " has been created.");
        return 0; // successful
    }

    /**
     * Recursively destroys the process with ID parentPid and all of
     * its descendant processes (child, grandchild, etc.).
     * 
     * @param targetPid the PID of the process to be destroyed
     * @return 0 if successful, -1 if unsuccessful
     */
    int destroy(int targetPid) {

        if (pcbArray[targetPid] == null) { // If targetPid is not in the process hierarchy, do nothing
            System.out.println("Target process does not exist.");
            return -1; // unsuccessful
        }

        // Assuming you've found the PCB for targetPid in the PCB array:
        // 1. Recursively destroy all descendants of targetPid, if it
        // has any, and mark their PCBs as "free" in the PCB array
        // (i.e., deallocate them)

        for (int child : pcbArray[targetPid].getChildren()) { // 1. Recursively destroy all descendants of targetPid
            destroy(child);
        }

        pcbArray[targetPid].getChildren().remove(targetPid); // 2. Remove targetPid from its parent's list of children

        pcbArray[targetPid] = null; // 3. Deallocate targetPid's PCB and mark its PCB array entry as "free"

        System.out.println("Process " + targetPid + " and all it's children have been destroyed.");
        return 0; // successful
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
        for (int i = 0; i < pcbArray.length; i++) {
            System.out.println("Process" + pcbArray[i] + ": parent is " + pcbArray[i].getParent() + " and ");
            LinkedList<Integer> children = pcbArray[i].getChildren();
            if (children.isEmpty()) {
                System.out.println("has no children");
            } else {
                System.out.println("and children are ");
                for (int child : children) {
                    System.out.println(child + " ");
                }
            }

        }
    }

    /* If you need or want more methods, feel free to add them. */

    /**
     * Finds the first free index in the PCB array.
     * 
     * @return the index of the first free PCB object in the array
     */
    int findFreePcbIndex() {
        for (int i = 0; i < pcbArray.length; i++) {
            if (pcbArray[i] == null) {
                return i;
            }
        }
        return -1; // Return -1 if no free index is found
    }

}
