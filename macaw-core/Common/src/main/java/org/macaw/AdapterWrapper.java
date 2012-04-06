/*
This file is part of PeerUnit.

PeerUnit is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PeerUnit is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PeerUnit.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.macaw;

import org.macaw.annotation.Action;
import org.macaw.annotation.Cleanup;
import org.macaw.annotation.Setup;
import org.macaw.messages.MethodCall;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sunye
 */
public class AdapterWrapper {

    private static final Logger LOG = Logger.getLogger(AdapterWrapper.class.getName());
    private Map<String, Method> actions = new TreeMap<String, Method>();
    private Object adapter;
    private Class<?> klass;
    private Method setup;
    private Method cleanup;

    public AdapterWrapper (Class<?> klass) {
        assert klass != null;

        this.klass = klass;
        this.instantiate();
    }

    /**
     * Execute the given method description
     *
     * @param mc : method description to execute
     * @throws Throwable if any exception is thrown
     */
    public Object execute(MethodCall mc) throws Throwable {
        assert actions.containsKey(mc.name()) : "Method should be registered";
        Method m = actions.get(mc.name());
        return this.invoke(m, mc.arguments());
    }

    private Object invoke(Method m, Object[] args) throws Throwable {
        assert adapter != null : "Adapter instance should not be null";

        Object result;
        try {
            result = m.invoke(adapter, args);
        } catch (IllegalAccessException e) {
            e.fillInStackTrace();
            throw e.getCause();
        } catch (InvocationTargetException e) {
            e.fillInStackTrace();
            throw e.getCause();
        }
        
        return result;
    }

    private void instantiate() {
        this.parse(klass);
        try {
            adapter = klass.newInstance();
        } catch (InstantiationException e) {
            LOG.log(Level.SEVERE, "Instantiation Exception", e);
        } catch (IllegalAccessException e) {
            LOG.log(Level.SEVERE, "Illegal Access Exception", e);
        } catch (IllegalArgumentException ex) {
            LOG.log(Level.SEVERE, "Illegal Argument Exception", ex);
        }

    }

    public void cleanup() throws Throwable {
        if (cleanup != null) {
            this.invoke(cleanup, (Object[]) null);
        }
    }

    public void setup() throws Throwable {
        if (setup != null) {
            this.invoke(setup, (Object[]) null);
        }
    }

    /**
     * Parse the test case to extract the methods to be executed
     * @param c
     */
    private void parse(Class<?> c) {

        Method[] methods = c.getMethods();
        for (Method each : methods) {
            if (each.isAnnotationPresent(Action.class)) {
                actions.put(each.getName(), each);
            } else if (each.isAnnotationPresent(Setup.class)
                    && each.getParameterTypes().length == 0) {
                setup = each;
            } else if (each.isAnnotationPresent(Cleanup.class)
                    && each.getParameterTypes().length == 0) {
                cleanup = each;
            }

        }
    }
}
