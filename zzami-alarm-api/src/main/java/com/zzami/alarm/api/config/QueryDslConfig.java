package com.zzami.alarm.api.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Configuration;
import com.querydsl.jpa.impl.JPAQueryFactory;


@Configuration
public class QueryDslConfig {
  
  @PersistenceContext
  private EntityManager entityManager;
  
  
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(entityManager);
  }
  
  
}