package com.edwin.clinic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;


@NamedQuery(name="User.updateStatus",query = "update User u set u.status=:status where  u.id=:id")

@NamedQuery(name="User.getAllAdmin", query = "select u.email from User u where u.role='admin'")


@Entity
@Table(name = "user")
@Data
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class User  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;


    @Column(name="name")
    private String name;

    @Column(name="contact_number")
    private String contactNumber;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="status")
    private String status;

    @Column(name="role")
    private String role;

}
