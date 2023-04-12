package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "asistente", schema = "skybank", catalog = "")
public class AsistenteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAsistente", nullable = false)
    private int idAsistente;
    @OneToMany(mappedBy = "asistenteByIdAsistente")
    private Set<ConversacionEntity> conversacionsByIdAsistente;

    public int getIdAsistente() {
        return idAsistente;
    }

    public void setIdAsistente(int idAsistente) {
        this.idAsistente = idAsistente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsistenteEntity that = (AsistenteEntity) o;
        return idAsistente == that.idAsistente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAsistente);
    }

    public Set<ConversacionEntity> getConversacionsByIdAsistente() {
        return conversacionsByIdAsistente;
    }

    public void setConversacionsByIdAsistente(Set<ConversacionEntity> conversacionsByIdAsistente) {
        this.conversacionsByIdAsistente = conversacionsByIdAsistente;
    }
}
