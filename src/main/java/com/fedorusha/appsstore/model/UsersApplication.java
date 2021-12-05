package com.fedorusha.appsstore.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@Entity
@Table(name = "Users_apps")
@Getter
@Setter
public class UsersApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usersApp_id", insertable = false, updatable = false)
    private long id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_app")
    private Apps application;



}
