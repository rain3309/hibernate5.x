package org.hibernate.engine.jdbc.connections.spi;

import org.hibernate.service.UnknownUnwrapTypeException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 对使用的{ MultiTenantConnectionProvider}实现基本支持 每个租户在个人场景{ ConnectionProvider}实例，
 */
public abstract class AbstractMultiTenantConnectionProvider implements MultiTenantConnectionProvider{

    protected abstract ConnectionProvider getAnyConnectionProvider();

    protected abstract ConnectionProvider selectConnectionProvider(String tenantIdentifier);

    public Connection getAnyConnection()throws SQLException{
        return getAnyConnectionProvider().getConnection();
    }

    public void releaseAnyConnection(Connection connection)throws SQLException{
        getAnyConnectionProvider().closeConnection(connection);
    }

    public Connection selectConnection(String tenantIdentifier)throws SQLException{
        return selectConnectionProvider(tenantIdentifier).getConnection();
    }

    public void releaseConnection(String tenantIdentifier,Connection connection)throws SQLException{
        selectConnectionProvider(tenantIdentifier).closeConnection(connection);
    }

    public boolean supportAggressiveRelease(){
        return getAnyConnectionProvider().supportAggressiveRelease();
    }

    public boolean isUnWrappableAs(Class unwrapType) {
        return MultiTenantConnectionProvider.class.equals(unwrapType)||
                ConnectionProvider.class.equals(unwrapType)||
                AbstractMultiTenantConnectionProvider.class.isAssignableFrom(unwrapType);
    }

    public <T> T unwrap(Class<T> unwrapType) {
        if(isUnWrappableAs(unwrapType)){
            return (T)unwrapType;
        }else{
            throw new UnknownUnwrapTypeException(unwrapType);
        }
    }
}
