package pt.consulting.grlp.message;

import pt.consulting.lib.date.SimpleDateUtils;
import pt.consulting.lib.message.BasicField;
import pt.consulting.lib.message.Message;
import pt.consulting.lib.message.NumericField;

public class RequestHeader extends Message {

    private static final long serialVersionUID = 1L;

    public BasicField messageCode = add(new BasicField(4));

    public NumericField messageVersion = add(new NumericField(2));

    public NumericField growllerId = add(new NumericField(5));

    public NumericField transactionDateTime = add(new NumericField(14));

    public BasicField token = add(new BasicField(5));

    /**
     * Default constructor
     */
    public void setHeader() {
	this.transactionDateTime.set(SimpleDateUtils.getInstance(620).getDateTime());
    }

    public void setHeader(String name) {
	setHeader();
	this.messageCode.set(name);
    }

    public void setHeader(String name, int version) {
	setHeader(name);
	this.messageVersion.set(version);
    }

    public void setHeader(String name, int version, int growllerId) {
	setHeader(name, version);
	this.growllerId.set(growllerId);
    }

    public void setHeader(String name, int version, int growllerId, String token) {
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
     * @param name
     *            the name to set
     */
    public void setMessageCode(String messageCode) {
	this.messageCode.set(messageCode);
    }

    /**
     * @return the version
     */
    public String getMessageVersion() {
	return messageVersion.getValue();
    }

    /**
     * @param version
     *            the version to set
     */
    public void setMessageVersion(String version) {
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
    public String getTransactionDateTime() {
	return transactionDateTime.getValue();
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

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Header [name=" + getName() + ", version=" + messageVersion.getValue() + ", growllerId="
		+ getGrowllerId() + ", date=" + getTransactionDateTime() + " token=" + getToken() + "]\n";
    }
}
