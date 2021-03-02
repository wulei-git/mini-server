package com.init.mini.db.mapper;

import com.init.mini.common.entity.RefreshTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RefreshTableMapper {

    List<RefreshTable> selectList(String status);
}
