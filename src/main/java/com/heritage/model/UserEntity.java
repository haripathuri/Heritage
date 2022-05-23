package com.heritage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Hari Pathuri
 * 5/17/2022 12:24 PM
 */

@Setter
@Getter
@Table(name = "tbl_user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    public UserEntity(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private Character active;
    @Column(name = "is_admin")
    private Character isAdmin;
/*    @OneToMany
    private List<AccountEntity> accountEntities;*/

}
