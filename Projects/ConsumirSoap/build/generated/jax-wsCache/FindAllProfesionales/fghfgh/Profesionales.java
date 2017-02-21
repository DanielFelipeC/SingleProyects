
package fghfgh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para profesionales complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="profesionales"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idProfesional" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="prfApellidos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="prfNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="prfNroDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "profesionales", propOrder = {
    "idProfesional",
    "prfApellidos",
    "prfNombre",
    "prfNroDocumento"
})
public class Profesionales {

    protected Long idProfesional;
    protected String prfApellidos;
    protected String prfNombre;
    protected String prfNroDocumento;

    /**
     * Obtiene el valor de la propiedad idProfesional.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdProfesional() {
        return idProfesional;
    }

    /**
     * Define el valor de la propiedad idProfesional.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdProfesional(Long value) {
        this.idProfesional = value;
    }

    /**
     * Obtiene el valor de la propiedad prfApellidos.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrfApellidos() {
        return prfApellidos;
    }

    /**
     * Define el valor de la propiedad prfApellidos.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrfApellidos(String value) {
        this.prfApellidos = value;
    }

    /**
     * Obtiene el valor de la propiedad prfNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrfNombre() {
        return prfNombre;
    }

    /**
     * Define el valor de la propiedad prfNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrfNombre(String value) {
        this.prfNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad prfNroDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrfNroDocumento() {
        return prfNroDocumento;
    }

    /**
     * Define el valor de la propiedad prfNroDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrfNroDocumento(String value) {
        this.prfNroDocumento = value;
    }

}
