package org.hibernate.service;

/**
 * service 注册处
 */
public interface ServiceRegistry {

    /**
     * 检索此注册表的父注册表
     * @return
     */
    ServiceRegistry getParentServiceRegistry();

    /**
     *  根据角色检索service 如果该service没有找到 但是注册了{ org.hibernate.service.spi.ServiceInitiator} 这个service角色
     *  这个service将会被初始化和返回
     *
     *  注意： 我们不能返回这个service{@code <R extends Service<T>>} 因为这个service可能来自他的父亲
     * @param serviceRole
     * @param <R>
     * @return
     */
    <R extends Service> R getService(Class<R> serviceRole);

    default <R extends Service> R requireService(Class<R> serviceRole){
        final R service = getService(serviceRole);
        if(service == null){
            throw new NullServiceException(serviceRole);
        }
        return service;
    }
}
