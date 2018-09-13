package com.atlp.rsgl.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "RSGL_B_CCJL", schema = "RSGL", catalog = "")
public class RsglBCcjlEntity {
    private String ccjlid;
    private String ccyhid;
    private String ccyy;
    private Time ccsj;
    private String cccs;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "CCJLID")
    public String getCcjlid() {
        return ccjlid;
    }

    public void setCcjlid(String ccjlid) {
        this.ccjlid = ccjlid;
    }

    @Basic
    @Column(name = "CCYHID")
    public String getCcyhid() {
        return ccyhid;
    }

    public void setCcyhid(String ccyhid) {
        this.ccyhid = ccyhid;
    }

    @Basic
    @Column(name = "CCYY")
    public String getCcyy() {
        return ccyy;
    }

    public void setCcyy(String ccyy) {
        this.ccyy = ccyy;
    }

    @Basic
    @Column(name = "CCSJ")
    public Time getCcsj() {
        return ccsj;
    }

    public void setCcsj(Time ccsj) {
        this.ccsj = ccsj;
    }

    @Basic
    @Column(name = "CCCS")
    public String getCccs() {
        return cccs;
    }

    public void setCccs(String cccs) {
        this.cccs = cccs;
    }

    @Basic
    @Column(name = "SM")
    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    @Basic
    @Column(name = "DQZT")
    public String getDqzt() {
        return dqzt;
    }

    public void setDqzt(String dqzt) {
        this.dqzt = dqzt;
    }

    @Basic
    @Column(name = "FIRSTTIME")
    public Time getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(Time firsttime) {
        this.firsttime = firsttime;
    }

    @Basic
    @Column(name = "LASTTIME")
    public Time getLasttime() {
        return lasttime;
    }

    public void setLasttime(Time lasttime) {
        this.lasttime = lasttime;
    }

    @Basic
    @Column(name = "YHID")
    public String getYhid() {
        return yhid;
    }

    public void setYhid(String yhid) {
        this.yhid = yhid;
    }

    @Basic
    @Column(name = "YHXM")
    public String getYhxm() {
        return yhxm;
    }

    public void setYhxm(String yhxm) {
        this.yhxm = yhxm;
    }

    @Basic
    @Column(name = "YHDWID")
    public String getYhdwid() {
        return yhdwid;
    }

    public void setYhdwid(String yhdwid) {
        this.yhdwid = yhdwid;
    }

    @Basic
    @Column(name = "YHDWMC")
    public String getYhdwmc() {
        return yhdwmc;
    }

    public void setYhdwmc(String yhdwmc) {
        this.yhdwmc = yhdwmc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RsglBCcjlEntity that = (RsglBCcjlEntity) o;

        if (ccjlid != null ? !ccjlid.equals(that.ccjlid) : that.ccjlid != null) return false;
        if (ccyhid != null ? !ccyhid.equals(that.ccyhid) : that.ccyhid != null) return false;
        if (ccyy != null ? !ccyy.equals(that.ccyy) : that.ccyy != null) return false;
        if (ccsj != null ? !ccsj.equals(that.ccsj) : that.ccsj != null) return false;
        if (cccs != null ? !cccs.equals(that.cccs) : that.cccs != null) return false;
        if (sm != null ? !sm.equals(that.sm) : that.sm != null) return false;
        if (dqzt != null ? !dqzt.equals(that.dqzt) : that.dqzt != null) return false;
        if (firsttime != null ? !firsttime.equals(that.firsttime) : that.firsttime != null) return false;
        if (lasttime != null ? !lasttime.equals(that.lasttime) : that.lasttime != null) return false;
        if (yhid != null ? !yhid.equals(that.yhid) : that.yhid != null) return false;
        if (yhxm != null ? !yhxm.equals(that.yhxm) : that.yhxm != null) return false;
        if (yhdwid != null ? !yhdwid.equals(that.yhdwid) : that.yhdwid != null) return false;
        if (yhdwmc != null ? !yhdwmc.equals(that.yhdwmc) : that.yhdwmc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ccjlid != null ? ccjlid.hashCode() : 0;
        result = 31 * result + (ccyhid != null ? ccyhid.hashCode() : 0);
        result = 31 * result + (ccyy != null ? ccyy.hashCode() : 0);
        result = 31 * result + (ccsj != null ? ccsj.hashCode() : 0);
        result = 31 * result + (cccs != null ? cccs.hashCode() : 0);
        result = 31 * result + (sm != null ? sm.hashCode() : 0);
        result = 31 * result + (dqzt != null ? dqzt.hashCode() : 0);
        result = 31 * result + (firsttime != null ? firsttime.hashCode() : 0);
        result = 31 * result + (lasttime != null ? lasttime.hashCode() : 0);
        result = 31 * result + (yhid != null ? yhid.hashCode() : 0);
        result = 31 * result + (yhxm != null ? yhxm.hashCode() : 0);
        result = 31 * result + (yhdwid != null ? yhdwid.hashCode() : 0);
        result = 31 * result + (yhdwmc != null ? yhdwmc.hashCode() : 0);
        return result;
    }
}
