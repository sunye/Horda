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
package org.atlanmod.commons.util;

/**
 *
 * @author sunye
 */
public interface HNode<K,V> {

    /**
     *
     * @return the value stored in this HNode.
     */
    V value();

    /**
     *
     * @return the key stored in this HNode.
     */
    K key();

    /**
     * 
     * @return The children of this node.
     */
    HNode<K,V>[] children();

    boolean isLeaf();
}
