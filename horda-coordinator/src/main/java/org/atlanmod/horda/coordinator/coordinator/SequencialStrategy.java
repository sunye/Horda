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
package org.atlanmod.horda.coordinator.coordinator;

import org.atlanmod.commons.common.MethodDescription;

import java.util.logging.Logger;

    /**
     *
     * @author sunye
     */
    class SequencialStrategy implements CoordinationStrategy {

        private static final Logger LOG = Logger.getLogger(SequencialStrategy.class.getName());
        private TesterSet testers;

        public void init(TesterSet ts) {
            testers = ts;
        }

        /**
         * Sequencial execution of test steps.
         *
         * @param schedule
         * @throws InterruptedException
         */
        public void testcaseExecution() throws InterruptedException {
            LOG.entering("SequencialStrategy", "testCaseExecution()");

            for (MethodDescription each : testers.getSchedule().methods()) {
                    testers.execute(each);
            }
        }
    }
