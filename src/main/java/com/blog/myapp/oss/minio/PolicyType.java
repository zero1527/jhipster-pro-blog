package com.blog.myapp.oss.minio;

/**
 * minio策略配置
 *
 */
public enum PolicyType {
    /**
     * 只读
     */
    READ,

    /**
     * 只写
     */
    WRITE,

    /**
     * 读写
     */
    READ_WRITE,
}
