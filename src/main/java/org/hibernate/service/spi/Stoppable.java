package org.hibernate.service.spi;

/**
 * 生命周期中的服务，希望在停止时得到通知
 *
 */
public interface Stoppable {

    /**
     * 停止阶段通知
     */
    public void stop();
}
