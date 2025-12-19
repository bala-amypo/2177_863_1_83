package om.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDteTime;

@Entity
@Table(name='vendors',uniqueConstraints=@UnigueConstraint(columnNames="name"))
public class Vendor{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique=true)
    private String name;

    private String contactEmail;
    private String contactPhone;

    @Column(nullable=false)
    private Boolean active=true;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate()
}