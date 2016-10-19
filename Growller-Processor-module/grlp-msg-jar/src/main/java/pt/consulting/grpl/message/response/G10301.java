package pt.consulting.grpl.message.response;

import pt.consulting.grlp.message.ResponseHeader;
import pt.consulting.lib.message.BasicField;

public class G10301 extends ResponseHeader {

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
