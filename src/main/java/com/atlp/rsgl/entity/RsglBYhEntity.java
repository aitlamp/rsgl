package com.atlp.rsgl.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "RSGL_B_YH")
public class RsglBYhEntity {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String yhid;
    private String yhxm;
    private byte yhpwsx;
    private String dwid;
    private String dwmc;
    private String dwjc;
    private String llbid;
    private String llbmc;
    private String slbid;
    private String slbmc;
    private String sfzh;
    private String xb;
    private String mz;
    private Timestamp csrq;
    private String zzmm;
    private String xl;
    private String jg;
    private String sjh;
    private String zjh;
    private String qq;
    private String wx;
    private String email;
    private String zpfjid;
    private String dlid;
    private String sm;
    private String dqzt;
    private Timestamp firsttime;
    private Timestamp lasttime;
    private String yhid2;
    private String yhxm2;
    private String yhdwid;
    private String yhdwmc;

}
