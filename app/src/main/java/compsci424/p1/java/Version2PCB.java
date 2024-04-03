/* COMPSCI 424 Program 1
 * Name: Joey Maffiola
 * File: Version2PCB.java
 */
package compsci424.p1.java;

/**
 * The process control block structure that is used to track a
 * process's parent, first child, younger sibling, and older sibling
 * (if they exist) in Version 2.
 */
public class Version2PCB {
    private int parent, firstChild, youngerSibling, olderSibling;

    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed.
     * 
     * @param parent
     */
    Version2PCB(int parent) {
        this.parent = parent;
        this.firstChild = -1;
        this.youngerSibling = -1;
        this.olderSibling = -1;
    }

}
