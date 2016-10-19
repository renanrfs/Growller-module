package pt.consulting.grlp.listener;

import java.util.Iterator;
import java.util.Timer;
import java.util.Vector;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import pt.consulting.grlp.container.IContainer;
import pt.consulting.lib.config.ResourceConfig;
import pt.consulting.lib.logger.LogStream;

/**
 * 
 * @author Renan Fernandes
 */
@WebListener
public class GrowllerContextListener implements ServletContextListener {

    /**
     * LogStream event register
     */
    private LogStream log;

    /**
     * The Growller container
     */
    @EJB
    private IContainer container;

    /**
     * Group of tasks
     */
    private Vector<Timer> timerVector = new Vector<Timer>();

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
     * .ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
	try {
	    /*
	     * load properties
	     */
	    ResourceConfig resource = ResourceConfig.getInstance();
	    /*
	     * create log
	     */
	    log = resource.createLog("listener");
	    resource.printPropertiesValue(log);
	    log.info(this + ":contextInitialized:Started!");
	    /*
	     * container task (0 minutes - 1 hour)
	     */
	    Timer timer = container.initTask(0, 3600000);
	    addTimerTask(timer);

	    log.info(this + ":contextInitialized:End!");
	} catch (Throwable tw) {
	    if (null != log) {
		log.error(tw);
		log.error(this + ":contextInitialized:" + tw);
	    } else {
		tw.printStackTrace();
	    }
	    throw new RuntimeException(tw);
	}
    }

    /**
     * Schedules the specified task for repeated fixed-delay execution,
     * beginning after the specified delay.
     * 
     * @param task
     *            task to be scheduled.
     * @param delay
     *            delay in milliseconds before task is to be executed.
     * @param period
     *            time in milliseconds between successive task executions.
     */
    private void addTimerTask(Timer timer) {
	timerVector.add(timer);
	log.info("TimerTask Added: " + timer.toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
     * ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
	log.info(sce.toString());
	log.info(this + ":contextDestroyed:Start!");
	log.info(this + ": Stop Timers:Start!");
	Timer timer;
	for (Iterator<Timer> timers = timerVector.iterator(); timers.hasNext();) {
	    timer = timers.next();
	    log.info("TimerTask Cancel: " + timer.toString());
	    timer.cancel();
	    log.info("TimerTask Canceled: " + timer.toString());
	}
	log.info(this + ": Stop Timers:End!");
	log.info(this + ":contextDestroyed:End!");
	log.close();
    }
}