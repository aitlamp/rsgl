package com.atlp.rsgl.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "RSGL_B_PXJL", schema = "RSGL", catalog = "")
public class RsglBPxjlEntity {
    private String pxjlid;
    private String pxyhid;
    private String pxmc;
    private Time pxsj;
    private String pxdd;
    private String cbjg;
    private Long xs;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "PXJLID")
    public String getPxjlid() {
        return pxjlid;
    }

    public void setPxjlid(String pxjlid) {
        this.pxjlid = pxjlid;
    }

    @Basic
    @Column(name = "PXYHID")
    public String getPxyhid() {
        return pxyhid;
    }

    public void setPxyhid(String pxyhid) {
        this.pxyhid = pxyhid;
    }

    @Basic
    @Column(name = "PXMC")
    public String getPxmc() {
        return pxmc;
    }

    public void setPxmc(String pxmc) {
        this.pxmc = pxmc;
    }

    @Basic
    @Column(name = "PXSJ")
    public Time getPxsj() {
        return pxsj;
    }

    public void setPxsj(Time pxsj) {
        this.pxsj = pxsj;
    }

    @Basic
    @Column(name = "PXDD")
    public String getPxdd() {
        return pxdd;
    }

    public void setPxdd(String pxdd) {
        this.pxdd = pxdd;
    }

    @Basic
    @Column(name = "CBJG")
    public String getCbjg() {
        return cbjg;
    }

    public void setCbjg(String cbjg) {
        this.cbjg = cbjg;
    }

    @Basic
    @Column(name = "XS")
    public Long getXs() {
        return xs;
    }

    public void setXs(Long xs) {
        this.xs = xs;
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

        RsglBPxjlEntity that = (RsglBPxjlEntity) o;

        if (pxjlid != null ? !pxjlid.equals(that.pxjlid) : that.pxjlid != null) return false;
        if (pxyhid != null ? !pxyhid.equals(that.pxyhid) : that.pxyhid != null) return false;
        if (pxmc != null ? !pxmc.equals(that.pxmc) : that.pxmc != null) return false;
        if (pxsj != null ? !pxsj.equals(that.pxsj) : that.pxsj != null) return false;
        if (pxdd != null ? !pxdd.equals(that.pxdd) : that.pxdd != null) return false;
        if (cbjg != null ? !cbjg.equals(that.cbjg) : that.cbjg != null) return false;
        if (xs != null ? !xs.equals(that.xs) : that.xs != null) return false;
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
        int result = pxjlid != null ? pxjlid.hashCode() : 0;
        result = 31 * result + (pxyhid != null ? pxyhid.hashCode() : 0);
        result = 31 * result + (pxmc != null ? pxmc.hashCode() : 0);
        result = 31 * result + (pxsj != null ? pxsj.hashCode() : 0);
        result = 31 * result + (pxdd != null ? pxdd.hashCode() : 0);
        result = 31 * result + (cbjg != null ? cbjg.hashCode() : 0);
        result = 31 * result + (xs != null ? xs.hashCode() : 0);
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
