/*
@author: Pablo García Platero
*/

package com.example.skybank.entity;

import com.example.skybank.dto.AsistenteDTO;

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
    @Basic
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @OneToMany(mappedBy = "asistenteByIdasistente")
    private List<ConversacionEntity> conversacionsByIdasistente;

    public int getIdasistente() {
        return idasistente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public AsistenteDTO toDTO(){
        AsistenteDTO asisDTO = new AsistenteDTO();
        asisDTO.setIdasistente(this.idasistente);
        asisDTO.setEmail(this.email);
        asisDTO.setPassword(this.password);

        return asisDTO;
    }
}
