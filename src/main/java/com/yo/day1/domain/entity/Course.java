package com.yo.day1.domain.entity;

import com.yo.day1.domain.AuditableEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Entity
@Table(name = "courses")//dung de chi dinh 1 class nao do map toi bang nao trong DB
@Data
@EqualsAndHashCode(callSuper = false)//tao ham so sanh equals() va hashCode()
//dung de so sanh du lieu field, khong phai so sanh RAM(mac dinh java ss)
//callSuper = false la de bo qua field  cua class cha
public class Course extends AuditableEntity {

    @Id//primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//DB tu sinh gia tri cho key
    //strategy(cach de tao ID)=GenerationType.IDENTITY(dùng cơ che tu sinh cua DB)
    private Long id;

    @Column(name = "course_code",nullable = false,unique = true,length = 20)
    private String courseCode;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private BigDecimal tuitionFee;

    @Column(nullable = false)
    private int totalSessions;

    @Column(nullable = false)
    private boolean isActive = true;
}
