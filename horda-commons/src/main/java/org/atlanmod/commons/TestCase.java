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
package org.atlanmod.commons;

import org.atlanmod.commons.remote.Tester;

/**
 * The <i>test cases</i> interface. This interface must be implemented by the 
 * testing engineer  wants to write a <i>test case</i>, it allow to access the 
 * <i>commons</i> who will execute the  <i>test case</i>.
 *
 **/
public interface TestCase {

//	public void setTester(TesterImpl ti);


	/**
	 * For set the reference to the <i>commons</i> which  will execute the  <i>test case</i>
	 * in distributed  architecture. 
	 * 
	 * @param t the  reference of the subjacent <i>commons</i>
	 */		
	public void setTester(Tester t);
	
}
