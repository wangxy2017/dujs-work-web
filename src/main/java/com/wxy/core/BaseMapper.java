package com.wxy.core;

import java.util.List;

public interface BaseMapper<T> {
    /**
     * 保存对象
     *
     * @param t
     * @return
     */
    int save(T t);

    int saveByBatch(List<T> list);

    /**
     * 修改对象
     *
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 根据对象ID物理删除对象
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 根据对象ID逻辑删除对象
     *
     * @param id
     */
    void deleteActive(Long id);

    /**
     * 条件查询对象列表
     *
     * @param t
     * @return
     */
    List<T> queryList(T t);

    /**
     * 根据对象ID查询对象
     *
     * @param id
     * @return
     */
    T queryById(Long id);
}

