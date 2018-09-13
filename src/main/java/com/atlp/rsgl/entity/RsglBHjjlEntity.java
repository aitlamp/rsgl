package com.atlp.rsgl.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "RSGL_B_HJJL", schema = "RSGL", catalog = "")
public class RsglBHjjlEntity {
    private String hjjlid;
    private String hjyhid;
    private Time hjsj;
    private String hjmc;
    private String hjdj;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "HJJLID")
    public String getHjjlid() {
        return hjjlid;
    }

    public void setHjjlid(String hjjlid) {
        this.hjjlid = hjjlid;
    }

    @Basic
    @Column(name = "HJYHID")
    public String getHjyhid() {
        return hjyhid;
    }

    public void setHjyhid(String hjyhid) {
        this.hjyhid = hjyhid;
    }

    @Basic
    @Column(name = "HJSJ")
    public Time getHjsj() {
        return hjsj;
    }

    public void setHjsj(Time hjsj) {
        this.hjsj = hjsj;
    }

    @Basic
    @Column(name = "HJMC")
    public String getHjmc() {
        return hjmc;
    }

    public void setHjmc(String hjmc) {
        this.hjmc = hjmc;
    }

    @Basic
    @Column(name = "HJDJ")
    public String getHjdj() {
        return hjdj;
    }

    public void setHjdj(String hjdj) {
        this.hjdj = hjdj;
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

        RsglBHjjlEntity that = (RsglBHjjlEntity) o;

        if (hjjlid != null ? !hjjlid.equals(that.hjjlid) : that.hjjlid != null) return false;
        if (hjyhid != null ? !hjyhid.equals(that.hjyhid) : that.hjyhid != null) return false;
        if (hjsj != null ? !hjsj.equals(that.hjsj) : that.hjsj != null) return false;
        if (hjmc != null ? !hjmc.equals(that.hjmc) : that.hjmc != null) return false;
        if (hjdj != null ? !hjdj.equals(that.hjdj) : that.hjdj != null) return false;
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
        int result = hjjlid != null ? hjjlid.hashCode() : 0;
        result = 31 * result + (hjyhid != null ? hjyhid.hashCode() : 0);
        result = 31 * result + (hjsj != null ? hjsj.hashCode() : 0);
        result = 31 * result + (hjmc != null ? hjmc.hashCode() : 0);
        result = 31 * result + (hjdj != null ? hjdj.hashCode() : 0);
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
