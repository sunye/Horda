package org.macaw.messages;

/**
 *
 * @author sunye
 */
public class MethodResult extends Message {

    private MethodCall md;
    private long start;
    private long stop;
    private Object result;
    private Throwable error;


    protected MethodResult(int id, MethodCall md) {
        super(id);
        this.md = md;
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public void stop() {
        stop = System.currentTimeMillis();
    }

    public long delay() {
        return stop - start;
    }
    
    public Object result() {
        return result;
    }
    
    public Throwable error() {
        return error;
    }
    
    public void setResult(Object o) {
        result = o;
    }
    
    public void setError(Throwable t) {
        error = t;
    }
}
