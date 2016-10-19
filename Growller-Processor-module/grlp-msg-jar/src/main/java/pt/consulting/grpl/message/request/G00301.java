package pt.consulting.grpl.message.request;

import pt.consulting.grlp.message.RequestHeader;
import pt.consulting.lib.message.BasicField;

public class G00301 extends RequestHeader {

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
