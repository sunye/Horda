/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.atlanmod.commons.tester;

import org.atlanmod.commons.base.Range;

import java.lang.reflect.Method;

/**
 *
 * @author sunye
 */
public abstract class TestMethod {
    
    protected Range range;
    protected Method method;
    protected int timeout;

    public Range range() {
        return range;
    }

    public Method method() {
        return method;
    }

    protected Range newRange(String str) {
        return Range.fromString(str);
    }

    public int timeout() {
        return timeout;
    }

}
