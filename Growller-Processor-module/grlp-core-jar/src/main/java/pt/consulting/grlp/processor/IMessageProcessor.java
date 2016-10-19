package pt.consulting.grlp.processor;

import pt.consulting.grlp.dao.IService;
import pt.consulting.grlp.dao.entity.Growllers;
import pt.consulting.lib.message.ByteBuffer;

public interface IMessageProcessor {

    public ByteBuffer exec(IService service, Growllers growller, ByteBuffer bb) throws Exception;
}
