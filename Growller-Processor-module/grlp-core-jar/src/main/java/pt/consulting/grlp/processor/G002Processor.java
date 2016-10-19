package pt.consulting.grlp.processor;

import pt.consulting.grlp.dao.IService;
import pt.consulting.grlp.dao.entity.Growllers;
import pt.consulting.grpl.message.request.G00201;
import pt.consulting.grpl.message.response.G10201;
import pt.consulting.lib.logger.ILogStream;
import pt.consulting.lib.message.ByteBuffer;

/**
 *
 * @author Renan Fernandes
 *
 */
public class G002Processor implements IMessageProcessor {

    /*
     * (non-Javadoc)
     *
     * @see
     * pt.consulting.growller.processor.IMessageProcessor#exec(pt.consulting
     * .growller.processor.dao.IGrowllerPersistence,
     * pt.consulting.library.core.message.ByteBuffer)
     */
    @Override
    public ByteBuffer exec(IService persistence, Growllers growller, ByteBuffer bb) throws Exception {

	ILogStream log = bb.getReqCtx().getLogStream();
	/*
	 * preenche GP00201
	 */
	G00201 g00201 = new G00201();
	g00201.setBuffer(bb.getBuffer(), bb.getOffset());
	log.info(g00201);
	/*
	 * register Turn Water
	 */
	int status = persistence.persistTurnWater(g00201.getTurnWater());
	/*
	 * prepare GP10201
	 */
	G10201 g10201 = new G10201();
	g10201.setBuffer();
	g10201.setHeader(g10201.getName(), 1, g00201.getGrowllerId(), g00201.getToken());
	g10201.setStatus(status);

	bb.setBytes(g10201.getBuffer(), g10201.getOffset(), g10201.getLength());
	log.info(g10201);

	return bb;
    }
}
