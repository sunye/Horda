package org.kevoree.macaw.framework.scheduler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.kevoree.annotation.*;
import org.kevoree.api.service.core.script.KevScriptEngine;
import org.kevoree.library.camel.framework.AbstractKevoreeCamelComponentType;
import org.kevoree.macaw.framework.MacawMessageTypes;

/**
 * Created with IntelliJ IDEA.
 * User: duke
 * Date: 16/04/12
 * Time: 11:04
 */


@Library(name = "Freepastry")
@ComponentType
@Requires({
        @RequiredPort(name = "request", type = PortType.MESSAGE, messageType = "request")
})
@Provides({
        @ProvidedPort(name = "response", type = PortType.MESSAGE, messageType = "response"),
        @ProvidedPort(name = "query", type = PortType.MESSAGE)
})
public class CamelScheduler extends AbstractKevoreeCamelComponentType implements MacawMessageTypes {
    /*
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
    */
    @Override
    protected void buildRoutes(RouteBuilder rb) {
        rb.from("kport:response").process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                //TODO
            }
        });

        rb.from("kport:query").process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                System.out.println("input="+exchange.getIn().getBody());
                //TODO
            }
        });
    }

}
