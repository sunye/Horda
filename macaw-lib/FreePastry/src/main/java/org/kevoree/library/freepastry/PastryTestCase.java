package org.kevoree.library.freepastry;

import org.kevoree.ContainerRoot;
import org.kevoree.api.service.core.script.KevScriptEngine;
import org.kevoree.macaw.framework.scheduler.Scheduler;
import org.kevoree.macaw.framework.testcase.TestCase;
import org.kevoree.macaw.framework.testcase.TestResult;

/**
 * Created with IntelliJ IDEA.
 * User: duke
 * Date: 16/04/12
 * Time: 11:04
 */



public class PastryTestCase implements TestCase {

    private final int nbPeerNodes = 10;

    private Scheduler scheduler = null;

    @Override
    public void setParentScheduler(Scheduler sch) {
        scheduler = sch;
    }

    @Override
    public ContainerRoot buildTestModel(KevScriptEngine offlineEngine) {
        for(int i =0 ;i< nbPeerNodes ; i++){
            offlineEngine.addVariable("nodeName","node"+i);
            offlineEngine.append("addNode {nodeName} : JavaSeNode");
            offlineEngine.append("addComponent freePastry0@{nodeName} : FreePastryNode");
        }
        return offlineEngine.interpret();
    }

    @Override
    public TestResult execute() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
