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
            System.out.println("Parent process does not exist.");
            return -1; // unsuccessful
        }
        // 1. Allocate and initialize a free PCB object from the array of PCB objects
        Version2PCB newPCB = new Version2PCB(parentPid);
        int newPcbIndex = findFreePcbIndex();
        // 2. Connect the new PCB object to its parent
        pcbArray[newPcbIndex] = newPCB;
        // its older sibling (if any), and its younger sibling (if any)
        int firstChild = pcbArray[parentPid].getFirstChild();
        if (firstChild == -1) { // Parent has no first child
            pcbArray[parentPid].setFirstChild(newPcbIndex); // Set first child to new process
            return 0;
        }
        // Parent has a first child
        int currentChild = firstChild;
        while (pcbArray[currentChild].getYoungerSibling() != -1) { // While current child has an older sibling
            currentChild = pcbArray[currentChild].getYoungerSibling();
        }
        // current child is now the youngest child
        pcbArray[currentChild].setYoungerSibling(newPcbIndex);
        pcbArray[newPcbIndex].setOlderSibling(currentChild);

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
        if (pcbArray[targetPid] == null) { // If targetPid is not in the process hierarchy, do nothing;
            System.out.println("Target process does not exist.");
            return -1; // unsuccessful
        }

        // 1. Recursively destroy all descendants of targetPid, if it has any, and mark
        // their PCBs as "free" in the PCB array (i.e., deallocate them)
        int firstChild = pcbArray[targetPid].getFirstChild();
        for (int child = firstChild; child != -1; child = pcbArray[child].getYoungerSibling()) {
            destroy(child);
        }

        // 2. Adjust connections within the hierarchy graph
        int olderSibling = pcbArray[targetPid].getOlderSibling();
        int youngerSibling = pcbArray[targetPid].getYoungerSibling();
        if (olderSibling != -1) { // targetPid has an older sibling
            pcbArray[olderSibling].setYoungerSibling(youngerSibling);
            if (youngerSibling != -1) { // targetPid has a younger sibling
                pcbArray[youngerSibling].setOlderSibling(olderSibling);
            }
        } else { // targetPid is the first child
            pcbArray[pcbArray[targetPid].getParent()].setFirstChild(youngerSibling);
            if (youngerSibling != -1) { // targetPid has a younger sibling
                pcbArray[youngerSibling].setOlderSibling(-1);
            }
        }

        // 3. Deallocate targetPid's PCB and mark its PCB array entry as "free"

        pcbArray[targetPid] = null;
        return 0; // successful
    }

    /**
     * Prints information to the console about each process in the pcbArray.
     */
    void showProcessInfo() {
        for (int i = 0; i < pcbArray.length; i++) { // For each process in the PCB array
            if (pcbArray[i] == null) {
                continue;
            }
            System.out.print("Process" + i + ": parent is " + pcbArray[i].getParent() + " and ");
            int firstChild = pcbArray[i].getFirstChild();

            if (firstChild == -1) { // If process has no children
                System.out.println("has no children");
            } else {
                System.out.print("and children are " + pcbArray[firstChild] + " ");
                int currentChild = pcbArray[firstChild].getYoungerSibling();
                while (currentChild != 1) { // While there are younger siblings
                    System.out.print(pcbArray[currentChild] + " ");
                    currentChild = pcbArray[currentChild].getYoungerSibling();
                }
                System.out.println();
            }
        }
    }

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
