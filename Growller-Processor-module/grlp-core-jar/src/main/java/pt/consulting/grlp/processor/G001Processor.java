package pt.consulting.grlp.processor;

import java.util.List;

import pt.consulting.grlp.dao.IService;
import pt.consulting.grlp.dao.entity.Growllers;
import pt.consulting.grlp.dao.entity.Sensors;
import pt.consulting.grlp.dao.entity.Tasks;
import pt.consulting.grpl.message.request.G00101;
import pt.consulting.grpl.message.response.G10101;
import pt.consulting.lib.logger.ILogStream;
import pt.consulting.lib.message.ByteBuffer;

/**
 * Processor class for Update Info and Notify Sensor´s: <br>
 * <b>Sensor's:</b>
 * <ul>
 * <li>LightSensor</li>
 * <li>HumiditySensor</li>
 * <li>TemperatureSensor</li>
 * </ul>
 *
 * @author Renan Souza
 */
public class G001Processor implements IMessageProcessor {

    /*
     * (non-Javadoc)
     *
     * @see
     * pt.consulting.grlp.processor.IMessageProcessor#exec(pt.consulting.grlp.
     * dao.IService, pt.consulting.grlp.dao.entity.Growllers,
     * pt.consulting.lib.message.ByteBuffer)
     */
    @Override
    public ByteBuffer exec(IService service, Growllers growller, ByteBuffer bb) throws Exception {

	ILogStream log = null;
	try {
	    log = bb.getReqCtx().getLogStream();
	    /*
	     * prepare request
	     */
	    G00101 g00101 = new G00101();
	    g00101.setBuffer(bb.getBuffer(), bb.getOffset());
	    log.info(g00101);
	    /*
	     * growller values
	     */
	    growller.setIpAddress(g00101.getIpAddress());
	    growller.setVersion("1.00.00");
	    /*
	     * sensor values
	     */

	    Sensors sensor = new Sensors();
	    sensor.setTemperature(g00101.getTemperatureSensor());
	    sensor.setHumidity(g00101.getHumiditySensor());
	    sensor.setLight(g00101.getLightSensor());
	    sensor.setGrowller(growller);

	    service.persist(sensor);
	    service.update(growller);
	    /*
	     * check growller task´s
	     */
	    int status = existTaskGrowller(service, growller.getToken());
	    /*
	     * prepare response
	     */
	    G10101 g10101 = new G10101();
	    g10101.setBuffer();
	    g10101.setHeader(g10101.getName(), 1, growller.getId(), growller.getToken());
	    g10101.setStatus(status);

	    bb.setBytes(g10101.getBuffer(), g10101.getOffset(), g10101.getLength());
	    log.info(g10101);

	} catch (Exception e) {
	    if (null != log) {
		log.error(e);
	    }
	    throw new Exception(e);
	}

	return bb;
    }

    /**
     * Verify {@link Tasks} by {@link Growllers} and return:
     * <ul>
     * <li>0 - Don´t have task;</li>
     * <li>1 - Have a task;</li>
     * </ul>
     *
     * @param persistence
     *            The PRESMON persistence
     * @param token
     *            Growller identify.
     * @return Status
     *
     * @throws Exception
     */
    private int existTaskGrowller(IService persistence, String token) throws Exception {
	List<Tasks> tasks = persistence.findAllWaitingTaskByToken(token);
	return (tasks.isEmpty() ? 0 : 1);
    }
}
