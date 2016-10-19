package pt.consulting.grlp.processor;

import pt.consulting.grlp.dao.IService;
import pt.consulting.grlp.dao.entity.Growllers;
import pt.consulting.grpl.message.request.G00301;
import pt.consulting.grpl.message.response.G10301;
import pt.consulting.lib.logger.ILogStream;
import pt.consulting.lib.message.ByteBuffer;

public class G003Processor implements IMessageProcessor {

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
	 * preenche GP00301
	 */
	G00301 g00301 = new G00301();
	g00301.setBuffer(bb.getBuffer(), bb.getOffset());
	log.info(g00301);
	/*
	 * register Turn Light
	 */
	int status = persistence.persistTurnLight(g00301.getTurnLight());
	/*
	 * prepare GP10301
	 */
	G10301 g10301 = new G10301();
	g10301.setBuffer();
	g10301.setHeader(g10301.getName(), 1, g00301.getGrowllerId(), g00301.getToken());
	g10301.setStatus(status);

	bb.setBytes(g10301.getBuffer(), g10301.getOffset(), g10301.getLength());
	log.info(g10301);

	return bb;
    }

}
