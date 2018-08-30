package com.fallen.springboot.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID> {

	List<T> findBySql(String sql);
	
	List<T> findBySql(String sql,Map<String, Object> map);

}
