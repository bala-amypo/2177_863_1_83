package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vendors")
public class Vendor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(unique = true, nullable = false) private String name;
    @Column(name = "contact_email", nullable = false) private String contactEmail;
    @Column(name = "contact_phone", nullable = false) private String contactPhone;
    private boolean active = true;

    public Vendor() {}
    public Vendor(String name, String contactEmail, String contactPhone) {
        this.name = name; this.contactEmail = contactEmail; this.contactPhone = contactPhone;
    }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getContactEmail() { return contactEmail; } public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public String getContactPhone() { return contactPhone; } public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public boolean getActive() { return active; } public void setActive(boolean active) { this.active = active; }
}
