package pt.consulting.grlp.container;

import java.util.Timer;

import javax.ejb.Local;

import pt.consulting.grlp.dao.entity.Growllers;

@Local
public interface IContainer {

    public Timer initTask(long delay, long period);

    /**
     * @param growllerId
     * @return
     */
    public String existGrowller(int growllerId);

    /**
     * Add new {@link Growllers} in growller map structure.
     *
     * @param growller
     *            New {@link Growllers} to add.
     */
    public void addGrowller(Growllers growller);
}
