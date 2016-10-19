package pt.consulting.grlp.message;

import pt.consulting.lib.date.SimpleDateUtils;
import pt.consulting.lib.message.BasicField;
import pt.consulting.lib.message.Message;
import pt.consulting.lib.message.NumericField;

public class ResponseHeader extends Message {

    private static final long serialVersionUID = 1L;

    public BasicField messageCode = add(new BasicField(4));

    public NumericField messageVersion = add(new NumericField(2));

    public NumericField growllerId = add(new NumericField(5));

    public NumericField transactionDateTime = add(new NumericField(14));

    public BasicField token = add(new BasicField(5));

    public BasicField status = add(new BasicField(1));

    /**
     * Default constructor
     *
     * @throws Exception
     */
    public void setHeader() throws Exception {
	this.transactionDateTime.set(SimpleDateUtils.getInstance(620).getDateTime());
    }

    public void setHeader(String name) throws Exception {
	setHeader();
	this.messageCode.set(name);
    }

    public void setHeader(String name, int version) throws Exception {
	setHeader(name);
	this.messageVersion.set(version);
    }

    public void setHeader(String name, int version, int growllerId) throws Exception {
	setHeader(name, version);
	this.growllerId.set(growllerId);
    }

    public void setHeader(String name, int version, int growllerId, String token) throws Exception {
	setHeader(name, version, growllerId);
	this.token.set(token);
    }

    /**
     * @return the name
     */
    public String getMessageCode() {
	return messageCode.getValue();
    }

    /**
     * @param messageCode
     *            the name to set
     */
    public void setMessageCode(String messageCode) {
	this.messageCode.set(messageCode);
    }

    /**
     * @return the version
     */
    public int getMessageVersion() {
	return messageVersion.getInt();
    }

    /**
     * @param version
     *            the version to set
     */
    public void setMessageVersion(int version) {
	this.messageVersion.set(version);
    }

    /**
     * @return the growllerId
     */
    public int getGrowllerId() {
	return growllerId.getInt();
    }

    /**
     * @param growllerId
     *            the growllerId to set
     */
    public void setGrowllerId(int growllerId) {
	this.growllerId.set(growllerId);
    }

    /**
     * @return the date
     */
    public long getTransactionDateTime() {
	return transactionDateTime.getLong();
    }

    /**
     * @param date
     *            the date to set
     */
    public void setTransactionDateTime(long dateTime) {
	this.transactionDateTime.set(dateTime);
    }

    /**
     * @return the token
     */
    public String getToken() {
	return token.getValue();
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
	this.token.set(token);
    }

    /**
     * @return the status
     */
    public int getStatus() {
	return status.getInt();
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
	this.status.set(status);
    }
}
