package org.hibernate.service.spi;

import org.hibernate.service.Service;

/**
 * 根据合约初始化service
 * @param <R>
 */
public interface ServiceInitiator<R extends Service> {

    /**
     * 获取该发起者发起的服务角色。 在注册表中应该是唯一的
     * @return
     */
    public Class<R> getServiceInitated();
}
