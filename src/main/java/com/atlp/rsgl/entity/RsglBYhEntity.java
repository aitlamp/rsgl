package com.atlp.rsgl.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "RSGL_B_YH")
public class RsglBYhEntity {
    private String yhid;
    private String yhxm;
    private byte yhpwsx;
    private String dwid;
    private String dwmc;
    private String dwjc;
    private String sfzh;
    private String xb;
    private String mz;
    private Time csrq;
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
    private Time firsttime;
    private Time lasttime;
    private String yhid2;
    private String yhxm2;
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
    @Column(name = "YHXM")
    public String getYhxm() {
        return yhxm;
    }

    public void setYhxm(String yhxm) {
        this.yhxm = yhxm;
    }

    @Basic
    @Column(name = "YHPWSX")
    public byte getYhpwsx() {
        return yhpwsx;
    }

    public void setYhpwsx(byte yhpwsx) {
        this.yhpwsx = yhpwsx;
    }

    @Basic
    @Column(name = "DWID")
    public String getDwid() {
        return dwid;
    }

    public void setDwid(String dwid) {
        this.dwid = dwid;
    }

    @Basic
    @Column(name = "DWMC")
    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    @Basic
    @Column(name = "DWJC")
    public String getDwjc() {
        return dwjc;
    }

    public void setDwjc(String dwjc) {
        this.dwjc = dwjc;
    }

    @Basic
    @Column(name = "SFZH")
    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    @Basic
    @Column(name = "XB")
    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    @Basic
    @Column(name = "MZ")
    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    @Basic
    @Column(name = "CSRQ")
    public Time getCsrq() {
        return csrq;
    }

    public void setCsrq(Time csrq) {
        this.csrq = csrq;
    }

    @Basic
    @Column(name = "ZZMM")
    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    @Basic
    @Column(name = "XL")
    public String getXl() {
        return xl;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    @Basic
    @Column(name = "JG")
    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    @Basic
    @Column(name = "SJH")
    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
    }

    @Basic
    @Column(name = "ZJH")
    public String getZjh() {
        return zjh;
    }

    public void setZjh(String zjh) {
        this.zjh = zjh;
    }

    @Basic
    @Column(name = "QQ")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "WX")
    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "ZPFJID")
    public String getZpfjid() {
        return zpfjid;
    }

    public void setZpfjid(String zpfjid) {
        this.zpfjid = zpfjid;
    }

    @Basic
    @Column(name = "DLID")
    public String getDlid() {
        return dlid;
    }

    public void setDlid(String dlid) {
        this.dlid = dlid;
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
    @Column(name = "YHXM2")
    public String getYhxm2() {
        return yhxm2;
    }

    public void setYhxm2(String yhxm2) {
        this.yhxm2 = yhxm2;
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

        RsglBYhEntity that = (RsglBYhEntity) o;

        if (yhpwsx != that.yhpwsx) return false;
        if (yhid != null ? !yhid.equals(that.yhid) : that.yhid != null) return false;
        if (yhxm != null ? !yhxm.equals(that.yhxm) : that.yhxm != null) return false;
        if (dwid != null ? !dwid.equals(that.dwid) : that.dwid != null) return false;
        if (dwmc != null ? !dwmc.equals(that.dwmc) : that.dwmc != null) return false;
        if (dwjc != null ? !dwjc.equals(that.dwjc) : that.dwjc != null) return false;
        if (sfzh != null ? !sfzh.equals(that.sfzh) : that.sfzh != null) return false;
        if (xb != null ? !xb.equals(that.xb) : that.xb != null) return false;
        if (mz != null ? !mz.equals(that.mz) : that.mz != null) return false;
        if (csrq != null ? !csrq.equals(that.csrq) : that.csrq != null) return false;
        if (zzmm != null ? !zzmm.equals(that.zzmm) : that.zzmm != null) return false;
        if (xl != null ? !xl.equals(that.xl) : that.xl != null) return false;
        if (jg != null ? !jg.equals(that.jg) : that.jg != null) return false;
        if (sjh != null ? !sjh.equals(that.sjh) : that.sjh != null) return false;
        if (zjh != null ? !zjh.equals(that.zjh) : that.zjh != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (wx != null ? !wx.equals(that.wx) : that.wx != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (zpfjid != null ? !zpfjid.equals(that.zpfjid) : that.zpfjid != null) return false;
        if (dlid != null ? !dlid.equals(that.dlid) : that.dlid != null) return false;
        if (sm != null ? !sm.equals(that.sm) : that.sm != null) return false;
        if (dqzt != null ? !dqzt.equals(that.dqzt) : that.dqzt != null) return false;
        if (firsttime != null ? !firsttime.equals(that.firsttime) : that.firsttime != null) return false;
        if (lasttime != null ? !lasttime.equals(that.lasttime) : that.lasttime != null) return false;
        if (yhid2 != null ? !yhid2.equals(that.yhid2) : that.yhid2 != null) return false;
        if (yhxm2 != null ? !yhxm2.equals(that.yhxm2) : that.yhxm2 != null) return false;
        if (yhdwid != null ? !yhdwid.equals(that.yhdwid) : that.yhdwid != null) return false;
        if (yhdwmc != null ? !yhdwmc.equals(that.yhdwmc) : that.yhdwmc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = yhid != null ? yhid.hashCode() : 0;
        result = 31 * result + (yhxm != null ? yhxm.hashCode() : 0);
        result = 31 * result + (int) yhpwsx;
        result = 31 * result + (dwid != null ? dwid.hashCode() : 0);
        result = 31 * result + (dwmc != null ? dwmc.hashCode() : 0);
        result = 31 * result + (dwjc != null ? dwjc.hashCode() : 0);
        result = 31 * result + (sfzh != null ? sfzh.hashCode() : 0);
        result = 31 * result + (xb != null ? xb.hashCode() : 0);
        result = 31 * result + (mz != null ? mz.hashCode() : 0);
        result = 31 * result + (csrq != null ? csrq.hashCode() : 0);
        result = 31 * result + (zzmm != null ? zzmm.hashCode() : 0);
        result = 31 * result + (xl != null ? xl.hashCode() : 0);
        result = 31 * result + (jg != null ? jg.hashCode() : 0);
        result = 31 * result + (sjh != null ? sjh.hashCode() : 0);
        result = 31 * result + (zjh != null ? zjh.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (wx != null ? wx.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (zpfjid != null ? zpfjid.hashCode() : 0);
        result = 31 * result + (dlid != null ? dlid.hashCode() : 0);
        result = 31 * result + (sm != null ? sm.hashCode() : 0);
        result = 31 * result + (dqzt != null ? dqzt.hashCode() : 0);
        result = 31 * result + (firsttime != null ? firsttime.hashCode() : 0);
        result = 31 * result + (lasttime != null ? lasttime.hashCode() : 0);
        result = 31 * result + (yhid2 != null ? yhid2.hashCode() : 0);
        result = 31 * result + (yhxm2 != null ? yhxm2.hashCode() : 0);
        result = 31 * result + (yhdwid != null ? yhdwid.hashCode() : 0);
        result = 31 * result + (yhdwmc != null ? yhdwmc.hashCode() : 0);
        return result;
    }
}
