package pt.consulting.grpl.message.request;

import pt.consulting.grlp.message.RequestHeader;
import pt.consulting.lib.message.BasicField;

public class G00201 extends RequestHeader {

    private static final long serialVersionUID = 1L;

    public BasicField turnWater = add(new BasicField(3));

    /**
     * @return the turnWater
     */
    public String getTurnWater() {
	return turnWater.getValue();
    }

    /**
     * @param turnWater
     *            the turnWater to set
     */
    public void setTurnWater(String turnWater) {
	this.turnWater.set(turnWater);
    }

}
