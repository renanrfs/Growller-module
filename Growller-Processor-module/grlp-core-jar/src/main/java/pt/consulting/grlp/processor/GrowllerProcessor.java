package pt.consulting.grlp.processor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pt.consulting.grlp.container.IContainer;
import pt.consulting.grlp.dao.IService;
import pt.consulting.grlp.dao.entity.Growllers;
import pt.consulting.grlp.message.Messages;
import pt.consulting.grlp.message.RequestHeader;
import pt.consulting.lib.logger.ILogStream;
import pt.consulting.lib.message.ByteBuffer;

/**
 *
 *
 * @author Renan Fernandes
 * @since January / 2014
 *
 */
@Stateless
public class GrowllerProcessor implements IProcessor {

    /**
     * Database persistence
     */
    @EJB
    private IService service;

    /**
     * Singleton container
     */
    @EJB
    private IContainer container;

    /**
     * Services that needs to be executed after dependency injection is done to
     * perform any initialization.
     */
    @PostConstruct
    public void postConstruct() {
	System.out.println("RS - PostConstruct....");
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pt.consulting.grlp.processor.IProcessor#exec(pt.consulting.lib.message
     * .ByteBuffer)
     */
    @Override
    public ByteBuffer exec(ByteBuffer bb) throws Exception {
	/*
	 * discovery message
	 */
	Class<?> messageClass = Messages.discoveryMessage(bb);
	IMessageProcessor messageProcessor = (IMessageProcessor) messageClass.newInstance();
	/*
	 * validate growller
	 */
	Growllers growller = validateHeader(bb);
	/*
	 * execute message processor
	 */
	bb = messageProcessor.exec(service, growller, bb);

	return bb;
    }

    public Growllers validateHeader(ByteBuffer bb) throws Exception {
	ILogStream log = bb.getReqCtx().getLogStream();
	/*
	 * header constructor
	 */
	RequestHeader header = new RequestHeader();
	header.setBuffer(bb.getBuffer(), bb.getOffset());
	log.info(header);

	/*
	 * prepare fields
	 */
	int growllerId = header.getGrowllerId();

	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	Date transactionDateTime = formatter.parse(header.getTransactionDateTime());

	String lastMessage = (header.getMessageCode() + header.getMessageVersion());
	/*
	 * exist growller?
	 */
	Growllers growller;
	String token = container.existGrowller(growllerId);
	if (null == token) {
	    growller = persist(header.getToken(), growllerId, lastMessage, transactionDateTime);

	    container.addGrowller(growller);
	} else {
	    growller = update(growllerId, lastMessage, transactionDateTime);
	}
	return growller;
    }

    /**
     * @param token
     * @param growllerId
     * @param lastMessage
     * @param transactionDateTime
     * @return
     * @throws Exception
     */
    private Growllers persist(String token, int growllerId, String lastMessage, Date transactionDateTime)
	    throws Exception {

	Growllers growller = new Growllers();
	growller.setId(growllerId);
	growller.setToken(token);
	growller.setLastMessage(lastMessage);

	growller.setTransactionDate(transactionDateTime);

	return service.persist(growller);
    }

    private Growllers update(int growllerId, String lastMessage, Date transactionDateTime) throws Exception {

	Growllers growller = service.findGrowller(growllerId);
	growller.setLastMessage(lastMessage);
	growller.setTransactionDate(transactionDateTime);

	return service.update(growller);
    }

    /**
     * The instance is in the process of being removed by the container.
     */
    @PreDestroy
    public void preDestroy() {
	System.out.println("RS - preDestroy....");
    }
}
