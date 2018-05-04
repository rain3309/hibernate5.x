package org.hibernate.engine.jdbc.connections.spi;

import org.hibernate.service.Service;
import org.hibernate.service.spi.Wrapped;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 当应用程序需要使用承租人的场景时，支持多租户连接时连接提供者。
 *
 */
public interface MultiTenantConnectionProvider extends Service,Wrapped {

    /**
     * 在我们没有租户ID的情况下（例如启动处理），允许访问底层数据库的数据库元数据。
     *
     * @return
     * @throws SQLException
     */
    public Connection getAnyConnection() throws SQLException;

    /**
     *
     * 释放在getAnyConnection中获取的连接
     *
     * @param connection
     * @throws SQLException
     */
    public void releaseAnyConnection(Connection connection)throws SQLException;

    /**
     *
     * 根据此提供者的基本策略获取Hibernate使用的连接
     *
     * @param tenantIdentifier 连接租户的标识
     * @return
     * @throws SQLException
     */
    public Connection getConnection(String tenantIdentifier)throws SQLException;

    /**
     * 根据租户标识和connection释放连接
     *
     * @param tenantIdentifier
     * @param connection
     * @throws SQLException
     */
    public void releaseConnection(String tenantIdentifier,Connection connection)throws SQLException;

    /**
     * 此连接提供者是否支持JDBC连接的主动释放以及稍后重新获取这些连接（如果需要）？
     *
     *  这与{ org.hibernate.cfg.Environment＃RELEASE_CONNECTIONS}结合使用以主动释放JDBC连接。
     *  但是，配置的ConnectionProvider必须支持重新获取相同的基础连接，以使该语义起作用。
     *
     *  通常情况下，只有在托管环境中，容器通过事务或线程跟踪连接才是真实的。
     *
     *  请注意，JTA语义取决于底层连接提供者确实支持主动释放的事实。
     * @return
     */
    public boolean supportsAggressiveRelease();
}
