package org.hibernate.engine.jdbc.connections.spi;

import org.hibernate.service.UnknownUnwrapTypeException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * MultiTenantConnectionProvider 实现类
 */
public abstract class AbstractDataSourceBasedMultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider{

    protected abstract DataSource selectAnyDataSource();

    protected abstract DataSource selectDataSource(String tenantIdentifier);

    @Override
    public Connection getAnyConnection()throws SQLException {
        return selectAnyDataSource().getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection)throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier)throws SQLException {
        return selectDataSource(tenantIdentifier).getConnection();
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection)throws SQLException {
        connection.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return true;
    }

    @Override
    public boolean isUnWrappableAs(Class unwrapType) {
        return MultiTenantConnectionProvider.class.equals(unwrapType) || AbstractDataSourceBasedMultiTenantConnectionProviderImpl.class.isAssignableFrom(unwrapType);
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        if(isUnWrappableAs(unwrapType)){
            return (T) this;
        }else{
            throw new UnknownUnwrapTypeException(unwrapType);
        }
    }
}
