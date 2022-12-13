package com.example.Inst_Sait.entity;

import com.example.Inst_Sait.entity.enums.ERole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity

public class User implements UserDetails  {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    private String name;
    @Column (unique = true, updatable = false)
    private String username;
    @Column (nullable = false)
    private String lastname;
    @Column (unique = true)
    private String email;
    @Column (columnDefinition = "text")
    private String bio;
    @Column (length = 3000)
    private String password;

    @ElementCollection(targetClass = ERole.class)

    @CollectionTable (name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> role = new HashSet<>();

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    private List <Post> posts = new ArrayList<>();

    @JsonFormat (pattern = "yyyy-mm-dd HH:mm:ss")
    @Column (updatable = false)
    private LocalDateTime createdDate;

    @Transient
    private Collection <? extends GrantedAuthority> authorities;

    public User ()
    {
    }

    public User(Long id,
                String username,
                String email,
                String password,
                Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    @PrePersist
    protected void onCreate ()
    {
        this.createdDate = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     *
     * SECURITY
     */

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ERole> getRole() {
        return role;
    }

    public void setRole(Set<ERole> role) {
        this.role = role;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
