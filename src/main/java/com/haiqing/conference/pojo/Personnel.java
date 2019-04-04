package com.haiqing.conference.pojo;

/**
 * @description :人员实体类
 * @author haiqing
 * @Date: 2019/3/29
 */
public class Personnel {
    private Integer id;

    private String name;

    private String department;

    private String profession;

    private Byte sex;

    private Long createAt;

    private Integer createBy;

    private Long updataAt;

    private Integer updataBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Long getUpdataAt() {
        return updataAt;
    }

    public void setUpdataAt(Long updataAt) {
        this.updataAt = updataAt;
    }

    public Integer getUpdataBy() {
        return updataBy;
    }

    public void setUpdataBy(Integer updataBy) {
        this.updataBy = updataBy;
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", profession='" + profession + '\'' +
                ", sex=" + sex +
                ", createAt=" + createAt +
                ", createBy=" + createBy +
                ", updataAt=" + updataAt +
                ", updataBy=" + updataBy +
                '}';
    }
}