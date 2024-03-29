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

/**
 * 
 * @author Eduardo Almeida
 * 
 * This enumeration represents the different results returned by a test. 
 * 
 */

public enum Verdicts {
	/**
	 * The test has been correctly executed.
	 */
	PASS, 
	/**
	 * The test failed.
	 */
	FAIL,
	/**
	 * The test didn't failed but we can't be sure it works.
	 */
	INCONCLUSIVE, 
	/**
	 * The test has not been executed correctly : it's not possible to determine if the test is <code>PASS</code>, <code>FAIL</code> or <code>INCONCLUSIVE</code>. 
	 */
	ERROR;
}
