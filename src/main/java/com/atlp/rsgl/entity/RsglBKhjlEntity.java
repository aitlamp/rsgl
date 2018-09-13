package com.atlp.rsgl.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "RSGL_B_KHJL", schema = "RSGL", catalog = "")
public class RsglBKhjlEntity {
    private String khjlid;
    private String khyhid;
    private String khnd;
    private String khjd;
    private String khqk;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "KHJLID")
    public String getKhjlid() {
        return khjlid;
    }

    public void setKhjlid(String khjlid) {
        this.khjlid = khjlid;
    }

    @Basic
    @Column(name = "KHYHID")
    public String getKhyhid() {
        return khyhid;
    }

    public void setKhyhid(String khyhid) {
        this.khyhid = khyhid;
    }

    @Basic
    @Column(name = "KHND")
    public String getKhnd() {
        return khnd;
    }

    public void setKhnd(String khnd) {
        this.khnd = khnd;
    }

    @Basic
    @Column(name = "KHJD")
    public String getKhjd() {
        return khjd;
    }

    public void setKhjd(String khjd) {
        this.khjd = khjd;
    }

    @Basic
    @Column(name = "KHQK")
    public String getKhqk() {
        return khqk;
    }

    public void setKhqk(String khqk) {
        this.khqk = khqk;
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

        RsglBKhjlEntity that = (RsglBKhjlEntity) o;

        if (khjlid != null ? !khjlid.equals(that.khjlid) : that.khjlid != null) return false;
        if (khyhid != null ? !khyhid.equals(that.khyhid) : that.khyhid != null) return false;
        if (khnd != null ? !khnd.equals(that.khnd) : that.khnd != null) return false;
        if (khjd != null ? !khjd.equals(that.khjd) : that.khjd != null) return false;
        if (khqk != null ? !khqk.equals(that.khqk) : that.khqk != null) return false;
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
        int result = khjlid != null ? khjlid.hashCode() : 0;
        result = 31 * result + (khyhid != null ? khyhid.hashCode() : 0);
        result = 31 * result + (khnd != null ? khnd.hashCode() : 0);
        result = 31 * result + (khjd != null ? khjd.hashCode() : 0);
        result = 31 * result + (khqk != null ? khqk.hashCode() : 0);
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
