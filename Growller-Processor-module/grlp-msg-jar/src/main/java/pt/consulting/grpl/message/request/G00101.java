package pt.consulting.grpl.message.request;

import pt.consulting.grlp.message.RequestHeader;
import pt.consulting.lib.message.BasicField;

/**
 * Stay Alive Message
 *
 * @author Renan Fernandes
 *
 */
public class G00101 extends RequestHeader {

    private static final long serialVersionUID = 1L;

    public BasicField ipAddress = add(new BasicField(15));

    public BasicField lightSensor = add(new BasicField(3));

    public BasicField temperatureSensor = add(new BasicField(3));

    public BasicField humiditySensor = add(new BasicField(3));

    public String getIpAddress() {
	return ipAddress.getValue();
    }

    public void setIpAddress(String ipAddress) {
	this.ipAddress.set(ipAddress);
    }

    public String getLightSensor() {
	return lightSensor.getValue();
    }

    public void setLightSensor(String lightSensor) {
	this.lightSensor.set(lightSensor);
    }

    public String getTemperatureSensor() {
	return temperatureSensor.getValue();
    }

    public void setTemperatureSensor(String temperatureSensor) {
	this.temperatureSensor.set(temperatureSensor);
    }

    public String getHumiditySensor() {
	return humiditySensor.getValue();
    }

    public void setHumiditySensor(String humiditySensor) {
	this.humiditySensor.set(humiditySensor);
    }
}
