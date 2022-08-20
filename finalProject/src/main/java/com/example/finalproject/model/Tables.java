package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@javax.persistence.Table(name = "tables")
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "table_name")
    private String tableName;

    @Column(name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    // cascade - upload multiple file - tu child add value to parent
    @OneToMany(mappedBy = "tables", cascade = CascadeType.ALL)
    private Set<Image> image;

    @OneToMany(mappedBy = "tables")
    private Set<OrderTable> orderTables;

    // fetch = FetchType.LAZY
//    @ManyToOne()
//    @JoinColumn(name = "status_id")
//    private Status status;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

}
