/*
This file is part of Horda.

Horda is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Horda is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Horda.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.atlanmod.commons.coordinator;

import org.atlanmod.commons.common.MethodDescription;
import org.atlanmod.commons.remote.Tester;

import java.io.Serializable;
import java.util.Collection;

public class TesterRegistration implements Serializable {

    private final Tester tester;
    private final Collection<MethodDescription> methods;

    public TesterRegistration(Tester t, Collection<MethodDescription> coll) {
        tester = t;
        methods = coll;
    }

    public Tester tester() {
        return tester;
    }

    public Collection<MethodDescription> methods() {
        return methods;
    }
}
