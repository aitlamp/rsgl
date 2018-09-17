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
@Table(name = "RSGL_B_ZD")
public class RsglBZdEntity {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String zdid;
    private String zjid;
    private String zdmc;
    private String zjmc;
    private long pwsx;
    private String sm;
    private String dqzt;
    private Timestamp firsttime;
    private Timestamp lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

}
