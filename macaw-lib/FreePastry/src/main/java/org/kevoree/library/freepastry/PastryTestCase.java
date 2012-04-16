package org.kevoree.library.freepastry;

import org.apache.camel.builder.RouteBuilder;
import org.kevoree.annotation.*;
import org.kevoree.api.service.core.script.KevScriptEngine;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.library.camel.framework.AbstractKevoreeCamelComponentType;

/**
 * Created with IntelliJ IDEA.
 * User: duke
 * Date: 16/04/12
 * Time: 11:04
 */


@Library(name = "Freepastry")
@ComponentType
@Requires({
        @RequiredPort(name = "dht", type = PortType.SERVICE, className = DHTNode.class)
})
public class PastryTestCase extends AbstractKevoreeCamelComponentType {

    private final int nbPeerNodes = 10;

    public void start() {
        KevScriptEngine kengine = getKevScriptEngineFactory().createKevScriptEngine();
        for(int i =0 ;i< nbPeerNodes ; i++){
            kengine.addVariable("nodeName","node"+i);
            kengine.append("addNode {nodeName} : JavaSeNode");
            kengine.append("addComponent freePastry0@{nodeName} : FreePastryNode");
        }
        kengine.interpretDeploy();
    }

    @Stop
    public void stop() {
        KevScriptEngine kengine = getKevScriptEngineFactory().createKevScriptEngine();
        for(int i =0 ;i< nbPeerNodes ; i++){
            kengine.addVariable("nodeName","node"+i);
            kengine.append("addNode {nodeName} : JavaSeNode");
        }
        kengine.interpretDeploy();
    }

    @Override
    protected void buildRoutes(RouteBuilder rb) {

    }

}
