/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.atlanmod.commons.tester;

import org.atlanmod.horda.coordinator.atlanmod.annotations.BeforeClass;

import java.lang.reflect.Method;

/**
 *
 * @author sunye
 */
public class BeforeClassMethod extends TestMethod {
    
    public  BeforeClassMethod(Method m) {
        BeforeClass ac = m.getAnnotation(BeforeClass.class);
        timeout = ac.timeout();
        method = m;
        range = this.newRange(ac.range());
    }
}
