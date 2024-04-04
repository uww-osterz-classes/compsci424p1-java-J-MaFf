/* COMPSCI 424 Program 1
 * Name: Joey Maffiola
 * File: Version1.java
 */
package compsci424.p1.java;

import java.util.LinkedList;

/**
 * Implements the process creation hierarchy for Version 1, which uses
 * linked lists.
 * 
 * This class contains methods to create and destroy processes, as well as
 * a method to print information about each process in the PCB array.
 */

public class Version1 {
    // Declare any class/instance variables that you need here.
    private Version1PCB[] pcbArray;

    /**
     * Represents a version 1 Process Control Block.
     * This constructor initializes an array of Version1PCB objects and sets the
     * initial
     * process.
     */
    public Version1() {
        this.pcbArray = new Version1PCB[16]; // assuming 16 processes max
        pcbArray[0] = new Version1PCB(-1); // Initial process 0 has no parent
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

        for (int child : pcbArray[targetPid].getChildren()) { // 1. Recursively destroy all descendants of targetPid
            destroy(child);
        }

        pcbArray[targetPid].getChildren().remove(targetPid); // 2. Remove targetPid from its parent's list of children

        pcbArray[targetPid] = null; // 3. Deallocate targetPid's PCB and mark its PCB array entry as "free"

        System.out.println("Process " + targetPid + " and all it's children have been destroyed.");
        return 0; // successful
    }

    /**
     * Prints information to the console about each process in the pcbArray.
     */
    void showProcessInfo() {
        for (int i = 0; i < pcbArray.length; i++) {
            if (pcbArray[i] == null)
                continue;
            System.out.print("Process" + i + ": parent is " + pcbArray[i].getParent() + " and ");
            LinkedList<Integer> children = pcbArray[i].getChildren();
            if (children.isEmpty()) {
                System.out.println("has no children");
            } else {
                System.out.print("and children are ");
                for (int child : children) {
                    System.out.print(child + " ");
                }
                System.out.println();
            }

        }
    }

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
