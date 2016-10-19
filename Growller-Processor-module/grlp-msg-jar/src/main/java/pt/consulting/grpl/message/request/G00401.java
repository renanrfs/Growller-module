package pt.consulting.grpl.message.request;

import pt.consulting.grlp.message.RequestHeader;
import pt.consulting.lib.message.BasicField;

public class G00401 extends RequestHeader {

    private static final long serialVersionUID = 1L;

    public BasicField turnFan = add(new BasicField(3));

    /**
     * @return the turnFan
     */
    public String getTurnFan() {
	return turnFan.getValue();
    }

    /**
     * @param turnFan
     *            the turnFan to set
     */
    public void setTurnFan(String turnFan) {
	this.turnFan.set(turnFan);
    }

}
