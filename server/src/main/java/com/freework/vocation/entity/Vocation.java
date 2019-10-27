package com.freework.vocation.entity;


import java.util.Date;

/**
 * @author daihongru
 */
public class Vocation {

    /**
     * 岗位编号，自增主键
     */
    private Integer vocationId;

    /**
     * 分类ID
     */
    private Integer vocationCategoryId;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    /**
     * 企业名称，数据库无此字段
     */
    private String enterpriseName;

    /**
     * 岗位名称，不可为空
     */
    private String vocationName;

    /**
     * 岗位状态，0为冻结，1为正常，默认为0
     */
    private Integer status;

    /**
     * 岗位性质（全职、兼职、临时等），不可为空
     */
    private String workType;

    /**
     * 招聘人数，不可为空，0为"招满即止"
     */
    private Integer peopleNumber;

    /**
     * 最低工资
     */
    private Integer minSalary;

    /**
     * 最高工资
     */
    private Integer maxSalary;

    /**
     * 岗位工作经验要求
     */
    private String experience;

    /**
     * 岗位学历要求
     */
    private String education;

    /**
     * 岗位描述
     */
    private String profile;

    /**
     * 工作地点
     */
    private String place;

    /**
     * 岗位福利
     */
    private String welfare;

    /**
     * 将岗位福利拆分储存为列表，便于前台显示
     */
    private String[] welfareList;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近修改时间
     */
    private Date lastEditTime;


    public Integer getVocationId() {
        return vocationId;
    }

    public void setVocationId(Integer vocationId) {
        this.vocationId = vocationId;
    }

    public String getVocationName() {
        return vocationName;
    }

    public void setVocationName(String vocationName) {
        this.vocationName = vocationName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
        this.welfareList = welfare.split("#");
    }

    public String[] getWelfareList() {
        return welfareList;
    }

    public void setWelfareList(String[] welfareList) {
        this.welfareList = welfareList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getVocationCategoryId() {
        return vocationCategoryId;
    }

    public void setVocationCategoryId(Integer vocationCategoryId) {
        this.vocationCategoryId = vocationCategoryId;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}
