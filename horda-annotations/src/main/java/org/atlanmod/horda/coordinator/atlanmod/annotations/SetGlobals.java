/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.atlanmod.horda.coordinator.atlanmod.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author sunye
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SetGlobals {

}

