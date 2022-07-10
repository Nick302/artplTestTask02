package com.example.artpltesttask02.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "pets")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Getter
@Setter
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nickname")
    @Min(2)
    @Max(15)
    @NotBlank
    private String nickName;

    @Column(name = "birthday")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @NotBlank
    private Date birthDay;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    @NotBlank
    private Sex sex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users")
    @NotBlank
    @JsonIdentityReference(alwaysAsId = true)
    private AppUser user;

    public Pet() {

    }

    public Pet(Long id, String nickName, Date birthDay, Sex sex, AppUser user) {
        this.id = id;
        this.nickName = nickName;
        this.birthDay = birthDay;
        this.sex = sex;
        this.user = user;
    }


}
