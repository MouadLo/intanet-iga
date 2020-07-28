package io.intranet.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="etudiants")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 15, message= "Nom incorect")
    private String nom;

    @NotNull
    @Size(min = 5, max = 15, message= "Prenom incorect")
    private String prenom;

    @NotNull
    @Size(min = 5, max = 100, message= "Adresse incorect")
    private String adresse;

    @Size(min = 5, max = 35, message= "Mail incorect")
    private String mail;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groups_id", referencedColumnName = "id")
    private Group group;

}
