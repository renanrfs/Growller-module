package pt.consulting.grlp.container;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.EJB;
import javax.ejb.Singleton;

import pt.consulting.grlp.dao.IService;
import pt.consulting.grlp.dao.entity.Growllers;
import pt.consulting.lib.config.ResourceConfig;
import pt.consulting.lib.logger.LogStream;

/**
 * The Growller container application. Define the maintenance task´s:
 * <ul>
 * <li>loadGrowllerMap - Load the {@link Growllers} map structure;</li>
 * </ul>
 *
 * @author Renan Fernandes
 * @since March / 2014
 *
 */
@Singleton
public class GrowllerContainer extends TimerTask implements IContainer {

    /**
     * LogStream
     */
    private LogStream log;

    /**
     * The Growller persistence
     */
    @EJB
    private IService service;

    /**
     * Structure of the Growller map:
     */
    private HashMap<Integer, String> growllerMap = new HashMap<Integer, String>();

    public GrowllerContainer() {
	System.out.println("RS - create: " + this);
    }

    /*
     * (non-Javadoc)
     *
     * @see pt.consulting.grlp.container.IContainer#initTask(long, long)
     */
    @Override
    public Timer initTask(long delay, long period) {
	log = ResourceConfig.getInstance().createLog("container");
	log.info(this + ":Persistence Unit Name: " + service.getUnitName());
	/*
	 * load growller map
	 */
	loadGrowllerMap();

	return schedule(delay, period);
    }

    /**
     * Schedules the specified task for repeated fixed-delay execution,
     * beginning after the specified delay.
     *
     * @param delay
     *            delay in milliseconds before task is to be executed.
     * @param period
     *            time in milliseconds between successive task executions.
     */
    public Timer schedule(long delay, long period) {
	Timer timer = new Timer(true);
	timer.schedule(this, delay, period);
	return timer;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.TimerTask#run()
     */
    @Override
    public void run() {
	log.info(this + ":Start");
	try {
	    /*
	     * ALERT: new software version!!
	     */
	    log.warning("Need implementation for New Software Update!");

	} catch (Exception e) {
	    log.error(e);
	    throw new RuntimeException(e);
	} finally {
	    log.info(this + ":Stop");
	}
    }

    /**
     * Task to load the {@link Growllers} map structure.
     *
     * @throws Exception
     */
    private void loadGrowllerMap() {
	List<Growllers> growllerList;
	log.info(this + ":loadGrowllerMap:Init");
	try {
	    growllerList = service.findAllGrowllers();
	    if (null != growllerList) {
		int id;
		String token;
		synchronized (growllerMap) {
		    growllerMap.clear();
		    for (Growllers growller : growllerList) {
			id = growller.getId();
			token = growller.getToken();
			growllerMap.put(id, token);
			log.info("Loaded:{" + id + ":" + token + "}");
		    }
		}
	    }
	} catch (Exception e) {
	    throw new RuntimeException("Load Growller Map fail: ", e);
	}
	log.info(this + ":loadGrowllerMap:End");
    }

    /*
     * (non-Javadoc)
     *
     * @see pt.consulting.grlp.container.IContainer#existGrowller(int)
     */
    @Override
    public String existGrowller(int growllerId) {
	String version = growllerMap.get(growllerId);
	if (null == version) {
	    log.warning("Growller dont exist: " + growllerId);
	}
	return version;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pt.consulting.grlp.container.IContainer#addGrowller(pt.consulting.grlp
     * .dao.entity.Growller)
     */
    @Override
    public void addGrowller(Growllers growller) {
	int id = growller.getId();
	String token = growller.getToken();
	synchronized (growllerMap) {
	    growllerMap.put(id, token);
	    log.info("addGrowller:{" + id + ":" + token + "}");
	}
    }
}
