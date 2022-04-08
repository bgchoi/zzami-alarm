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
@ToString
@Entity(name = "dust_info")
@NoArgsConstructor
public class DustInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dust_id", nullable = false)
    private Long dustId;
    
    @Column(name="pm25")
    private Float pm25;
    
    @Column(name="pm10")
    private Float pm10;
    
    @Column(name="station_nm")
    private String stationNm;
    
    @Column(name="fcast_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fcastDt;
    
    @Column(name="create_dt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDt;
     
    @ToString.Exclude
    @JoinColumn(name="add_id")
    @ManyToOne(targetEntity = AddressInfo.class, optional = false, fetch=FetchType.LAZY)
    private AddressInfo addressInfo;
}
