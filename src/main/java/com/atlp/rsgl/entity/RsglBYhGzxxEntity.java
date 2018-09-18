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
@Table(name = "RSGL_B_YH_GZXX")
public class RsglBYhGzxxEntity {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String yhid;
    private String gwdjid;
    private String gwdjmc;
    private String qypygwid;
    private String qypygwmc;
    private String zwid;
    private String zwmc;
    private String zjid;
    private String zjmc;
    private String zdid;
    private String zdmc;
    private String sm;
    private String dqzt;
    private Timestamp firsttime;
    private Timestamp lasttime;
    private String yhid2;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;
    private String yid;

}
