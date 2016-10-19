package pt.consulting.grlp.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pt.consulting.grlp.dao.entity.Growllers;
import pt.consulting.grlp.dao.entity.Sensors;
import pt.consulting.grlp.dao.entity.Tasks;

/**
 * The JPA persistence application.
 *
 * @author renan.souza
 *
 */
@Stateless
public class GrowllerServiceBean implements IService {

    private static final String unitName = "growllerUnit";

    @PersistenceContext(unitName = "growllerUnit")
    private EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see pt.consulting.grlp.dao.IService#findAllWaitingTaskByToken(java.lang.
     * String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Tasks> findAllWaitingTaskByToken(String token) throws Exception {
	Query query = em.createNamedQuery("Tasks.allWaitingByToken");
	query.setParameter(1, token);
	return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pt.consulting.grlp.dao.IService#persist(pt.consulting.grlp.dao.entity.
     * Growllers)
     */
    @Override
    public Growllers persist(Growllers growller) throws Exception {
	// growller.setCreatedAt((new Date(System.currentTimeMillis())));
	// growller.setUpdatedAt(new Date(System.currentTimeMillis()));
	em.persist(growller);
	return growller;

    }

    public Sensors persist(Sensors sensor) throws Exception {
	// growller.setCreatedAt((new Date(System.currentTimeMillis())));
	// growller.setUpdatedAt(new Date(System.currentTimeMillis()));
	em.persist(sensor);
	return sensor;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pt.consulting.grlp.dao.IService#update(pt.consulting.grlp.dao.entity.
     * Growllers)
     */
    @Override
    public Growllers update(Growllers growller) throws Exception {
	growller.setUpdatedAt(new Date(System.currentTimeMillis()));
	return em.merge(growller);
    }

    /*
     * (non-Javadoc)
     *
     * @see pt.consulting.grlp.dao.IService#findAllGrowllers()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Growllers> findAllGrowllers() throws Exception {
	Query query = em.createNamedQuery("Growllers.getAllGrowllers");
	return query.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pt.consulting.grlp.dao.IService#findGrowllerByToken(java.lang.String)
     */
    @Override
    public Growllers findGrowllerByToken(String token) throws Exception {
	Query query = em.createNamedQuery("Growllers.getByToken");
	query.setParameter(1, token);
	return (Growllers) query.getSingleResult();
    }

    /*
     * (non-Javadoc)
     *
     * @see pt.consulting.grlp.dao.IService#findGrowller(int)
     */
    @Override
    public Growllers findGrowller(int growllerId) throws Exception {
	return em.find(Growllers.class, growllerId);
    }

    /*
     * (non-Javadoc)
     *
     * @see pt.consulting.grlp.dao.IService#persistTurnLight(java.lang.String)
     */
    @Override
    public int persistTurnLight(String turnLight) throws Exception {
	// TemperatureSensor tempSensor = new TemperatureSensor(growller,
	// temperatureValue);
	// em.persist(tempSensor);
	return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see pt.consulting.grlp.dao.IService#persistTurnWater(java.lang.String)
     */
    @Override
    public int persistTurnWater(String turnWater) throws Exception {

	// TemperatureSensor tempSensor = new TemperatureSensor(growller,
	// turnWater);
	// em.persist(tempSensor);
	return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see pt.consulting.grlp.dao.IService#persistTurnFan(java.lang.String)
     */
    @Override
    public int persistTurnFan(String turnFan) throws Exception {
	// TemperatureSensor tempSensor = new TemperatureSensor(growller,
	// temperatureValue);
	// em.persist(tempSensor);
	return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pt.consulting.growller.processor.dao.IGrowllerPersistence#getUnitName()
     */
    @Override
    public String getUnitName() {
	return unitName;
    }

}
