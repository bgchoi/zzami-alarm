package com.zzami.alarm.api.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity(name = "alarm_weather")
@NoArgsConstructor
public class WeatherInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "weather_id", nullable = false)
  private long weatherId;
  
  @Column(name="templature")
  private Float templature;
  
  @Column(name="templature_min")
  private Float templatureMin;
  
  @Column(name="templature_max")
  private Float templatureMax;
  
  @Column(name="tip_message", columnDefinition = "text")
  private String tipMessage;
  
  @Column(name="status")
  private int status;
  
  @Column(name="fcast_dt")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fcastDt;
 
  @Column(name="create_dt")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDt;
  
  @ToString.Exclude
  @JoinColumn(name="add_id")
  @ManyToOne(targetEntity = AddressInfo.class, optional = false, fetch=FetchType.LAZY)
  private AddressInfo addressInfo;
}
