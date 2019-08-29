package com.wxy.configuration;

import com.wxy.mapper.NoteMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * @Author wxy
 * @Date 19-7-19 下午5:30
 * @Description TODO
 **/
@MapperScan(basePackageClasses = NoteMapper.class)
@Component
public class MapperConfiguration {
}
