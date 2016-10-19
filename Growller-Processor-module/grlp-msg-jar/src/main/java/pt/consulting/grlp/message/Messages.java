package pt.consulting.grlp.message;

import java.util.HashMap;

import pt.consulting.lib.logger.IRequestContext;
import pt.consulting.lib.message.ByteBuffer;

public class Messages {

    /**
     * End of value to message processor class.
     */
    private static final String PROCESSOR = "Processor";

    /**
     * Value of message processor package.
     */
    private static final String PROCESSOR_PACKAGE = "pt.consulting.grlp.processor.";

    /**
     * Structure of the message processor map:
     * <ul>
     * <li>key - Fully qualified name of the class;</li>
     * <li>value - Object of the Message Processor ({@link IMessageProcessor});</li>
     * </ul>
     * <br/>
     */
    private static HashMap<String, Class<?>> messageMap = new HashMap<String, Class<?>>();

    /**
     * @param buffer
     * @return
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Class<?> discoveryMessage(final ByteBuffer bb)
	    throws Exception {
	/*
	 * discovery the class name
	 */
	Class<?> messageProcessor;
	String fullyQualifiedName = null;
	try {

	    byte[] buffer = bb.getBuffer();
	    int offset = bb.getOffset();
	    StringBuffer classNameBuffer = new StringBuffer();
	    for (int i = 0; i < 4; i++) {
		classNameBuffer.append((char) buffer[offset++]);
	    }
	    String className = classNameBuffer.toString();
	    /*
	     * 
	     */
	    IRequestContext ctx = bb.getReqCtx();
	    ctx.setPartnerID(IRequestContext.PARTNER_PROCESSOR);
	    ctx.setReqMsgCode(className);
	    /*
	     * jump Message Version (2 bytes)
	     */
	    offset += 2;
	    int result = 0;
	    for (int i = 5; i-- > 0;) {
		result = (result * 10) + (buffer[offset++] - '0');
	    }
	    ctx.setReqTrmID(result);
	    /*
	     * prepare fully qualified name of the class
	     */
	    StringBuffer messageProcessorBuffer = new StringBuffer();
	    messageProcessorBuffer.append(PROCESSOR_PACKAGE);
	    messageProcessorBuffer.append(classNameBuffer);
	    messageProcessorBuffer.append(PROCESSOR);
	    fullyQualifiedName = messageProcessorBuffer.toString();
	    /*
	     * new message processor instance
	     */
	    messageProcessor = Class.forName(fullyQualifiedName);

	} catch (Exception e) {
	    throw new RuntimeException("### Message Processor not found! ###",
		    e);
	}
	return messageProcessor;
    }
}
