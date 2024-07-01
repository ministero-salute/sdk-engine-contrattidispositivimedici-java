//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v3.0.0 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2022.12.15 alle 02:28:27 PM CET 
//


/* SPDX-License-Identifier: BSD-3-Clause */
 
package it.mds.sdk.flusso.ct2.tracciato.output;

import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="REGIONE"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="AS" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="OPERAZIONE" maxOccurs="unbounded"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="CONTRATTO" maxOccurs="unbounded"&gt;
 *                                         &lt;complexType&gt;
 *                                           &lt;complexContent&gt;
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                               &lt;sequence&gt;
 *                                                 &lt;element name="DISPOSITIVO" maxOccurs="unbounded"&gt;
 *                                                   &lt;complexType&gt;
 *                                                     &lt;complexContent&gt;
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                                         &lt;attribute name="tipo_dispositivo" use="required" type="{http://eng.com/rdm/xml/model}TypeDispositivo" /&gt;
 *                                                         &lt;attribute name="num_rep" use="required" type="{http://eng.com/rdm/xml/model}TypeNumRepertorio" /&gt;
 *                                                         &lt;attribute name="prezzo_agg" use="required" type="{http://eng.com/rdm/xml/model}TypeImporto" /&gt;
 *                                                         &lt;attribute name="qta_agg" use="required" type="{http://eng.com/rdm/xml/model}TypeQuantita" /&gt;
 *                                                         &lt;attribute name="qta_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeQuantita" /&gt;
 *                                                         &lt;attribute name="num_pz" type="{http://eng.com/rdm/xml/model}TypePezzi" /&gt;
 *                                                         &lt;attribute name="den_forn" type="{http://eng.com/rdm/xml/model}TypeStr100" /&gt;
 *                                                         &lt;attribute name="piva_forn" type="{http://eng.com/rdm/xml/model}TypeStr15" /&gt;
 *                                                         &lt;attribute name="iva" use="required" type="{http://eng.com/rdm/xml/model}TypeIVA" /&gt;
 *                                                         &lt;attribute name="flg_ser_acc" type="{http://eng.com/rdm/xml/model}TypeFlag" /&gt;
 *                                                         &lt;attribute name="flg_conto_dep" use="required" type="{http://eng.com/rdm/xml/model}TypeFlag" /&gt;
 *                                                         &lt;attribute name="progr_riga" type="{http://eng.com/rdm/xml/model}TypeNum5" /&gt;
 *                                                         &lt;attribute name="cod_mod_ce" type="{http://eng.com/rdm/xml/model}TypeStr6" /&gt;
 *                                                       &lt;/restriction&gt;
 *                                                     &lt;/complexContent&gt;
 *                                                   &lt;/complexType&gt;
 *                                                 &lt;/element&gt;
 *                                               &lt;/sequence&gt;
 *                                               &lt;attribute name="num_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeStr40" /&gt;
 *                                               &lt;attribute name="anno" use="required"&gt;
 *                                                 &lt;simpleType&gt;
 *                                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                                     &lt;pattern value="[2][0][0-9]{2}"/&gt;
 *                                                   &lt;/restriction&gt;
 *                                                 &lt;/simpleType&gt;
 *                                               &lt;/attribute&gt;
 *                                               &lt;attribute name="mese" use="required"&gt;
 *                                                 &lt;simpleType&gt;
 *                                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                                     &lt;pattern value="0[1-9]|1[012]"/&gt;
 *                                                   &lt;/restriction&gt;
 *                                                 &lt;/simpleType&gt;
 *                                               &lt;/attribute&gt;
 *                                               &lt;attribute name="giorno" use="required"&gt;
 *                                                 &lt;simpleType&gt;
 *                                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                                     &lt;pattern value="0[1-9]|[12][0-9]|3[01]"/&gt;
 *                                                   &lt;/restriction&gt;
 *                                                 &lt;/simpleType&gt;
 *                                               &lt;/attribute&gt;
 *                                               &lt;attribute name="durata_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeDurata" /&gt;
 *                                               &lt;attribute name="tipo_contr"&gt;
 *                                                 &lt;simpleType&gt;
 *                                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                                     &lt;enumeration value="CA"/&gt;
 *                                                     &lt;enumeration value="CB"/&gt;
 *                                                     &lt;enumeration value="CC"/&gt;
 *                                                     &lt;enumeration value="CD"/&gt;
 *                                                     &lt;enumeration value="CO"/&gt;
 *                                                     &lt;enumeration value="CS"/&gt;
 *                                                     &lt;enumeration value="LF"/&gt;
 *                                                   &lt;/restriction&gt;
 *                                                 &lt;/simpleType&gt;
 *                                               &lt;/attribute&gt;
 *                                               &lt;attribute name="forma_neg"&gt;
 *                                                 &lt;simpleType&gt;
 *                                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                                     &lt;enumeration value="PA"/&gt;
 *                                                     &lt;enumeration value="PR"/&gt;
 *                                                     &lt;enumeration value="PS"/&gt;
 *                                                     &lt;enumeration value="PP"/&gt;
 *                                                     &lt;enumeration value="CF"/&gt;
 *                                                     &lt;enumeration value="AD"/&gt;
 *                                                     &lt;enumeration value="NC"/&gt;
 *                                                   &lt;/restriction&gt;
 *                                                 &lt;/simpleType&gt;
 *                                               &lt;/attribute&gt;
 *                                               &lt;attribute name="amb_val"&gt;
 *                                                 &lt;simpleType&gt;
 *                                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                                     &lt;enumeration value="1"/&gt;
 *                                                     &lt;enumeration value="2"/&gt;
 *                                                     &lt;enumeration value="3"/&gt;
 *                                                     &lt;enumeration value="4"/&gt;
 *                                                   &lt;/restriction&gt;
 *                                                 &lt;/simpleType&gt;
 *                                               &lt;/attribute&gt;
 *                                               &lt;attribute name="cod_cig" type="{http://eng.com/rdm/xml/model}TypeCig" /&gt;
 *                                             &lt;/restriction&gt;
 *                                           &lt;/complexContent&gt;
 *                                         &lt;/complexType&gt;
 *                                       &lt;/element&gt;
 *                                     &lt;/sequence&gt;
 *                                     &lt;attribute name="tipo_op" use="required" type="{http://eng.com/rdm/xml/model}TypeOperazione" /&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="cod_as" use="required"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                 &lt;pattern value="[0-9]{6}"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="cod_reg" use="required" type="{http://eng.com/rdm/xml/model}TypeAllegatoA" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CANCELLAZIONE_MASSIVA_DATI"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="REGIONE"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="AS" maxOccurs="unbounded"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="CONTR" maxOccurs="unbounded" minOccurs="0"&gt;
 *                                         &lt;complexType&gt;
 *                                           &lt;complexContent&gt;
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                               &lt;attribute name="num_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeStr40" /&gt;
 *                                             &lt;/restriction&gt;
 *                                           &lt;/complexContent&gt;
 *                                         &lt;/complexType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="PERIODO" maxOccurs="unbounded" minOccurs="0"&gt;
 *                                         &lt;complexType&gt;
 *                                           &lt;complexContent&gt;
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                               &lt;attribute name="anno" use="required"&gt;
 *                                                 &lt;simpleType&gt;
 *                                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                                     &lt;pattern value="[2][0][0-9]{2}"/&gt;
 *                                                   &lt;/restriction&gt;
 *                                                 &lt;/simpleType&gt;
 *                                               &lt;/attribute&gt;
 *                                               &lt;attribute name="mese" use="required"&gt;
 *                                                 &lt;simpleType&gt;
 *                                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                                     &lt;pattern value="0[1-9]|1[012]"/&gt;
 *                                                   &lt;/restriction&gt;
 *                                                 &lt;/simpleType&gt;
 *                                               &lt;/attribute&gt;
 *                                             &lt;/restriction&gt;
 *                                           &lt;/complexContent&gt;
 *                                         &lt;/complexType&gt;
 *                                       &lt;/element&gt;
 *                                     &lt;/sequence&gt;
 *                                     &lt;attribute name="cod_as" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;pattern value="[0-9]{6}"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="cod_reg" use="required" type="{http://eng.com/rdm/xml/model}TypeAllegatoA" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "regione",
    "cancellazionemassivadati"
})
@XmlRootElement(name = "dataroot", namespace = "http://eng.com/rdm/xml/model")
public class Dataroot {

    @XmlElement(name = "REGIONE", namespace = "http://eng.com/rdm/xml/model")
    protected REGIONE regione;
    @XmlElement(name = "CANCELLAZIONE_MASSIVA_DATI", namespace ="http://eng.com/rdm/xml/model")
    protected CANCELLAZIONEMASSIVADATI cancellazionemassivadati;

    /**
     * Recupera il valore della proprietà regione.
     * 
     * @return
     *     possible object is
     *     {@link REGIONE }
     *     
     */
    public REGIONE getREGIONE() {
        return regione;
    }

    /**
     * Imposta il valore della proprietà regione.
     * 
     * @param value
     *     allowed object is
     *     {@link REGIONE }
     *     
     */
    public void setREGIONE(REGIONE value) {
        this.regione = value;
    }

    /**
     * Recupera il valore della proprietà cancellazionemassivadati.
     * 
     * @return
     *     possible object is
     *     {@link CANCELLAZIONEMASSIVADATI }
     *     
     */
    public CANCELLAZIONEMASSIVADATI getCANCELLAZIONEMASSIVADATI() {
        return cancellazionemassivadati;
    }

    /**
     * Imposta il valore della proprietà cancellazionemassivadati.
     * 
     * @param value
     *     allowed object is
     *     {@link CANCELLAZIONEMASSIVADATI }
     *     
     */
    public void setCANCELLAZIONEMASSIVADATI(CANCELLAZIONEMASSIVADATI value) {
        this.cancellazionemassivadati = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="REGIONE"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="AS" maxOccurs="unbounded"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="CONTR" maxOccurs="unbounded" minOccurs="0"&gt;
     *                               &lt;complexType&gt;
     *                                 &lt;complexContent&gt;
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                     &lt;attribute name="num_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeStr40" /&gt;
     *                                   &lt;/restriction&gt;
     *                                 &lt;/complexContent&gt;
     *                               &lt;/complexType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="PERIODO" maxOccurs="unbounded" minOccurs="0"&gt;
     *                               &lt;complexType&gt;
     *                                 &lt;complexContent&gt;
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                     &lt;attribute name="anno" use="required"&gt;
     *                                       &lt;simpleType&gt;
     *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                           &lt;pattern value="[2][0][0-9]{2}"/&gt;
     *                                         &lt;/restriction&gt;
     *                                       &lt;/simpleType&gt;
     *                                     &lt;/attribute&gt;
     *                                     &lt;attribute name="mese" use="required"&gt;
     *                                       &lt;simpleType&gt;
     *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                           &lt;pattern value="0[1-9]|1[012]"/&gt;
     *                                         &lt;/restriction&gt;
     *                                       &lt;/simpleType&gt;
     *                                     &lt;/attribute&gt;
     *                                   &lt;/restriction&gt;
     *                                 &lt;/complexContent&gt;
     *                               &lt;/complexType&gt;
     *                             &lt;/element&gt;
     *                           &lt;/sequence&gt;
     *                           &lt;attribute name="cod_as" use="required"&gt;
     *                             &lt;simpleType&gt;
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                 &lt;pattern value="[0-9]{6}"/&gt;
     *                               &lt;/restriction&gt;
     *                             &lt;/simpleType&gt;
     *                           &lt;/attribute&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="cod_reg" use="required" type="{http://eng.com/rdm/xml/model}TypeAllegatoA" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "regione"
    })
    public static class CANCELLAZIONEMASSIVADATI {

        @XmlElement(name = "REGIONE", namespace ="http://eng.com/rdm/xml/model", required = true)
        protected REGIONE regione;

        /**
         * Recupera il valore della proprietà regione.
         * 
         * @return
         *     possible object is
         *     {@link REGIONE }
         *     
         */
        public REGIONE getREGIONE() {
            return regione;
        }

        /**
         * Imposta il valore della proprietà regione.
         * 
         * @param value
         *     allowed object is
         *     {@link REGIONE }
         *     
         */
        public void setREGIONE(REGIONE value) {
            this.regione = value;
        }


        /**
         * <p>Classe Java per anonymous complex type.
         * 
         * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="AS" maxOccurs="unbounded"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="CONTR" maxOccurs="unbounded" minOccurs="0"&gt;
         *                     &lt;complexType&gt;
         *                       &lt;complexContent&gt;
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                           &lt;attribute name="num_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeStr40" /&gt;
         *                         &lt;/restriction&gt;
         *                       &lt;/complexContent&gt;
         *                     &lt;/complexType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="PERIODO" maxOccurs="unbounded" minOccurs="0"&gt;
         *                     &lt;complexType&gt;
         *                       &lt;complexContent&gt;
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                           &lt;attribute name="anno" use="required"&gt;
         *                             &lt;simpleType&gt;
         *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                                 &lt;pattern value="[2][0][0-9]{2}"/&gt;
         *                               &lt;/restriction&gt;
         *                             &lt;/simpleType&gt;
         *                           &lt;/attribute&gt;
         *                           &lt;attribute name="mese" use="required"&gt;
         *                             &lt;simpleType&gt;
         *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                                 &lt;pattern value="0[1-9]|1[012]"/&gt;
         *                               &lt;/restriction&gt;
         *                             &lt;/simpleType&gt;
         *                           &lt;/attribute&gt;
         *                         &lt;/restriction&gt;
         *                       &lt;/complexContent&gt;
         *                     &lt;/complexType&gt;
         *                   &lt;/element&gt;
         *                 &lt;/sequence&gt;
         *                 &lt;attribute name="cod_as" use="required"&gt;
         *                   &lt;simpleType&gt;
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                       &lt;pattern value="[0-9]{6}"/&gt;
         *                     &lt;/restriction&gt;
         *                   &lt;/simpleType&gt;
         *                 &lt;/attribute&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *       &lt;attribute name="cod_reg" use="required" type="{http://eng.com/rdm/xml/model}TypeAllegatoA" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "as"
        })
        public static class REGIONE {

            @XmlElement(name = "AS", required = true)
            protected List<AS> as;
            @XmlAttribute(name = "cod_reg", required = true)
            protected String codReg;

            /**
             * Gets the value of the as property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the Jakarta XML Binding object.
             * This is why there is not a <CODE>set</CODE> method for the as property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getAS().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link AS }
             * 
             * 
             */
            public List<AS> getAS() {
                if (as == null) {
                    as = new ArrayList<AS>();
                }
                return this.as;
            }

            /**
             * Recupera il valore della proprietà codReg.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodReg() {
                return codReg;
            }

            /**
             * Imposta il valore della proprietà codReg.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodReg(String value) {
                this.codReg = value;
            }


            /**
             * <p>Classe Java per anonymous complex type.
             * 
             * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="CONTR" maxOccurs="unbounded" minOccurs="0"&gt;
             *           &lt;complexType&gt;
             *             &lt;complexContent&gt;
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                 &lt;attribute name="num_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeStr40" /&gt;
             *               &lt;/restriction&gt;
             *             &lt;/complexContent&gt;
             *           &lt;/complexType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="PERIODO" maxOccurs="unbounded" minOccurs="0"&gt;
             *           &lt;complexType&gt;
             *             &lt;complexContent&gt;
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                 &lt;attribute name="anno" use="required"&gt;
             *                   &lt;simpleType&gt;
             *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *                       &lt;pattern value="[2][0][0-9]{2}"/&gt;
             *                     &lt;/restriction&gt;
             *                   &lt;/simpleType&gt;
             *                 &lt;/attribute&gt;
             *                 &lt;attribute name="mese" use="required"&gt;
             *                   &lt;simpleType&gt;
             *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *                       &lt;pattern value="0[1-9]|1[012]"/&gt;
             *                     &lt;/restriction&gt;
             *                   &lt;/simpleType&gt;
             *                 &lt;/attribute&gt;
             *               &lt;/restriction&gt;
             *             &lt;/complexContent&gt;
             *           &lt;/complexType&gt;
             *         &lt;/element&gt;
             *       &lt;/sequence&gt;
             *       &lt;attribute name="cod_as" use="required"&gt;
             *         &lt;simpleType&gt;
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *             &lt;pattern value="[0-9]{6}"/&gt;
             *           &lt;/restriction&gt;
             *         &lt;/simpleType&gt;
             *       &lt;/attribute&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "contr",
                "periodo"
            })
            public static class AS {

                @XmlElement(name = "CONTR")
                protected List<CONTR> contr;
                @XmlElement(name = "PERIODO")
                protected List<PERIODO> periodo;
                @XmlAttribute(name = "cod_as", required = true)
                protected String codAs;

                /**
                 * Gets the value of the contr property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the Jakarta XML Binding object.
                 * This is why there is not a <CODE>set</CODE> method for the contr property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getCONTR().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link CONTR }
                 * 
                 * 
                 */
                public List<CONTR> getCONTR() {
                    if (contr == null) {
                        contr = new ArrayList<CONTR>();
                    }
                    return this.contr;
                }

                /**
                 * Gets the value of the periodo property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the Jakarta XML Binding object.
                 * This is why there is not a <CODE>set</CODE> method for the periodo property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getPERIODO().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link PERIODO }
                 * 
                 * 
                 */
                public List<PERIODO> getPERIODO() {
                    if (periodo == null) {
                        periodo = new ArrayList<PERIODO>();
                    }
                    return this.periodo;
                }

                /**
                 * Recupera il valore della proprietà codAs.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCodAs() {
                    return codAs;
                }

                /**
                 * Imposta il valore della proprietà codAs.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCodAs(String value) {
                    this.codAs = value;
                }


                /**
                 * <p>Classe Java per anonymous complex type.
                 * 
                 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
                 * 
                 * <pre>
                 * &lt;complexType&gt;
                 *   &lt;complexContent&gt;
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                 *       &lt;attribute name="num_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeStr40" /&gt;
                 *     &lt;/restriction&gt;
                 *   &lt;/complexContent&gt;
                 * &lt;/complexType&gt;
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class CONTR {

                    @XmlAttribute(name = "num_contr", required = true)
                    protected String numContr;

                    /**
                     * Recupera il valore della proprietà numContr.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getNumContr() {
                        return numContr;
                    }

                    /**
                     * Imposta il valore della proprietà numContr.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setNumContr(String value) {
                        this.numContr = value;
                    }

                }


                /**
                 * <p>Classe Java per anonymous complex type.
                 * 
                 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
                 * 
                 * <pre>
                 * &lt;complexType&gt;
                 *   &lt;complexContent&gt;
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                 *       &lt;attribute name="anno" use="required"&gt;
                 *         &lt;simpleType&gt;
                 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
                 *             &lt;pattern value="[2][0][0-9]{2}"/&gt;
                 *           &lt;/restriction&gt;
                 *         &lt;/simpleType&gt;
                 *       &lt;/attribute&gt;
                 *       &lt;attribute name="mese" use="required"&gt;
                 *         &lt;simpleType&gt;
                 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
                 *             &lt;pattern value="0[1-9]|1[012]"/&gt;
                 *           &lt;/restriction&gt;
                 *         &lt;/simpleType&gt;
                 *       &lt;/attribute&gt;
                 *     &lt;/restriction&gt;
                 *   &lt;/complexContent&gt;
                 * &lt;/complexType&gt;
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class PERIODO {

                    @XmlAttribute(name = "anno", required = true)
                    protected String anno;
                    @XmlAttribute(name = "mese", required = true)
                    protected String mese;

                    /**
                     * Recupera il valore della proprietà anno.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getAnno() {
                        return anno;
                    }

                    /**
                     * Imposta il valore della proprietà anno.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setAnno(String value) {
                        this.anno = value;
                    }

                    /**
                     * Recupera il valore della proprietà mese.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getMese() {
                        return mese;
                    }

                    /**
                     * Imposta il valore della proprietà mese.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setMese(String value) {
                        this.mese = value;
                    }

                }

            }

        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="AS" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="OPERAZIONE" maxOccurs="unbounded"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="CONTRATTO" maxOccurs="unbounded"&gt;
     *                               &lt;complexType&gt;
     *                                 &lt;complexContent&gt;
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                     &lt;sequence&gt;
     *                                       &lt;element name="DISPOSITIVO" maxOccurs="unbounded"&gt;
     *                                         &lt;complexType&gt;
     *                                           &lt;complexContent&gt;
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                               &lt;attribute name="tipo_dispositivo" use="required" type="{http://eng.com/rdm/xml/model}TypeDispositivo" /&gt;
     *                                               &lt;attribute name="num_rep" use="required" type="{http://eng.com/rdm/xml/model}TypeNumRepertorio" /&gt;
     *                                               &lt;attribute name="prezzo_agg" use="required" type="{http://eng.com/rdm/xml/model}TypeImporto" /&gt;
     *                                               &lt;attribute name="qta_agg" use="required" type="{http://eng.com/rdm/xml/model}TypeQuantita" /&gt;
     *                                               &lt;attribute name="qta_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeQuantita" /&gt;
     *                                               &lt;attribute name="num_pz" type="{http://eng.com/rdm/xml/model}TypePezzi" /&gt;
     *                                               &lt;attribute name="den_forn" type="{http://eng.com/rdm/xml/model}TypeStr100" /&gt;
     *                                               &lt;attribute name="piva_forn" type="{http://eng.com/rdm/xml/model}TypeStr15" /&gt;
     *                                               &lt;attribute name="iva" use="required" type="{http://eng.com/rdm/xml/model}TypeIVA" /&gt;
     *                                               &lt;attribute name="flg_ser_acc" type="{http://eng.com/rdm/xml/model}TypeFlag" /&gt;
     *                                               &lt;attribute name="flg_conto_dep" use="required" type="{http://eng.com/rdm/xml/model}TypeFlag" /&gt;
     *                                               &lt;attribute name="progr_riga" type="{http://eng.com/rdm/xml/model}TypeNum5" /&gt;
     *                                               &lt;attribute name="cod_mod_ce" type="{http://eng.com/rdm/xml/model}TypeStr6" /&gt;
     *                                             &lt;/restriction&gt;
     *                                           &lt;/complexContent&gt;
     *                                         &lt;/complexType&gt;
     *                                       &lt;/element&gt;
     *                                     &lt;/sequence&gt;
     *                                     &lt;attribute name="num_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeStr40" /&gt;
     *                                     &lt;attribute name="anno" use="required"&gt;
     *                                       &lt;simpleType&gt;
     *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                           &lt;pattern value="[2][0][0-9]{2}"/&gt;
     *                                         &lt;/restriction&gt;
     *                                       &lt;/simpleType&gt;
     *                                     &lt;/attribute&gt;
     *                                     &lt;attribute name="mese" use="required"&gt;
     *                                       &lt;simpleType&gt;
     *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                           &lt;pattern value="0[1-9]|1[012]"/&gt;
     *                                         &lt;/restriction&gt;
     *                                       &lt;/simpleType&gt;
     *                                     &lt;/attribute&gt;
     *                                     &lt;attribute name="giorno" use="required"&gt;
     *                                       &lt;simpleType&gt;
     *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                           &lt;pattern value="0[1-9]|[12][0-9]|3[01]"/&gt;
     *                                         &lt;/restriction&gt;
     *                                       &lt;/simpleType&gt;
     *                                     &lt;/attribute&gt;
     *                                     &lt;attribute name="durata_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeDurata" /&gt;
     *                                     &lt;attribute name="tipo_contr"&gt;
     *                                       &lt;simpleType&gt;
     *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                           &lt;enumeration value="CA"/&gt;
     *                                           &lt;enumeration value="CB"/&gt;
     *                                           &lt;enumeration value="CC"/&gt;
     *                                           &lt;enumeration value="CD"/&gt;
     *                                           &lt;enumeration value="CO"/&gt;
     *                                           &lt;enumeration value="CS"/&gt;
     *                                           &lt;enumeration value="LF"/&gt;
     *                                         &lt;/restriction&gt;
     *                                       &lt;/simpleType&gt;
     *                                     &lt;/attribute&gt;
     *                                     &lt;attribute name="forma_neg"&gt;
     *                                       &lt;simpleType&gt;
     *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                           &lt;enumeration value="PA"/&gt;
     *                                           &lt;enumeration value="PR"/&gt;
     *                                           &lt;enumeration value="PS"/&gt;
     *                                           &lt;enumeration value="PP"/&gt;
     *                                           &lt;enumeration value="CF"/&gt;
     *                                           &lt;enumeration value="AD"/&gt;
     *                                           &lt;enumeration value="NC"/&gt;
     *                                         &lt;/restriction&gt;
     *                                       &lt;/simpleType&gt;
     *                                     &lt;/attribute&gt;
     *                                     &lt;attribute name="amb_val"&gt;
     *                                       &lt;simpleType&gt;
     *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                           &lt;enumeration value="1"/&gt;
     *                                           &lt;enumeration value="2"/&gt;
     *                                           &lt;enumeration value="3"/&gt;
     *                                           &lt;enumeration value="4"/&gt;
     *                                         &lt;/restriction&gt;
     *                                       &lt;/simpleType&gt;
     *                                     &lt;/attribute&gt;
     *                                     &lt;attribute name="cod_cig" type="{http://eng.com/rdm/xml/model}TypeCig" /&gt;
     *                                   &lt;/restriction&gt;
     *                                 &lt;/complexContent&gt;
     *                               &lt;/complexType&gt;
     *                             &lt;/element&gt;
     *                           &lt;/sequence&gt;
     *                           &lt;attribute name="tipo_op" use="required" type="{http://eng.com/rdm/xml/model}TypeOperazione" /&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="cod_as" use="required"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                       &lt;pattern value="[0-9]{6}"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="cod_reg" use="required" type="{http://eng.com/rdm/xml/model}TypeAllegatoA" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "as"
    })
    public static class REGIONE {

        @XmlElement(name = "AS", namespace ="http://eng.com/rdm/xml/model", required = true)
        protected List<AS> as;
        @XmlAttribute(name = "cod_reg", required = true)
        protected String codReg;

        /**
         * Gets the value of the as property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the as property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAS().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AS }
         * 
         * 
         */
        public List<AS> getAS() {
            if (as == null) {
                as = new ArrayList<AS>();
            }
            return this.as;
        }

        /**
         * Recupera il valore della proprietà codReg.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodReg() {
            return codReg;
        }

        /**
         * Imposta il valore della proprietà codReg.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodReg(String value) {
            this.codReg = value;
        }


        /**
         * <p>Classe Java per anonymous complex type.
         * 
         * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="OPERAZIONE" maxOccurs="unbounded"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="CONTRATTO" maxOccurs="unbounded"&gt;
         *                     &lt;complexType&gt;
         *                       &lt;complexContent&gt;
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                           &lt;sequence&gt;
         *                             &lt;element name="DISPOSITIVO" maxOccurs="unbounded"&gt;
         *                               &lt;complexType&gt;
         *                                 &lt;complexContent&gt;
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                                     &lt;attribute name="tipo_dispositivo" use="required" type="{http://eng.com/rdm/xml/model}TypeDispositivo" /&gt;
         *                                     &lt;attribute name="num_rep" use="required" type="{http://eng.com/rdm/xml/model}TypeNumRepertorio" /&gt;
         *                                     &lt;attribute name="prezzo_agg" use="required" type="{http://eng.com/rdm/xml/model}TypeImporto" /&gt;
         *                                     &lt;attribute name="qta_agg" use="required" type="{http://eng.com/rdm/xml/model}TypeQuantita" /&gt;
         *                                     &lt;attribute name="qta_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeQuantita" /&gt;
         *                                     &lt;attribute name="num_pz" type="{http://eng.com/rdm/xml/model}TypePezzi" /&gt;
         *                                     &lt;attribute name="den_forn" type="{http://eng.com/rdm/xml/model}TypeStr100" /&gt;
         *                                     &lt;attribute name="piva_forn" type="{http://eng.com/rdm/xml/model}TypeStr15" /&gt;
         *                                     &lt;attribute name="iva" use="required" type="{http://eng.com/rdm/xml/model}TypeIVA" /&gt;
         *                                     &lt;attribute name="flg_ser_acc" type="{http://eng.com/rdm/xml/model}TypeFlag" /&gt;
         *                                     &lt;attribute name="flg_conto_dep" use="required" type="{http://eng.com/rdm/xml/model}TypeFlag" /&gt;
         *                                     &lt;attribute name="progr_riga" type="{http://eng.com/rdm/xml/model}TypeNum5" /&gt;
         *                                     &lt;attribute name="cod_mod_ce" type="{http://eng.com/rdm/xml/model}TypeStr6" /&gt;
         *                                   &lt;/restriction&gt;
         *                                 &lt;/complexContent&gt;
         *                               &lt;/complexType&gt;
         *                             &lt;/element&gt;
         *                           &lt;/sequence&gt;
         *                           &lt;attribute name="num_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeStr40" /&gt;
         *                           &lt;attribute name="anno" use="required"&gt;
         *                             &lt;simpleType&gt;
         *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                                 &lt;pattern value="[2][0][0-9]{2}"/&gt;
         *                               &lt;/restriction&gt;
         *                             &lt;/simpleType&gt;
         *                           &lt;/attribute&gt;
         *                           &lt;attribute name="mese" use="required"&gt;
         *                             &lt;simpleType&gt;
         *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                                 &lt;pattern value="0[1-9]|1[012]"/&gt;
         *                               &lt;/restriction&gt;
         *                             &lt;/simpleType&gt;
         *                           &lt;/attribute&gt;
         *                           &lt;attribute name="giorno" use="required"&gt;
         *                             &lt;simpleType&gt;
         *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                                 &lt;pattern value="0[1-9]|[12][0-9]|3[01]"/&gt;
         *                               &lt;/restriction&gt;
         *                             &lt;/simpleType&gt;
         *                           &lt;/attribute&gt;
         *                           &lt;attribute name="durata_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeDurata" /&gt;
         *                           &lt;attribute name="tipo_contr"&gt;
         *                             &lt;simpleType&gt;
         *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                                 &lt;enumeration value="CA"/&gt;
         *                                 &lt;enumeration value="CB"/&gt;
         *                                 &lt;enumeration value="CC"/&gt;
         *                                 &lt;enumeration value="CD"/&gt;
         *                                 &lt;enumeration value="CO"/&gt;
         *                                 &lt;enumeration value="CS"/&gt;
         *                                 &lt;enumeration value="LF"/&gt;
         *                               &lt;/restriction&gt;
         *                             &lt;/simpleType&gt;
         *                           &lt;/attribute&gt;
         *                           &lt;attribute name="forma_neg"&gt;
         *                             &lt;simpleType&gt;
         *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                                 &lt;enumeration value="PA"/&gt;
         *                                 &lt;enumeration value="PR"/&gt;
         *                                 &lt;enumeration value="PS"/&gt;
         *                                 &lt;enumeration value="PP"/&gt;
         *                                 &lt;enumeration value="CF"/&gt;
         *                                 &lt;enumeration value="AD"/&gt;
         *                                 &lt;enumeration value="NC"/&gt;
         *                               &lt;/restriction&gt;
         *                             &lt;/simpleType&gt;
         *                           &lt;/attribute&gt;
         *                           &lt;attribute name="amb_val"&gt;
         *                             &lt;simpleType&gt;
         *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                                 &lt;enumeration value="1"/&gt;
         *                                 &lt;enumeration value="2"/&gt;
         *                                 &lt;enumeration value="3"/&gt;
         *                                 &lt;enumeration value="4"/&gt;
         *                               &lt;/restriction&gt;
         *                             &lt;/simpleType&gt;
         *                           &lt;/attribute&gt;
         *                           &lt;attribute name="cod_cig" type="{http://eng.com/rdm/xml/model}TypeCig" /&gt;
         *                         &lt;/restriction&gt;
         *                       &lt;/complexContent&gt;
         *                     &lt;/complexType&gt;
         *                   &lt;/element&gt;
         *                 &lt;/sequence&gt;
         *                 &lt;attribute name="tipo_op" use="required" type="{http://eng.com/rdm/xml/model}TypeOperazione" /&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *       &lt;attribute name="cod_as" use="required"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *             &lt;pattern value="[0-9]{6}"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "operazione"
        })
        public static class AS {

            @XmlElement(name = "OPERAZIONE", namespace ="http://eng.com/rdm/xml/model", required = true)
            protected List<OPERAZIONE> operazione;
            @XmlAttribute(name = "cod_as", required = true)
            protected String codAs;

            /**
             * Gets the value of the operazione property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the Jakarta XML Binding object.
             * This is why there is not a <CODE>set</CODE> method for the operazione property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getOPERAZIONE().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link OPERAZIONE }
             * 
             * 
             */
            public List<OPERAZIONE> getOPERAZIONE() {
                if (operazione == null) {
                    operazione = new ArrayList<OPERAZIONE>();
                }
                return this.operazione;
            }

            /**
             * Recupera il valore della proprietà codAs.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodAs() {
                return codAs;
            }

            /**
             * Imposta il valore della proprietà codAs.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodAs(String value) {
                this.codAs = value;
            }


            /**
             * <p>Classe Java per anonymous complex type.
             * 
             * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="CONTRATTO" maxOccurs="unbounded"&gt;
             *           &lt;complexType&gt;
             *             &lt;complexContent&gt;
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                 &lt;sequence&gt;
             *                   &lt;element name="DISPOSITIVO" maxOccurs="unbounded"&gt;
             *                     &lt;complexType&gt;
             *                       &lt;complexContent&gt;
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                           &lt;attribute name="tipo_dispositivo" use="required" type="{http://eng.com/rdm/xml/model}TypeDispositivo" /&gt;
             *                           &lt;attribute name="num_rep" use="required" type="{http://eng.com/rdm/xml/model}TypeNumRepertorio" /&gt;
             *                           &lt;attribute name="prezzo_agg" use="required" type="{http://eng.com/rdm/xml/model}TypeImporto" /&gt;
             *                           &lt;attribute name="qta_agg" use="required" type="{http://eng.com/rdm/xml/model}TypeQuantita" /&gt;
             *                           &lt;attribute name="qta_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeQuantita" /&gt;
             *                           &lt;attribute name="num_pz" type="{http://eng.com/rdm/xml/model}TypePezzi" /&gt;
             *                           &lt;attribute name="den_forn" type="{http://eng.com/rdm/xml/model}TypeStr100" /&gt;
             *                           &lt;attribute name="piva_forn" type="{http://eng.com/rdm/xml/model}TypeStr15" /&gt;
             *                           &lt;attribute name="iva" use="required" type="{http://eng.com/rdm/xml/model}TypeIVA" /&gt;
             *                           &lt;attribute name="flg_ser_acc" type="{http://eng.com/rdm/xml/model}TypeFlag" /&gt;
             *                           &lt;attribute name="flg_conto_dep" use="required" type="{http://eng.com/rdm/xml/model}TypeFlag" /&gt;
             *                           &lt;attribute name="progr_riga" type="{http://eng.com/rdm/xml/model}TypeNum5" /&gt;
             *                           &lt;attribute name="cod_mod_ce" type="{http://eng.com/rdm/xml/model}TypeStr6" /&gt;
             *                         &lt;/restriction&gt;
             *                       &lt;/complexContent&gt;
             *                     &lt;/complexType&gt;
             *                   &lt;/element&gt;
             *                 &lt;/sequence&gt;
             *                 &lt;attribute name="num_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeStr40" /&gt;
             *                 &lt;attribute name="anno" use="required"&gt;
             *                   &lt;simpleType&gt;
             *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *                       &lt;pattern value="[2][0][0-9]{2}"/&gt;
             *                     &lt;/restriction&gt;
             *                   &lt;/simpleType&gt;
             *                 &lt;/attribute&gt;
             *                 &lt;attribute name="mese" use="required"&gt;
             *                   &lt;simpleType&gt;
             *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *                       &lt;pattern value="0[1-9]|1[012]"/&gt;
             *                     &lt;/restriction&gt;
             *                   &lt;/simpleType&gt;
             *                 &lt;/attribute&gt;
             *                 &lt;attribute name="giorno" use="required"&gt;
             *                   &lt;simpleType&gt;
             *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *                       &lt;pattern value="0[1-9]|[12][0-9]|3[01]"/&gt;
             *                     &lt;/restriction&gt;
             *                   &lt;/simpleType&gt;
             *                 &lt;/attribute&gt;
             *                 &lt;attribute name="durata_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeDurata" /&gt;
             *                 &lt;attribute name="tipo_contr"&gt;
             *                   &lt;simpleType&gt;
             *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *                       &lt;enumeration value="CA"/&gt;
             *                       &lt;enumeration value="CB"/&gt;
             *                       &lt;enumeration value="CC"/&gt;
             *                       &lt;enumeration value="CD"/&gt;
             *                       &lt;enumeration value="CO"/&gt;
             *                       &lt;enumeration value="CS"/&gt;
             *                       &lt;enumeration value="LF"/&gt;
             *                     &lt;/restriction&gt;
             *                   &lt;/simpleType&gt;
             *                 &lt;/attribute&gt;
             *                 &lt;attribute name="forma_neg"&gt;
             *                   &lt;simpleType&gt;
             *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *                       &lt;enumeration value="PA"/&gt;
             *                       &lt;enumeration value="PR"/&gt;
             *                       &lt;enumeration value="PS"/&gt;
             *                       &lt;enumeration value="PP"/&gt;
             *                       &lt;enumeration value="CF"/&gt;
             *                       &lt;enumeration value="AD"/&gt;
             *                       &lt;enumeration value="NC"/&gt;
             *                     &lt;/restriction&gt;
             *                   &lt;/simpleType&gt;
             *                 &lt;/attribute&gt;
             *                 &lt;attribute name="amb_val"&gt;
             *                   &lt;simpleType&gt;
             *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *                       &lt;enumeration value="1"/&gt;
             *                       &lt;enumeration value="2"/&gt;
             *                       &lt;enumeration value="3"/&gt;
             *                       &lt;enumeration value="4"/&gt;
             *                     &lt;/restriction&gt;
             *                   &lt;/simpleType&gt;
             *                 &lt;/attribute&gt;
             *                 &lt;attribute name="cod_cig" type="{http://eng.com/rdm/xml/model}TypeCig" /&gt;
             *               &lt;/restriction&gt;
             *             &lt;/complexContent&gt;
             *           &lt;/complexType&gt;
             *         &lt;/element&gt;
             *       &lt;/sequence&gt;
             *       &lt;attribute name="tipo_op" use="required" type="{http://eng.com/rdm/xml/model}TypeOperazione" /&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "contratto"
            })
            public static class OPERAZIONE {

                @XmlElement(name = "CONTRATTO", namespace ="http://eng.com/rdm/xml/model", required = true)
                protected List<CONTRATTO> contratto;
                @XmlAttribute(name = "tipo_op", required = true)
                protected String tipoOp;

                /**
                 * Gets the value of the contratto property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the Jakarta XML Binding object.
                 * This is why there is not a <CODE>set</CODE> method for the contratto property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getCONTRATTO().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link CONTRATTO }
                 * 
                 * 
                 */
                public List<CONTRATTO> getCONTRATTO() {
                    if (contratto == null) {
                        contratto = new ArrayList<CONTRATTO>();
                    }
                    return this.contratto;
                }

                /**
                 * Recupera il valore della proprietà tipoOp.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTipoOp() {
                    return tipoOp;
                }

                /**
                 * Imposta il valore della proprietà tipoOp.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTipoOp(String value) {
                    this.tipoOp = value;
                }


                /**
                 * <p>Classe Java per anonymous complex type.
                 * 
                 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
                 * 
                 * <pre>
                 * &lt;complexType&gt;
                 *   &lt;complexContent&gt;
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                 *       &lt;sequence&gt;
                 *         &lt;element name="DISPOSITIVO" maxOccurs="unbounded"&gt;
                 *           &lt;complexType&gt;
                 *             &lt;complexContent&gt;
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                 *                 &lt;attribute name="tipo_dispositivo" use="required" type="{http://eng.com/rdm/xml/model}TypeDispositivo" /&gt;
                 *                 &lt;attribute name="num_rep" use="required" type="{http://eng.com/rdm/xml/model}TypeNumRepertorio" /&gt;
                 *                 &lt;attribute name="prezzo_agg" use="required" type="{http://eng.com/rdm/xml/model}TypeImporto" /&gt;
                 *                 &lt;attribute name="qta_agg" use="required" type="{http://eng.com/rdm/xml/model}TypeQuantita" /&gt;
                 *                 &lt;attribute name="qta_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeQuantita" /&gt;
                 *                 &lt;attribute name="num_pz" type="{http://eng.com/rdm/xml/model}TypePezzi" /&gt;
                 *                 &lt;attribute name="den_forn" type="{http://eng.com/rdm/xml/model}TypeStr100" /&gt;
                 *                 &lt;attribute name="piva_forn" type="{http://eng.com/rdm/xml/model}TypeStr15" /&gt;
                 *                 &lt;attribute name="iva" use="required" type="{http://eng.com/rdm/xml/model}TypeIVA" /&gt;
                 *                 &lt;attribute name="flg_ser_acc" type="{http://eng.com/rdm/xml/model}TypeFlag" /&gt;
                 *                 &lt;attribute name="flg_conto_dep" use="required" type="{http://eng.com/rdm/xml/model}TypeFlag" /&gt;
                 *                 &lt;attribute name="progr_riga" type="{http://eng.com/rdm/xml/model}TypeNum5" /&gt;
                 *                 &lt;attribute name="cod_mod_ce" type="{http://eng.com/rdm/xml/model}TypeStr6" /&gt;
                 *               &lt;/restriction&gt;
                 *             &lt;/complexContent&gt;
                 *           &lt;/complexType&gt;
                 *         &lt;/element&gt;
                 *       &lt;/sequence&gt;
                 *       &lt;attribute name="num_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeStr40" /&gt;
                 *       &lt;attribute name="anno" use="required"&gt;
                 *         &lt;simpleType&gt;
                 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
                 *             &lt;pattern value="[2][0][0-9]{2}"/&gt;
                 *           &lt;/restriction&gt;
                 *         &lt;/simpleType&gt;
                 *       &lt;/attribute&gt;
                 *       &lt;attribute name="mese" use="required"&gt;
                 *         &lt;simpleType&gt;
                 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
                 *             &lt;pattern value="0[1-9]|1[012]"/&gt;
                 *           &lt;/restriction&gt;
                 *         &lt;/simpleType&gt;
                 *       &lt;/attribute&gt;
                 *       &lt;attribute name="giorno" use="required"&gt;
                 *         &lt;simpleType&gt;
                 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
                 *             &lt;pattern value="0[1-9]|[12][0-9]|3[01]"/&gt;
                 *           &lt;/restriction&gt;
                 *         &lt;/simpleType&gt;
                 *       &lt;/attribute&gt;
                 *       &lt;attribute name="durata_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeDurata" /&gt;
                 *       &lt;attribute name="tipo_contr"&gt;
                 *         &lt;simpleType&gt;
                 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
                 *             &lt;enumeration value="CA"/&gt;
                 *             &lt;enumeration value="CB"/&gt;
                 *             &lt;enumeration value="CC"/&gt;
                 *             &lt;enumeration value="CD"/&gt;
                 *             &lt;enumeration value="CO"/&gt;
                 *             &lt;enumeration value="CS"/&gt;
                 *             &lt;enumeration value="LF"/&gt;
                 *           &lt;/restriction&gt;
                 *         &lt;/simpleType&gt;
                 *       &lt;/attribute&gt;
                 *       &lt;attribute name="forma_neg"&gt;
                 *         &lt;simpleType&gt;
                 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
                 *             &lt;enumeration value="PA"/&gt;
                 *             &lt;enumeration value="PR"/&gt;
                 *             &lt;enumeration value="PS"/&gt;
                 *             &lt;enumeration value="PP"/&gt;
                 *             &lt;enumeration value="CF"/&gt;
                 *             &lt;enumeration value="AD"/&gt;
                 *             &lt;enumeration value="NC"/&gt;
                 *           &lt;/restriction&gt;
                 *         &lt;/simpleType&gt;
                 *       &lt;/attribute&gt;
                 *       &lt;attribute name="amb_val"&gt;
                 *         &lt;simpleType&gt;
                 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
                 *             &lt;enumeration value="1"/&gt;
                 *             &lt;enumeration value="2"/&gt;
                 *             &lt;enumeration value="3"/&gt;
                 *             &lt;enumeration value="4"/&gt;
                 *           &lt;/restriction&gt;
                 *         &lt;/simpleType&gt;
                 *       &lt;/attribute&gt;
                 *       &lt;attribute name="cod_cig" type="{http://eng.com/rdm/xml/model}TypeCig" /&gt;
                 *     &lt;/restriction&gt;
                 *   &lt;/complexContent&gt;
                 * &lt;/complexType&gt;
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "dispositivo"
                })
                public static class CONTRATTO {

                    @XmlElement(name = "DISPOSITIVO", namespace ="http://eng.com/rdm/xml/model", required = true)
                    protected List<DISPOSITIVO> dispositivo;
                    @XmlAttribute(name = "num_contr", required = true)
                    protected String numContr;
                    @XmlAttribute(name = "anno", required = true)
                    protected String anno;
                    @XmlAttribute(name = "mese", required = true)
                    protected String mese;
                    @XmlAttribute(name = "giorno", required = true)
                    protected String giorno;
                    @XmlAttribute(name = "durata_contr", required = true)
                    protected int durataContr;
                    @XmlAttribute(name = "tipo_contr")
                    protected String tipoContr;
                    @XmlAttribute(name = "forma_neg")
                    protected String formaNeg;
                    @XmlAttribute(name = "amb_val")
                    protected String ambVal;
                    @XmlAttribute(name = "cod_cig")
                    protected String codCig;

                    /**
                     * Gets the value of the dispositivo property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the Jakarta XML Binding object.
                     * This is why there is not a <CODE>set</CODE> method for the dispositivo property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getDISPOSITIVO().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link DISPOSITIVO }
                     * 
                     * 
                     */
                    public List<DISPOSITIVO> getDISPOSITIVO() {
                        if (dispositivo == null) {
                            dispositivo = new ArrayList<DISPOSITIVO>();
                        }
                        return this.dispositivo;
                    }

                    /**
                     * Recupera il valore della proprietà numContr.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getNumContr() {
                        return numContr;
                    }

                    /**
                     * Imposta il valore della proprietà numContr.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setNumContr(String value) {
                        this.numContr = value;
                    }

                    /**
                     * Recupera il valore della proprietà anno.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getAnno() {
                        return anno;
                    }

                    /**
                     * Imposta il valore della proprietà anno.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setAnno(String value) {
                        this.anno = value;
                    }

                    /**
                     * Recupera il valore della proprietà mese.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getMese() {
                        return mese;
                    }

                    /**
                     * Imposta il valore della proprietà mese.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setMese(String value) {
                        this.mese = value;
                    }

                    /**
                     * Recupera il valore della proprietà giorno.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getGiorno() {
                        return giorno;
                    }

                    /**
                     * Imposta il valore della proprietà giorno.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setGiorno(String value) {
                        this.giorno = value;
                    }

                    /**
                     * Recupera il valore della proprietà durataContr.
                     * 
                     */
                    public int getDurataContr() {
                        return durataContr;
                    }

                    /**
                     * Imposta il valore della proprietà durataContr.
                     * 
                     */
                    public void setDurataContr(int value) {
                        this.durataContr = value;
                    }

                    /**
                     * Recupera il valore della proprietà tipoContr.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getTipoContr() {
                        return tipoContr;
                    }

                    /**
                     * Imposta il valore della proprietà tipoContr.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setTipoContr(String value) {
                        this.tipoContr = value;
                    }

                    /**
                     * Recupera il valore della proprietà formaNeg.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getFormaNeg() {
                        return formaNeg;
                    }

                    /**
                     * Imposta il valore della proprietà formaNeg.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setFormaNeg(String value) {
                        this.formaNeg = value;
                    }

                    /**
                     * Recupera il valore della proprietà ambVal.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getAmbVal() {
                        return ambVal;
                    }

                    /**
                     * Imposta il valore della proprietà ambVal.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setAmbVal(String value) {
                        this.ambVal = value;
                    }

                    /**
                     * Recupera il valore della proprietà codCig.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCodCig() {
                        return codCig;
                    }

                    /**
                     * Imposta il valore della proprietà codCig.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCodCig(String value) {
                        this.codCig = value;
                    }


                    /**
                     * <p>Classe Java per anonymous complex type.
                     * 
                     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
                     * 
                     * <pre>
                     * &lt;complexType&gt;
                     *   &lt;complexContent&gt;
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                     *       &lt;attribute name="tipo_dispositivo" use="required" type="{http://eng.com/rdm/xml/model}TypeDispositivo" /&gt;
                     *       &lt;attribute name="num_rep" use="required" type="{http://eng.com/rdm/xml/model}TypeNumRepertorio" /&gt;
                     *       &lt;attribute name="prezzo_agg" use="required" type="{http://eng.com/rdm/xml/model}TypeImporto" /&gt;
                     *       &lt;attribute name="qta_agg" use="required" type="{http://eng.com/rdm/xml/model}TypeQuantita" /&gt;
                     *       &lt;attribute name="qta_contr" use="required" type="{http://eng.com/rdm/xml/model}TypeQuantita" /&gt;
                     *       &lt;attribute name="num_pz" type="{http://eng.com/rdm/xml/model}TypePezzi" /&gt;
                     *       &lt;attribute name="den_forn" type="{http://eng.com/rdm/xml/model}TypeStr100" /&gt;
                     *       &lt;attribute name="piva_forn" type="{http://eng.com/rdm/xml/model}TypeStr15" /&gt;
                     *       &lt;attribute name="iva" use="required" type="{http://eng.com/rdm/xml/model}TypeIVA" /&gt;
                     *       &lt;attribute name="flg_ser_acc" type="{http://eng.com/rdm/xml/model}TypeFlag" /&gt;
                     *       &lt;attribute name="flg_conto_dep" use="required" type="{http://eng.com/rdm/xml/model}TypeFlag" /&gt;
                     *       &lt;attribute name="progr_riga" type="{http://eng.com/rdm/xml/model}TypeNum5" /&gt;
                     *       &lt;attribute name="cod_mod_ce" type="{http://eng.com/rdm/xml/model}TypeStr6" /&gt;
                     *     &lt;/restriction&gt;
                     *   &lt;/complexContent&gt;
                     * &lt;/complexType&gt;
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "content"
                    })
                    public static class DISPOSITIVO {

                        @XmlValue
                        protected String content;
                        @XmlAttribute(name = "tipo_dispositivo", required = true)
                        protected String tipoDispositivo;
                        @XmlAttribute(name = "num_rep", required = true)
                        protected long numRep;
                        @XmlAttribute(name = "prezzo_agg", required = true)
                        protected BigDecimal prezzoAgg;
                        @XmlAttribute(name = "qta_agg", required = true)
                        protected BigDecimal qtaAgg;
                        @XmlAttribute(name = "qta_contr", required = true)
                        protected BigDecimal qtaContr;
                        @XmlAttribute(name = "num_pz")
                        protected BigDecimal numPz;
                        @XmlAttribute(name = "den_forn")
                        protected String denForn;
                        @XmlAttribute(name = "piva_forn")
                        protected String pivaForn;
                        @XmlAttribute(name = "iva", required = true)
                        protected String iva;
                        @XmlAttribute(name = "flg_ser_acc")
                        protected String flgSerAcc;
                        @XmlAttribute(name = "flg_conto_dep", required = true)
                        protected String flgContoDep;
                        @XmlAttribute(name = "progr_riga")
                        protected Integer progrRiga;
                        @XmlAttribute(name = "cod_mod_ce")
                        protected String codModCe;

                        /**
                         * Recupera il valore della proprietà content.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getContent() {
                            return content;
                        }

                        /**
                         * Imposta il valore della proprietà content.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setContent(String value) {
                            this.content = value;
                        }

                        /**
                         * Recupera il valore della proprietà tipoDispositivo.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getTipoDispositivo() {
                            return tipoDispositivo;
                        }

                        /**
                         * Imposta il valore della proprietà tipoDispositivo.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setTipoDispositivo(String value) {
                            this.tipoDispositivo = value;
                        }

                        /**
                         * Recupera il valore della proprietà numRep.
                         * 
                         */
                        public long getNumRep() {
                            return numRep;
                        }

                        /**
                         * Imposta il valore della proprietà numRep.
                         * 
                         */
                        public void setNumRep(long value) {
                            this.numRep = value;
                        }

                        /**
                         * Recupera il valore della proprietà prezzoAgg.
                         * 
                         * @return
                         *     possible object is
                         *     {@link BigDecimal }
                         *     
                         */
                        public BigDecimal getPrezzoAgg() {
                            return prezzoAgg;
                        }

                        /**
                         * Imposta il valore della proprietà prezzoAgg.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link BigDecimal }
                         *     
                         */
                        public void setPrezzoAgg(BigDecimal value) {
                            this.prezzoAgg = value;
                        }

                        /**
                         * Recupera il valore della proprietà qtaAgg.
                         * 
                         * @return
                         *     possible object is
                         *     {@link BigDecimal }
                         *     
                         */
                        public BigDecimal getQtaAgg() {
                            return qtaAgg;
                        }

                        /**
                         * Imposta il valore della proprietà qtaAgg.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link BigDecimal }
                         *     
                         */
                        public void setQtaAgg(BigDecimal value) {
                            this.qtaAgg = value;
                        }

                        /**
                         * Recupera il valore della proprietà qtaContr.
                         * 
                         * @return
                         *     possible object is
                         *     {@link BigDecimal }
                         *     
                         */
                        public BigDecimal getQtaContr() {
                            return qtaContr;
                        }

                        /**
                         * Imposta il valore della proprietà qtaContr.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link BigDecimal }
                         *     
                         */
                        public void setQtaContr(BigDecimal value) {
                            this.qtaContr = value;
                        }

                        /**
                         * Recupera il valore della proprietà numPz.
                         * 
                         * @return
                         *     possible object is
                         *     {@link BigDecimal }
                         *     
                         */
                        public BigDecimal getNumPz() {
                            return numPz;
                        }

                        /**
                         * Imposta il valore della proprietà numPz.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link BigDecimal }
                         *     
                         */
                        public void setNumPz(BigDecimal value) {
                            this.numPz = value;
                        }

                        /**
                         * Recupera il valore della proprietà denForn.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getDenForn() {
                            return denForn;
                        }

                        /**
                         * Imposta il valore della proprietà denForn.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setDenForn(String value) {
                            this.denForn = value;
                        }

                        /**
                         * Recupera il valore della proprietà pivaForn.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getPivaForn() {
                            return pivaForn;
                        }

                        /**
                         * Imposta il valore della proprietà pivaForn.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setPivaForn(String value) {
                            this.pivaForn = value;
                        }

                        /**
                         * Recupera il valore della proprietà iva.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getIva() {
                            return iva;
                        }

                        /**
                         * Imposta il valore della proprietà iva.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setIva(String value) {
                            this.iva = value;
                        }

                        /**
                         * Recupera il valore della proprietà flgSerAcc.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getFlgSerAcc() {
                            return flgSerAcc;
                        }

                        /**
                         * Imposta il valore della proprietà flgSerAcc.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setFlgSerAcc(String value) {
                            this.flgSerAcc = value;
                        }

                        /**
                         * Recupera il valore della proprietà flgContoDep.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getFlgContoDep() {
                            return flgContoDep;
                        }

                        /**
                         * Imposta il valore della proprietà flgContoDep.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setFlgContoDep(String value) {
                            this.flgContoDep = value;
                        }

                        /**
                         * Recupera il valore della proprietà progrRiga.
                         * 
                         * @return
                         *     possible object is
                         *     {@link Integer }
                         *     
                         */
                        public Integer getProgrRiga() {
                            return progrRiga;
                        }

                        /**
                         * Imposta il valore della proprietà progrRiga.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link Integer }
                         *     
                         */
                        public void setProgrRiga(Integer value) {
                            this.progrRiga = value;
                        }

                        /**
                         * Recupera il valore della proprietà codModCe.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCodModCe() {
                            return codModCe;
                        }

                        /**
                         * Imposta il valore della proprietà codModCe.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCodModCe(String value) {
                            this.codModCe = value;
                        }

                    }

                }

            }

        }

    }

}
