package pt.consulting.grlp.listener;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pt.consulting.grlp.processor.GrowllerProcessor;
import pt.consulting.grlp.processor.IProcessor;
import pt.consulting.lib.config.ResourceConfig;
import pt.consulting.lib.enumeration.ILogLevel;
import pt.consulting.lib.logger.ILogStream;
import pt.consulting.lib.logger.IRequestContext;
import pt.consulting.lib.message.ByteBuffer;

/**
 * Processor Business Router. Define the {@link GrowllerProcessor} and execute
 * request message.
 *
 * @author Renan Fernandes
 * @since January / 2014
 *
 */
@WebServlet(name = "ProcessorBusinessRouter", urlPatterns = "/processor/*", loadOnStartup = 1)
public class ProcessorBusinessRouter extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Maximum request counter number
     */
    private static final int MAX_REQUEST_COUNT = Short.MAX_VALUE;

    /**
     * Application log
     */
    private ILogStream log;

    /**
     * Request id
     */
    private int requestId;

    /**
     * Transaction processor
     */
    @EJB
    private IProcessor processor;

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() throws ServletException {
	super.init();
	/*
	 * create log
	 */
	log = ResourceConfig.getInstance().createLog("business-router");
	log.info(this + ":Started!");
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
     * HttpServletRequest , javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	long timeMillis = System.currentTimeMillis();
	/*
	 * Verifica que sao enviados pelos menos 6 bytes
	 *
	 * 2 : Comprimento da mensagem
	 *
	 * 4 : Codigo de Mensagem [Ex:GP00101]
	 */
	int contentLength = req.getContentLength();
	if (contentLength < 7) {
	    log.error("contentLength: " + contentLength);
	    resp.setStatus(HttpServletResponse.SC_LENGTH_REQUIRED);
	    return;
	}
	/*
	 * request process
	 */
	ByteBuffer bb = null;
	IRequestContext ctx = null;
	try {
	    /*
	     * read input stream
	     */
	    bb = new ByteBuffer();
	    ctx = bb.getReqCtx();
	    if (++requestId > MAX_REQUEST_COUNT) {
		requestId = 1;
	    }
	    ctx.setConnID(requestId);
	    ctx.setLogStream(log);
	    ctx.setPartnerID(IRequestContext.PARTNER_PROCESSOR);
	    /*
	     *
	     */
	    bb.readFrom(req.getInputStream(), 0);
	    /*
	     * process message
	     */
	    bb = processor.exec(bb);
	    /*
	     * write output stream
	     */
	    resp.setHeader("Connection", "close");
	    resp.setContentLength(bb.getOffset() + bb.getLength());
	    bb.writeTo(resp.getOutputStream(), 0);

	} catch (Throwable tr) {
	    String msg = ctx.getLogToken("E", IRequestContext.PARTNER_PROCESSOR);
	    if (log.isFlagActive(ILogLevel.EXCEPTION)) {
		log.error(msg, tr);
	    } else {
		log.error(msg, tr.toString());
	    }
	    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	} finally {
	    if (null != ctx) {
		ctx.recycle();
	    }
	    System.out.println("PRESMON request in: " + (System.currentTimeMillis() - timeMillis) + " ms");
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
     * HttpServletRequest , javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req, resp);
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.GenericServlet#destroy()
     */
    @Override
    public void destroy() {
	log.info(this + ":Destroying...");
	super.destroy();
	log.info(this + ":Destroyed!");
    }
}
