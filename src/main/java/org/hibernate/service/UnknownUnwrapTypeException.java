package org.hibernate.service;

import org.hibernate.HibernateException;

public class UnknownUnwrapTypeException extends HibernateException {

    public UnknownUnwrapTypeException(Class unwrapType){
        super("不能转换为该类型： "+unwrapType.getName());
    }

    public UnknownUnwrapTypeException(Class unwrapType,Throwable root){
        this(unwrapType);
        super.initCause(root);
    }

}
