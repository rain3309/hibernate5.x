package org.hibernate.service;

import org.hibernate.HibernateException;

public class NullServiceException extends HibernateException {

    public final Class serviceRole;

    public NullServiceException(Class serviceRole){
        super("不知道这个service请求: "+serviceRole);
        this.serviceRole = serviceRole;
    }
    public Class getServiceRole(){
        return serviceRole;
    }
}
