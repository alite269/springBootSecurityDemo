package com.alite.springBootSecurityDemo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.alite.springBootSecurityDemo.converter.UserAuthority;

import lombok.Data;

//表明这是个需要生成数据表的类
@Entity
@Data
public class User {
//    定义主键id
    @Id
//    声明一个策略通用生成器，name为”system-uuid”,策略strategy为”uuid”。
    @GenericGenerator(name = "system-uuid", strategy ="uuid")
//    用generator属性指定要使用的策略生成器。
    @GeneratedValue(generator = "system-uuid")
    private String id;
    private String name;
    
    @Column(columnDefinition = "varchar(70) default 'John Snow'")
    private String password;

    @Column(columnDefinition = "integer default 27")
    private Integer age;

    @Column(columnDefinition = "boolean default false")
    private Boolean locked;
    @Column(columnDefinition = "varchar(50) default 'USER'")
    private String role;
    @Column(columnDefinition = "varchar(50) default 'USER'")
//    UserAuthority authorities;
    private String authorities;

    @Column(columnDefinition = "varchar(70)")
    private String emailAddress;
//    public String getId() {
//        return id;
//    }
//    public void setId(String id) {
//        this.id = id;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
    
}