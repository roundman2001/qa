package com.fallen.springboot.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager; 
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseDao<T, ID> {
	private final EntityManager entityManager;
	private final Session session;
	private final JpaEntityInformation entityInformation;
	private final Class<T> clazz;

	public BaseDaoImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		entityManager=entityManager.getEntityManagerFactory().createEntityManager();
		this.session = (Session) entityManager.unwrap(Session.class);
		this.entityInformation = entityInformation;
		this.clazz = entityInformation.getJavaType();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findBySql(String sql) {
		return entityManager.createNativeQuery(sql, clazz).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findBySql(String sql,Map<String, Object> map) {  
		 
		Query<T> query= session.createNativeQuery(sql);
		
		query.setProperties(map);
		
		return query.getResultList();
	}

}
