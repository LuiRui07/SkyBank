package com.example.skybank.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "asistente", schema = "skybank", catalog = "")
public class AsistenteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idasistente", nullable = false)
    private int idasistente;
    @OneToMany(mappedBy = "asistenteByIdasistente")
    private List<ConversacionEntity> conversacionsByIdasistente;

    public int getIdasistente() {
        return idasistente;
    }

    public void setIdasistente(int idasistente) {
        this.idasistente = idasistente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsistenteEntity that = (AsistenteEntity) o;
        return idasistente == that.idasistente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idasistente);
    }

    public List<ConversacionEntity> getConversacionsByIdasistente() {
        return conversacionsByIdasistente;
    }

    public void setConversacionsByIdasistente(List<ConversacionEntity> conversacionsByIdasistente) {
        this.conversacionsByIdasistente = conversacionsByIdasistente;
    }
}
