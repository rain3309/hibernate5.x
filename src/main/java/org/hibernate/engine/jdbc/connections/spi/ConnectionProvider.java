package org.hibernate.engine.jdbc.connections.spi;

import org.hibernate.service.Service;
import org.hibernate.service.spi.Wrapped;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 *  获取JDBC连接的合约
 *
 *  实现者也可能实现了连接池
 *
 *  实现者也应该提供一个公共的默认的构造方法
 *
 */
public interface ConnectionProvider extends Service,Wrapped {

    /**
     * 根据此提供者的基本策略获取Hibernate使用的连接
     * @return
     * @throws SQLException
     */
    public Connection getConnection()throws SQLException;

    /**
     * 释放一个hibernate使用的连接
     * @param connection
     * @throws SQLException
     */
    public void closeConnection(Connection connection)throws SQLException;

    /**
     * 此连接提供者是否支持JDBC连接的主动释放以及稍后重新获取这些连接（如果需要）？
     *
     * @return
     */
    public boolean supportAggressiveRelease();

}
