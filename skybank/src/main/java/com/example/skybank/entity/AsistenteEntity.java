package com.example.skybank.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Asistente", schema = "skybank", catalog = "")
public class AsistenteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAsistente", nullable = false)
    private Integer idAsistente;
    @OneToMany(mappedBy = "asistenteByAsistenteIdAsistente")
    private Collection<ConversacionEntity> conversacionsByIdAsistente;

    public Integer getIdAsistente() {
        return idAsistente;
    }

    public void setIdAsistente(Integer idAsistente) {
        this.idAsistente = idAsistente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsistenteEntity that = (AsistenteEntity) o;
        return Objects.equals(idAsistente, that.idAsistente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAsistente);
    }

    public Collection<ConversacionEntity> getConversacionsByIdAsistente() {
        return conversacionsByIdAsistente;
    }

    public void setConversacionsByIdAsistente(Collection<ConversacionEntity> conversacionsByIdAsistente) {
        this.conversacionsByIdAsistente = conversacionsByIdAsistente;
    }
}
