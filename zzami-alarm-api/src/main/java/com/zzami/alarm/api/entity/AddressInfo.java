package com.zzami.alarm.api.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "address_info")
@NoArgsConstructor
public class AddressInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "add_id", nullable = false)
  private Long addId;
   
  @Column(name ="add_cd", length=10,  nullable=false, columnDefinition = "char(10)")
  private String addCd;
  
  @Column(name ="add_nm", length=100, nullable=false)
  private String addNm;
  
  @Column(name="lat")
  private Float lat;
  
  @Column(name="lon")
  private Float lon;
  
  @Column(name="nx")
  private Float nx;
  
  @Column(name="ny")
  private Float ny;
  
  @Column(name="station_nm", length=200)
  private String stationNm;
  
  @Column(name="is_use")
  private int isUse;
  
  @Column(name="create_dt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDt;
  
  @ToString.Exclude
  @OneToMany( targetEntity = WeatherInfo.class,
              cascade = CascadeType.ALL,
              fetch = FetchType.LAZY,
              mappedBy = "addressInfo")
  private Set<WeatherInfo> weatherInfoList = new HashSet<>();
  
  @ToString.Exclude
  @OneToMany( targetEntity = DustInfo.class,
              cascade = CascadeType.ALL,
              fetch = FetchType.LAZY,
              mappedBy = "addressInfo")
  private Set<DustInfo> dustInfoList = new HashSet<>();
  
   
}
