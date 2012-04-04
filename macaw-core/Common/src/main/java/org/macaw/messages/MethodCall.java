package org.macaw.messages;

import java.io.Serializable;


/**
 * @author sunye
 *
 */
public class MethodCall extends Message {


    /**
     * Method name
     */
    private String name;
    
    private Serializable[] arguments;
    /**
     * Method execution timeout (in milliseconds).
     */
    private int timeout;

    /*
     * Create a method call
     */
    protected MethodCall(int id, String name, Serializable[] args, int timeout) {
        super(id);
        this.name = name;
        this.arguments = args;
        this.timeout = timeout;
    }

    /**
     * Returns a string representation of the method description . The toString
     * method returns a string consisting of the name, the test case and the
     * order of the class of which the object is an instance
     *
     * @return String a string representation of the method description
     */
    @Override
    public String toString() {
        return String.format("Method: %s", name);
    }


    /**
     * Returns the name associated to method
     * @return String
     */
    public String name() {
        return name;
    }

    /**
     * Returns the method execution timeout (in milliseconds)
     * @return int
     */
    public int timeout() {
        return timeout;
    }


    /** 
     * @return Serializable[] the arguments of the call.
     */
    public Serializable[] arguments() {
        return arguments;
    }
}
