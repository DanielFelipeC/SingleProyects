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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "TablePeter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TablePeter.findAll", query = "SELECT t FROM TablePeter t"),
    @NamedQuery(name = "TablePeter.findByLlavePrimaria", query = "SELECT t FROM TablePeter t WHERE t.llavePrimaria = :llavePrimaria"),
    @NamedQuery(name = "TablePeter.findByTextoUno", query = "SELECT t FROM TablePeter t WHERE t.textoUno = :textoUno"),
    @NamedQuery(name = "TablePeter.findByTextoDos", query = "SELECT t FROM TablePeter t WHERE t.textoDos = :textoDos"),
    @NamedQuery(name = "TablePeter.findByTextoTres", query = "SELECT t FROM TablePeter t WHERE t.textoTres = :textoTres")})
public class TablePeter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "llavePrimaria")
    private Long llavePrimaria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TextoUno")
    private String textoUno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TextoDos")
    private String textoDos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TextoTres")
    private String textoTres;

    public TablePeter() {
    }

    public TablePeter(Long llavePrimaria) {
        this.llavePrimaria = llavePrimaria;
    }

    public TablePeter(Long llavePrimaria, String textoUno, String textoDos, String textoTres) {
        this.llavePrimaria = llavePrimaria;
        this.textoUno = textoUno;
        this.textoDos = textoDos;
        this.textoTres = textoTres;
    }

    public Long getLlavePrimaria() {
        return llavePrimaria;
    }

    public void setLlavePrimaria(Long llavePrimaria) {
        this.llavePrimaria = llavePrimaria;
    }

    public String getTextoUno() {
        return textoUno;
    }

    public void setTextoUno(String textoUno) {
        this.textoUno = textoUno;
    }

    public String getTextoDos() {
        return textoDos;
    }

    public void setTextoDos(String textoDos) {
        this.textoDos = textoDos;
    }

    public String getTextoTres() {
        return textoTres;
    }

    public void setTextoTres(String textoTres) {
        this.textoTres = textoTres;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (llavePrimaria != null ? llavePrimaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablePeter)) {
            return false;
        }
        TablePeter other = (TablePeter) object;
        if ((this.llavePrimaria == null && other.llavePrimaria != null) || (this.llavePrimaria != null && !this.llavePrimaria.equals(other.llavePrimaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TablePeter[ llavePrimaria=" + llavePrimaria + " ]";
    }
    
}
