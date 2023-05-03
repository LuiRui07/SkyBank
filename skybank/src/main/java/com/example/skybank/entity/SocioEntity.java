package com.example.skybank.entity;

import com.example.skybank.dto.Socio;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "socio", schema = "skybank", catalog = "")
public class SocioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nif", nullable = false, length = 45)
    private String nif;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "apellido1", nullable = false, length = 100)
    private String apellido1;
    @Basic
    @Column(name = "apellido2", nullable = true, length = 100)
    private String apellido2;
    @Basic
    @Column(name = "fechanacimiento", nullable = false)
    private Date fechanacimiento;
    @Basic
    @Column(name = "bloqueado", nullable = false)
    private int bloqueado;
    @Basic
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Basic
    @Column(name = "calle", nullable = false, length = 100)
    private String calle;
    @Basic
    @Column(name = "numero", nullable = false)
    private int numero;
    @Basic
    @Column(name = "planta", nullable = false)
    private int planta;
    @Basic
    @Column(name = "ciudad", nullable = false, length = 100)
    private String ciudad;
    @Basic
    @Column(name = "pais", nullable = false, length = 100)
    private String pais;
    @Basic
    @Column(name = "region", nullable = true, length = 100)
    private String region;
    @Basic
    @Column(name = "cp", nullable = false)
    private int cp;
    @ManyToOne
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa", nullable = false)
    private EmpresaEntity empresaByIdempresa;

    @Basic
    @Column(name = "solicituddesbloqueo", nullable = false)
    private int solicituddesbloqueo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public int getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(int bloqueado) {
        this.bloqueado = bloqueado;
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

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocioEntity that = (SocioEntity) o;
        return id == that.id && bloqueado == that.bloqueado && numero == that.numero && planta == that.planta && cp == that.cp && Objects.equals(nif, that.nif) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(fechanacimiento, that.fechanacimiento) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(calle, that.calle) && Objects.equals(ciudad, that.ciudad) && Objects.equals(pais, that.pais) && Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nif, nombre, apellido1, apellido2, fechanacimiento, bloqueado, email, password, calle, numero, planta, ciudad, pais, region, cp);
    }

    public EmpresaEntity getEmpresaByIdempresa() {
        return empresaByIdempresa;
    }

    public void setEmpresaByIdempresa(EmpresaEntity empresaByIdempresa) {
        this.empresaByIdempresa = empresaByIdempresa;
    }

    public int getSolicituddesbloqueo() {
        return solicituddesbloqueo;
    }

    public void setSolicituddesbloqueo(int solicituddesbloqueo) {
        this.solicituddesbloqueo = solicituddesbloqueo;
    }

    public Socio toDTO(){
        Socio socio = new Socio();
        socio.setId(id);
        socio.setNif(nif);
        socio.setNombre(nombre);
        socio.setApellido1(apellido1);
        socio.setApellido2(apellido2);
        socio.setFechanacimiento(fechanacimiento);
        socio.setBloqueado(bloqueado);
        socio.setEmail(email);
        socio.setPassword(password);
        socio.setCalle(calle);
        socio.setNumero(numero);
        socio.setPlanta(planta);
        socio.setCiudad(ciudad);
        socio.setPais(pais);
        socio.setRegion(region);
        socio.setCp(cp);
        socio.setEmpresa(empresaByIdempresa.toDTO());
        socio.setSolicituddesbloqueo(solicituddesbloqueo);

        return socio;

    }
}
