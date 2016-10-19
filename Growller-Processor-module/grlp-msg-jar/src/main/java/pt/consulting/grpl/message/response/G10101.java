package pt.consulting.grpl.message.response;

import pt.consulting.grlp.message.ResponseHeader;
import pt.consulting.lib.message.BasicField;

public class G10101 extends ResponseHeader {

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
