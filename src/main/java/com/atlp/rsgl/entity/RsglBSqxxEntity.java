package com.atlp.rsgl.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "RSGL_B_SQXX", schema = "RSGL", catalog = "")
public class RsglBSqxxEntity {
    private String sqid;
    private String sqyhid;
    private String sqyhxm;
    private String cdid;
    private String cdmc;
    private Time yxq;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "SQID")
    public String getSqid() {
        return sqid;
    }

    public void setSqid(String sqid) {
        this.sqid = sqid;
    }

    @Basic
    @Column(name = "SQYHID")
    public String getSqyhid() {
        return sqyhid;
    }

    public void setSqyhid(String sqyhid) {
        this.sqyhid = sqyhid;
    }

    @Basic
    @Column(name = "SQYHXM")
    public String getSqyhxm() {
        return sqyhxm;
    }

    public void setSqyhxm(String sqyhxm) {
        this.sqyhxm = sqyhxm;
    }

    @Basic
    @Column(name = "CDID")
    public String getCdid() {
        return cdid;
    }

    public void setCdid(String cdid) {
        this.cdid = cdid;
    }

    @Basic
    @Column(name = "CDMC")
    public String getCdmc() {
        return cdmc;
    }

    public void setCdmc(String cdmc) {
        this.cdmc = cdmc;
    }

    @Basic
    @Column(name = "YXQ")
    public Time getYxq() {
        return yxq;
    }

    public void setYxq(Time yxq) {
        this.yxq = yxq;
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

        RsglBSqxxEntity that = (RsglBSqxxEntity) o;

        if (sqid != null ? !sqid.equals(that.sqid) : that.sqid != null) return false;
        if (sqyhid != null ? !sqyhid.equals(that.sqyhid) : that.sqyhid != null) return false;
        if (sqyhxm != null ? !sqyhxm.equals(that.sqyhxm) : that.sqyhxm != null) return false;
        if (cdid != null ? !cdid.equals(that.cdid) : that.cdid != null) return false;
        if (cdmc != null ? !cdmc.equals(that.cdmc) : that.cdmc != null) return false;
        if (yxq != null ? !yxq.equals(that.yxq) : that.yxq != null) return false;
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
        int result = sqid != null ? sqid.hashCode() : 0;
        result = 31 * result + (sqyhid != null ? sqyhid.hashCode() : 0);
        result = 31 * result + (sqyhxm != null ? sqyhxm.hashCode() : 0);
        result = 31 * result + (cdid != null ? cdid.hashCode() : 0);
        result = 31 * result + (cdmc != null ? cdmc.hashCode() : 0);
        result = 31 * result + (yxq != null ? yxq.hashCode() : 0);
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
