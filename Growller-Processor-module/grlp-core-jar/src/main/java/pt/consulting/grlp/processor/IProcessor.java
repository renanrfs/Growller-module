package pt.consulting.grlp.processor;

import javax.ejb.Local;

import pt.consulting.lib.message.ByteBuffer;

@Local
public interface IProcessor {

    public ByteBuffer exec(ByteBuffer bb) throws Exception;

}
