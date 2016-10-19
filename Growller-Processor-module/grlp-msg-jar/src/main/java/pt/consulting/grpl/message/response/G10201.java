package pt.consulting.grpl.message.response;

import pt.consulting.grlp.message.ResponseHeader;
import pt.consulting.lib.message.BasicField;

public class G10201 extends ResponseHeader {

    private static final long serialVersionUID = 1L;

    public BasicField turnLight = add(new BasicField(3));

    /**
     * @return the turnLight
     */
    public String getTurnLight() {
	return turnLight.getValue();
    }

    /**
     * @param turnLight
     *            the turnLight to set
     */
    public void setTurnLight(String turnLight) {
	this.turnLight.set(turnLight);
    }
}
