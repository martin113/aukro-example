package com.example.restaurantmenus.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
//`users`
@Entity(name = "users")
public class User implements Serializable {

    @Id
    @SequenceGenerator(name="user_iduser_seq", sequenceName="user_iduser_seq", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_iduser_seq")
    @Column(name = "iduser", columnDefinition = "serial", updatable=false)
    private Long id;

    @NotNull(message = "NotNull.user.description")
    @Column(unique = true)
    @Size(min = 1, max = 30, message = "Size.user.description")
    private String username;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "iduser"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "idrole"))
    private Set<Role> roles;

    private boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Prague")
    @Column(nullable = false, updatable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Prague")
    private Date updated;

    //TODO JPA callbacks do not work with the Session API
    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    //TODO same as above
    @PreUpdate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected void onUpdate() {
        updated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}