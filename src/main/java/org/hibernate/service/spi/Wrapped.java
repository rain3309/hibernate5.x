package org.hibernate.service.spi;

/**
 * 用于包装有访问权限的可选的services 。
 * 例如, 一个维护 {@link javax.sql.DataSource} 的service 想要暴露这个访问接口{@link javax.sql.DataSource}或 它的实现类{@link java.sql.Connection}.
 */
public interface Wrapped {

    /**
     * 一个包装的service是否可以展开(转换)为指定的类型
     * @param unwrapType
     * @return
     */
    public boolean isUnWrappableAs(Class unwrapType);

    /**
     * 取消代理服务
     *
     * @param unwrapType 这个类型转换为它的实现类
     * @param <T>
     * @return
     */
    public <T> T unwrap(Class<T> unwrapType);
}
