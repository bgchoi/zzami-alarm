package com.zzami.alarm.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String username;
  private String password;
}
