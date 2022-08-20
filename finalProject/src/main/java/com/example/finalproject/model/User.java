package com.example.finalproject.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username không đc để trống")
    @Column(name = "username")
    private String username;

    @Email(message = "Email không hợp lệ")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Password không đc để trống")
//    @Min(value = 6, message = "Password phải từ 6 kí tự trở lên")
    @Column(name = "password")
    private String password;

//    @Max(value = 11, message = "Phone không hợp lệ")
    @Column(name = "phone")
    private String phone;

    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private Date updatedAt;

    // lazy
    // create-drop -> update
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "tables")
    private Set<OrderTable> orderTables;
}
