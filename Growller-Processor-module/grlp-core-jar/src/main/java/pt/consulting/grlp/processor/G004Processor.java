package pt.consulting.grlp.processor;

import pt.consulting.grlp.dao.IService;
import pt.consulting.grlp.dao.entity.Growllers;
import pt.consulting.grpl.message.request.G00401;
import pt.consulting.grpl.message.response.G10401;
import pt.consulting.lib.logger.ILogStream;
import pt.consulting.lib.message.ByteBuffer;

public class G004Processor implements IMessageProcessor {

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
	 * preenche GP00401
	 */
	G00401 g00401 = new G00401();
	g00401.setBuffer(bb.getBuffer(), bb.getOffset());
	log.info(g00401);
	/*
	 * register Turn Light
	 */
	int status = persistence.persistTurnFan(g00401.getTurnFan());
	/*
	 * prepare GP10401
	 */
	G10401 g10401 = new G10401();
	g10401.setBuffer();
	g10401.setHeader(g10401.getName(), 1, g00401.getGrowllerId(), g00401.getToken());
	g10401.setStatus(status);

	bb.setBytes(g10401.getBuffer(), g10401.getOffset(), g10401.getLength());
	log.info(g10401);

	return bb;
    }

}
