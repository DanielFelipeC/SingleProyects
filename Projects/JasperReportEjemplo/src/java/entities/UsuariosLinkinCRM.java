/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dfcastellanosc
 */
@Entity
@Table(name = "usuariosLinkinCRM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuariosLinkinCRM.findAll", query = "SELECT u FROM UsuariosLinkinCRM u"),
    @NamedQuery(name = "UsuariosLinkinCRM.findByIDUsuarios", query = "SELECT u FROM UsuariosLinkinCRM u WHERE u.iDUsuarios = :iDUsuarios"),
    @NamedQuery(name = "UsuariosLinkinCRM.findByLogin", query = "SELECT u FROM UsuariosLinkinCRM u WHERE u.login = :login"),
    @NamedQuery(name = "UsuariosLinkinCRM.findByPassword", query = "SELECT u FROM UsuariosLinkinCRM u WHERE u.password = :password"),
    @NamedQuery(name = "UsuariosLinkinCRM.findByNombreUsuario", query = "SELECT u FROM UsuariosLinkinCRM u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "UsuariosLinkinCRM.findByEmail", query = "SELECT u FROM UsuariosLinkinCRM u WHERE u.email = :email"),
    @NamedQuery(name = "UsuariosLinkinCRM.findByTelefono", query = "SELECT u FROM UsuariosLinkinCRM u WHERE u.telefono = :telefono")})
public class UsuariosLinkinCRM implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUsuarios")
    private Long iDUsuarios;
    @Size(max = 50)
    @Column(name = "Login")
    private String login;
    @Size(max = 50)
    @Column(name = "Password")
    private String password;
    @Size(max = 50)
    @Column(name = "NombreUsuario")
    private String nombreUsuario;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "Email")
    private String email;
    @Lob
    @Column(name = "Foto")
    private byte[] foto;
    @Size(max = 50)
    @Column(name = "Telefono")
    private String telefono;

    public UsuariosLinkinCRM() {
    }

    public UsuariosLinkinCRM(Long iDUsuarios) {
        this.iDUsuarios = iDUsuarios;
    }

    public Long getIDUsuarios() {
        return iDUsuarios;
    }

    public void setIDUsuarios(Long iDUsuarios) {
        this.iDUsuarios = iDUsuarios;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDUsuarios != null ? iDUsuarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosLinkinCRM)) {
            return false;
        }
        UsuariosLinkinCRM other = (UsuariosLinkinCRM) object;
        if ((this.iDUsuarios == null && other.iDUsuarios != null) || (this.iDUsuarios != null && !this.iDUsuarios.equals(other.iDUsuarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UsuariosLinkinCRM[ iDUsuarios=" + iDUsuarios + " ]";
    }
    
}
