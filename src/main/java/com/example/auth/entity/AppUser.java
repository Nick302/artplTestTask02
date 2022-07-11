package com.example.auth.entity;

import com.example.auth.bean.RegistrationRequest;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "app_user")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @Min(6)
    @Max(32)
    @NotBlank
    private String name;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "password")
    @Min(6)
    @Max(32)
    @NotBlank
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private AppUserRole role = AppUserRole.USER;

    @Column(name = "locked")
    private boolean locked;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "app_user", fetch = FetchType.LAZY)
    @NotNull
    private List<Pet> pets;

    public AppUser(RegistrationRequest registrationRequest) {
        this.name = registrationRequest.getName();
        this.email = registrationRequest.getEmail();
        this.password = registrationRequest.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
