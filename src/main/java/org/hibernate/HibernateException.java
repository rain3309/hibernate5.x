package org.hibernate;

public class HibernateException extends RuntimeException/*extends PersistenceException*/{

    public HibernateException(String message){
        super(message);
    }

    public HibernateException(Throwable cause){
        super(cause);
    }

    public HibernateException(String message,Throwable cause){
        super(message, cause);
    }
}
