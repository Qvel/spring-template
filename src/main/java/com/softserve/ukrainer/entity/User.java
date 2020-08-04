package com.softserve.ukrainer.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "ukrainer")
@Component
@Scope("prototype")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String password;
    @ManyToMany
    @JoinTable(
        name="user_roles", schema = "ukrainer",
        joinColumns = {
            @JoinColumn(
                name= "user_id",
                nullable = false
            )
        },
        inverseJoinColumns = {
            @JoinColumn(
                name = "role_id",
                nullable = false
            )
        }

    )
    @JsonIgnore
    private List<Role> roleList = new ArrayList<>();
}
