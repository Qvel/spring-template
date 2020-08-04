package com.softserve.ukrainer.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles", schema = "ukrainer")
@Component
@Scope("prototype")
public class Role implements Serializable {

    @Id
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roleList")
    @JsonIgnore
    private Set<User> users;
}
