package com.atlp.rsgl.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "RSGL_B_KQJL", schema = "RSGL", catalog = "")
public class RsglBKqjlEntity {
    private String kqjlid;
    private String kqyhid;
    private Time qjsj;
    private Time xjsj;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "KQJLID")
    public String getKqjlid() {
        return kqjlid;
    }

    public void setKqjlid(String kqjlid) {
        this.kqjlid = kqjlid;
    }

    @Basic
    @Column(name = "KQYHID")
    public String getKqyhid() {
        return kqyhid;
    }

    public void setKqyhid(String kqyhid) {
        this.kqyhid = kqyhid;
    }

    @Basic
    @Column(name = "QJSJ")
    public Time getQjsj() {
        return qjsj;
    }

    public void setQjsj(Time qjsj) {
        this.qjsj = qjsj;
    }

    @Basic
    @Column(name = "XJSJ")
    public Time getXjsj() {
        return xjsj;
    }

    public void setXjsj(Time xjsj) {
        this.xjsj = xjsj;
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

        RsglBKqjlEntity that = (RsglBKqjlEntity) o;

        if (kqjlid != null ? !kqjlid.equals(that.kqjlid) : that.kqjlid != null) return false;
        if (kqyhid != null ? !kqyhid.equals(that.kqyhid) : that.kqyhid != null) return false;
        if (qjsj != null ? !qjsj.equals(that.qjsj) : that.qjsj != null) return false;
        if (xjsj != null ? !xjsj.equals(that.xjsj) : that.xjsj != null) return false;
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
        int result = kqjlid != null ? kqjlid.hashCode() : 0;
        result = 31 * result + (kqyhid != null ? kqyhid.hashCode() : 0);
        result = 31 * result + (qjsj != null ? qjsj.hashCode() : 0);
        result = 31 * result + (xjsj != null ? xjsj.hashCode() : 0);
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
