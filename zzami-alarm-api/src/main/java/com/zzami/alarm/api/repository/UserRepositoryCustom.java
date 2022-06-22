package com.zzami.alarm.api.repository;

/**
* @packageName : com.zzami.alarm.api.repository 
* @fileName : UserRepositoryCustom.java 
* @author : bong 
* @date : 2022.05.05 
* @description : JPA Repository에서 오는 메소드를 재정의하거나,QueryDSL로 구현한 메소드를 사용하고자 할때
* UserRepositoryCustom 인터페이스를 구현체를 만들면 UserRepository에서 사용할수 있게된다.
*
*/
public interface UserRepositoryCustom  {
 
    Object getOne(String username);
}
