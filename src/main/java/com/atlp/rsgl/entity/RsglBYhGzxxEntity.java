package com.atlp.rsgl.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "RSGL_B_YH_GZXX", schema = "RSGL", catalog = "")
public class RsglBYhGzxxEntity {
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
    private Time firsttime;
    private Time lasttime;
    private String yhid2;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "YHID")
    public String getYhid() {
        return yhid;
    }

    public void setYhid(String yhid) {
        this.yhid = yhid;
    }

    @Basic
    @Column(name = "GWDJID")
    public String getGwdjid() {
        return gwdjid;
    }

    public void setGwdjid(String gwdjid) {
        this.gwdjid = gwdjid;
    }

    @Basic
    @Column(name = "GWDJMC")
    public String getGwdjmc() {
        return gwdjmc;
    }

    public void setGwdjmc(String gwdjmc) {
        this.gwdjmc = gwdjmc;
    }

    @Basic
    @Column(name = "QYPYGWID")
    public String getQypygwid() {
        return qypygwid;
    }

    public void setQypygwid(String qypygwid) {
        this.qypygwid = qypygwid;
    }

    @Basic
    @Column(name = "QYPYGWMC")
    public String getQypygwmc() {
        return qypygwmc;
    }

    public void setQypygwmc(String qypygwmc) {
        this.qypygwmc = qypygwmc;
    }

    @Basic
    @Column(name = "ZWID")
    public String getZwid() {
        return zwid;
    }

    public void setZwid(String zwid) {
        this.zwid = zwid;
    }

    @Basic
    @Column(name = "ZWMC")
    public String getZwmc() {
        return zwmc;
    }

    public void setZwmc(String zwmc) {
        this.zwmc = zwmc;
    }

    @Basic
    @Column(name = "ZJID")
    public String getZjid() {
        return zjid;
    }

    public void setZjid(String zjid) {
        this.zjid = zjid;
    }

    @Basic
    @Column(name = "ZJMC")
    public String getZjmc() {
        return zjmc;
    }

    public void setZjmc(String zjmc) {
        this.zjmc = zjmc;
    }

    @Basic
    @Column(name = "ZDID")
    public String getZdid() {
        return zdid;
    }

    public void setZdid(String zdid) {
        this.zdid = zdid;
    }

    @Basic
    @Column(name = "ZDMC")
    public String getZdmc() {
        return zdmc;
    }

    public void setZdmc(String zdmc) {
        this.zdmc = zdmc;
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
    @Column(name = "YHID2")
    public String getYhid2() {
        return yhid2;
    }

    public void setYhid2(String yhid2) {
        this.yhid2 = yhid2;
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

        RsglBYhGzxxEntity that = (RsglBYhGzxxEntity) o;

        if (yhid != null ? !yhid.equals(that.yhid) : that.yhid != null) return false;
        if (gwdjid != null ? !gwdjid.equals(that.gwdjid) : that.gwdjid != null) return false;
        if (gwdjmc != null ? !gwdjmc.equals(that.gwdjmc) : that.gwdjmc != null) return false;
        if (qypygwid != null ? !qypygwid.equals(that.qypygwid) : that.qypygwid != null) return false;
        if (qypygwmc != null ? !qypygwmc.equals(that.qypygwmc) : that.qypygwmc != null) return false;
        if (zwid != null ? !zwid.equals(that.zwid) : that.zwid != null) return false;
        if (zwmc != null ? !zwmc.equals(that.zwmc) : that.zwmc != null) return false;
        if (zjid != null ? !zjid.equals(that.zjid) : that.zjid != null) return false;
        if (zjmc != null ? !zjmc.equals(that.zjmc) : that.zjmc != null) return false;
        if (zdid != null ? !zdid.equals(that.zdid) : that.zdid != null) return false;
        if (zdmc != null ? !zdmc.equals(that.zdmc) : that.zdmc != null) return false;
        if (sm != null ? !sm.equals(that.sm) : that.sm != null) return false;
        if (dqzt != null ? !dqzt.equals(that.dqzt) : that.dqzt != null) return false;
        if (firsttime != null ? !firsttime.equals(that.firsttime) : that.firsttime != null) return false;
        if (lasttime != null ? !lasttime.equals(that.lasttime) : that.lasttime != null) return false;
        if (yhid2 != null ? !yhid2.equals(that.yhid2) : that.yhid2 != null) return false;
        if (yhxm != null ? !yhxm.equals(that.yhxm) : that.yhxm != null) return false;
        if (yhdwid != null ? !yhdwid.equals(that.yhdwid) : that.yhdwid != null) return false;
        if (yhdwmc != null ? !yhdwmc.equals(that.yhdwmc) : that.yhdwmc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = yhid != null ? yhid.hashCode() : 0;
        result = 31 * result + (gwdjid != null ? gwdjid.hashCode() : 0);
        result = 31 * result + (gwdjmc != null ? gwdjmc.hashCode() : 0);
        result = 31 * result + (qypygwid != null ? qypygwid.hashCode() : 0);
        result = 31 * result + (qypygwmc != null ? qypygwmc.hashCode() : 0);
        result = 31 * result + (zwid != null ? zwid.hashCode() : 0);
        result = 31 * result + (zwmc != null ? zwmc.hashCode() : 0);
        result = 31 * result + (zjid != null ? zjid.hashCode() : 0);
        result = 31 * result + (zjmc != null ? zjmc.hashCode() : 0);
        result = 31 * result + (zdid != null ? zdid.hashCode() : 0);
        result = 31 * result + (zdmc != null ? zdmc.hashCode() : 0);
        result = 31 * result + (sm != null ? sm.hashCode() : 0);
        result = 31 * result + (dqzt != null ? dqzt.hashCode() : 0);
        result = 31 * result + (firsttime != null ? firsttime.hashCode() : 0);
        result = 31 * result + (lasttime != null ? lasttime.hashCode() : 0);
        result = 31 * result + (yhid2 != null ? yhid2.hashCode() : 0);
        result = 31 * result + (yhxm != null ? yhxm.hashCode() : 0);
        result = 31 * result + (yhdwid != null ? yhdwid.hashCode() : 0);
        result = 31 * result + (yhdwmc != null ? yhdwmc.hashCode() : 0);
        return result;
    }
}
