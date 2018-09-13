package com.atlp.rsgl.common.data;

import com.atlp.rsgl.entity.RsglBYhEntity;
import lombok.Data;

/**
 * 用户信息
 *
 * @author ctc
 * @date 2018年8月23日 21:14:06
 */
@Data
public class UserInfo {
    // 用户信息
    private String yhid; // 用户ID
    private String dlid; // 登录ID
    private String yhxm; // 用户姓名
    private int yhpwsx; // 用户排位顺序
    // 单位信息
    private String dwid; // 单位ID
    private String dwbm; // 单位编码
    private String dwmc; // 单位名称
    private int dwpwsx; // 单位排位顺序
    // 岗位信息

    //构造函数
    public UserInfo(RsglBYhEntity yhEntity) {
        // 用户信息
        this.yhid = yhEntity.getYhid();
        this.dlid = yhEntity.getDlid();
        this.yhxm = yhEntity.getYhxm();
        this.yhpwsx = yhEntity.getYhpwsx();
        // 单位信息
        this.dwid = yhEntity.getDwid();
        this.dwmc = yhEntity.getDwmc();
        //this.dwpwsx = yhEntity.getDwpwsx();
    }
}
