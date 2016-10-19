package pt.consulting.grlp.dao;

import java.util.List;

import javax.ejb.Local;

import pt.consulting.grlp.dao.entity.Growllers;
import pt.consulting.grlp.dao.entity.Sensors;
import pt.consulting.grlp.dao.entity.Tasks;

/**
 * @author renan.souza
 *
 */
@Local
public interface IService {

    public Growllers findGrowller(int growllerId) throws Exception;

    public List<Growllers> findAllGrowllers() throws Exception;

    public Growllers findGrowllerByToken(String token) throws Exception;

    public Growllers persist(Growllers growller) throws Exception;

    public Growllers update(Growllers growller) throws Exception;

    public List<Tasks> findAllWaitingTaskByToken(String token) throws Exception;

    /**
     * @param turnLight
     * @return
     * @throws Exception
     */
    public int persistTurnLight(String turnLight) throws Exception;

    /**
     * @param turnWater
     * @return
     * @throws Exception
     */
    public int persistTurnWater(String turnWater) throws Exception;

    /**
     * @param turnFan
     * @return
     * @throws Exception
     */
    public int persistTurnFan(String turnFan) throws Exception;

    public Sensors persist(Sensors sensor) throws Exception;

    public String getUnitName();

}
