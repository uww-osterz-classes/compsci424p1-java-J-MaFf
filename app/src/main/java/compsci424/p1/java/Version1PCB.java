/* COMPSCI 424 Program 1
 * Name: Joey Maffiola
 */
package compsci424.p1.java;

import java.util.LinkedList;

/**
 * The process control block structure that is used to track a
 * process's parent and children (if any) in Version 1.
 */
public class Version1PCB {
    private int parent; // a PCB index corresponding to the process's creator (parent)
    private LinkedList<Integer> children; // each list element contains the PCB index of one child process

    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed.
     */
    public Version1PCB(int parent) {
        {
            this.parent = parent;
            this.children = new LinkedList<Integer>();
        }
    }

    public void addChild(int child) {
        this.children.add(child);
    }

    public int getParent() {
        return parent;
    }

    public LinkedList<Integer> getChildren() {
        return children;
    }

}
