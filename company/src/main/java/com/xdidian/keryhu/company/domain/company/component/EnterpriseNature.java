package com.xdidian.keryhu.company.domain.company.component;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by hushuming on 2016/9/23.
 *
 * 企业性质
 * // 为什么要写 @JsonIgnoreProperties(ignoreUnknown = true)
 * ，因为申请公司注册的人员，当修改信息的时候，
 // 有可能公司行业没有修改，那么company/createCompanyAfterReject，
 // 中CompanyIndustry的value就是null
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public enum EnterpriseNature {

    SHI_YE("政府机关/事业单位"),
    GUO_QI("国营"),
    SI_YING("私营"),
    ZHONG_WAI("中外合资"),
    WAI_ZI("外资"),
    OTHER("其他");

    private final String value;

    EnterpriseNature(String value){
        this.value=value;
    }

    @JsonValue
    public String toValue(){
        return this.value;
    }

    @JsonCreator
    public static EnterpriseNature forValue(String value){

        if(value==null||value.isEmpty()){
            return  null;
        }
        else {
            for(EnterpriseNature enterpriseNature:EnterpriseNature.values()){
                if(enterpriseNature.value.equals(value))
                    return enterpriseNature;
            }

            String err=new StringBuffer("您提供的值: ")
                    .append(value)
                    .append(" 不对！")
                    .toString();

            throw new IllegalArgumentException(err);
        }
    }

}
