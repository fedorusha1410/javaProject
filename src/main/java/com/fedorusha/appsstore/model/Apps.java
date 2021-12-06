package com.fedorusha.appsstore.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "apps")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Apps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    private long id;


    @Column(name = "app_name")
    private String name;


    @Column(name = "description")
    private String description;



}
