package com.zzami.alarm.api.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long usn;
  
  @Column(name = "user_id", length = 36, nullable = false)
  private String userId;
  
  @Column(name = "username", length = 36, nullable = false)
  private String username;
  
  @Column(name = "encryted_password", length = 128, nullable = false)
  private String encrytedPassword;

  @Column(name = "enabled", length = 1, nullable = false)
  private boolean enabled;
  
  @Column(name="password_update_dt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date passwordUpdateDt; 
  
  @Column(name="create_dt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDt;
  
  @Column(name="update_dt")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updateDt;

}
