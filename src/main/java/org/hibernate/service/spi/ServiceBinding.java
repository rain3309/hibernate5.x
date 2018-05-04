package org.hibernate.service.spi;

import org.hibernate.service.Service;

/**
 * 建模一个特定服务的绑定
 * @param <R>
 */
public class ServiceBinding<R extends Service> {

    public static interface ServiceLifecycleOwner{

        public <R extends Service> R initiateService(ServiceInitiator<R> serviceInitiator);
        public <R extends Service> void configureService(ServiceBinding<R> binding);
        public <R extends Service> void injectDenpendencies(ServiceBinding<R> binding);
        public <R extends Service> void startService(ServiceBinding<R> binding);
        public <R extends Service> void stopService(ServiceBinding<R> binding);
    }

    private final ServiceLifecycleOwner lifecycleOwner;
    private final Class<R> serviceRole;
    private final ServiceInitiator<R> serviceInitiator;
    private volatile R service;

    public ServiceBinding(ServiceLifecycleOwner lifecycleOwner,Class<R> serviceRole,R service){
        this.lifecycleOwner = lifecycleOwner;
        this.serviceRole = serviceRole;
        this.serviceInitiator = null;
        this.service = service;
    }

    public ServiceBinding(ServiceLifecycleOwner lifecycleOwner,ServiceInitiator serviceInitiator){
        this.lifecycleOwner = lifecycleOwner;
        this.serviceRole = serviceInitiator.getServiceInitated();
        this.serviceInitiator = serviceInitiator;
    }

    public ServiceLifecycleOwner getLifecycleOwner() {
        return lifecycleOwner;
    }

    public Class<R> getServiceRole() {
        return serviceRole;
    }

    public ServiceInitiator<R> getServiceInitiator() {
        return serviceInitiator;
    }

    public R getService() {
        return service;
    }

    public void setService(R service) {
        if(service != null){
            System.out.println("重载已经存在的service bing"+serviceRole.getName());
        }
        this.service = service;
    }
}
