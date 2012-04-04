/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.macaw.messages;

import java.io.Serializable;

/**
 *
 * @author sunye
 */
public class Message implements Serializable, Comparable<Message> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * Message Identification.
     */
    private int id;

    protected Message(int i) {
        id = i;
    }

    /*
     * Compares the order of this object with the order of the specified object for id.
     *
     * @param o - the Message to be compared.
     * @return int
     *  -1 if the id of this object is less than the id of the specified object
     *  0 if the id of this object is equal to the id of the specified object
     *  1 if the id of this object is greater to the id of the specified object
     */
    public int compareTo(Message other) {

        if (id < other.id) {
            return -1;
        }

        if (id > other.id) {
            return 1;
        }

        return 0;
    }
    /*
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o - the reference object with which to compare.
     * @return boolean - true if this object is the same as the object argument; false otherwise.
     */

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Message)) {
            return false;
        } else {
            Message other = (Message) o;
            return id == other.id;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        return hash;
    }
}
