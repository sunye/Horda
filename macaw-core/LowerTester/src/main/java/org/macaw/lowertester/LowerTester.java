package org.macaw.lowertester;

import org.kevoree.annotation.*;
import org.kevoree.framework.message.StdKevoreeMessage;
import org.kevoree.macaw.framework.AbstractMacawLowerTester;
import org.macaw.adapter.AdapterWrapper;
import org.macaw.adapter.Executor;
import org.macaw.messages.MessageFactory;
import org.macaw.messages.MethodCall;
import org.macaw.messages.MethodResult;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author sunye
 */
@Library(name = "Macaw")
@ComponentType
@Provides({
		@ProvidedPort(name = "Executor", type = PortType.MESSAGE, className = Executor.class)
})
public class LowerTester extends AbstractMacawLowerTester {

	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(
			LowerTester.class.getName());
	/**
	 * Stores arriving method calls.
	 */
	private final BlockingQueue<MethodCall> calls =
			new ArrayBlockingQueue<MethodCall>(10);
	/**
	 * Stores method execution results.
	 */
	private final BlockingQueue<MethodResult> results =
			new ArrayBlockingQueue<MethodResult>(10);
	/*
		 * Adapter
		 */
	private AdapterWrapper adapter;
	/**
	 * Main thread.
	 */
	private Thread testerThread = new Thread(new ActionExecutionThread());
	/**
	 * Thread used to invoke @Action methods
	 */
	private Thread invocationThread;

	@Start
	public void start () throws Throwable {
		adapter.setup();
		testerThread.start();
	}

	@Stop
	public void stop () throws Throwable {
		testerThread.interrupt();
		adapter.cleanup();
	}

	@Update
	public void update () {
	}

	@Port(name = "Executor", method = "execute")
	public void execute (MethodCall mc) {
		calls.offer(mc);
	}

	@Override
	protected void executeRequest (StdKevoreeMessage message) {
		// build MethodCall according to Kevoree message
		int id = (Integer)message.getValue("id").get();
		String test = message.getValue("tests").get().toString();
		Serializable[] args = (Serializable[]) message.getValue("tests").get();
		int timeout = (Integer)message.getValue("timeout").get();
		MethodCall mc = MessageFactory.newMethodCall(test, args, timeout);
		calls.offer(mc);
	}

	/**
	 * Main thread execution.
	 * Waits for action availability and executes actions.
	 */
	private void execution () {
		try {
			MethodCall mc = calls.take();
			invocationThread = new Thread(new InvokerThread(mc));
			invocationThread.start();
			if (mc.timeout() > 0) {
				invocationThread.join(mc.timeout());
				if (invocationThread.isAlive()) {
					invocationThread.interrupt();
				}
			}
		} catch (InterruptedException ex) {
			LOG.log(Level.SEVERE, "ActionExecutionThread interrupted exception", ex);
		}
	}


	/**
	 * Invokes a method on the adapter.
	 *
	 * @param mc the method that is invoked
	 */
	private void invoke (MethodCall md) {
		assert adapter != null : "Null adapter";

		MethodResult result = MessageFactory.newMethodResult(md);

		try {
			result.start();
			result.setResult(adapter.execute(md));
			if (Thread.interrupted()) {
				LOG.finest("Thread was interrupted.");
			}
		} catch (Throwable e) {
			StringWriter writer = new StringWriter();
			PrintWriter pw = new PrintWriter(writer);
			e.printStackTrace(pw);
			pw.flush();
			writer.flush();
			LOG.log(Level.WARNING, writer.toString());
			result.setError(e);
		} finally {
			result.stop();
			results.offer(result);
		}
	}

	private class InvokerThread implements Runnable {

		MethodCall mc;

		public InvokerThread (MethodCall md) {
			this.mc = md;
		}

		public void run () {
			LOG.entering("InvokerThread", "run()");
			invoke(mc);
		}
	}

	private class ActionExecutionThread implements Runnable {
		public void run () {
			LOG.entering("ActionExecutionThread", "run()");
			execution();
		}
	}
}
