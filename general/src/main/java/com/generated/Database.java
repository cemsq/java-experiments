//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2015.11.06 um 03:53:58 PM CET 
//


package com.generated;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java-Klasse f�r anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tables">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Table" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                             &lt;element name="Columns">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Column" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;simpleContent>
 *                                             &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                               &lt;attribute name="name" use="required" type="{}dbColumnName" />
 *                                               &lt;attribute name="type" use="required" type="{}columnType" />
 *                                               &lt;attribute name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="domain" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                             &lt;/extension>
 *                                           &lt;/simpleContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="Index" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Columns">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="Column" maxOccurs="unbounded">
 *                                                   &lt;complexType>
 *                                                     &lt;simpleContent>
 *                                                       &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                                         &lt;attribute name="name" use="required" type="{}dbColumnName" />
 *                                                       &lt;/extension>
 *                                                     &lt;/simpleContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="name" use="required" type="{}dbObjectName" />
 *                                     &lt;attribute name="primary" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="unique" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="Values" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Value" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;simpleContent>
 *                                             &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                               &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="core" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="displayText" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="sortSequence" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                                             &lt;/extension>
 *                                           &lt;/simpleContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="name" use="required" type="{}dbObjectName" />
 *                           &lt;attribute name="isValueTable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                           &lt;attribute name="deleteLogical" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ForeignKeys">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ForeignKey" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Column">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="src" use="required" type="{}dbColumnName" />
 *                                     &lt;attribute name="target" use="required" type="{}dbColumnName" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="name" use="required" type="{}dbObjectName" />
 *                           &lt;attribute name="child" use="required" type="{}dbObjectName" />
 *                           &lt;attribute name="parent" use="required" type="{}dbObjectName" />
 *                           &lt;attribute name="cardinality" use="required" type="{}cardinalityType" />
 *                           &lt;attribute name="masterDetail" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Domains" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Domain" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="name" use="required" type="{}dbObjectName" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tables",
    "foreignKeys",
    "domains"
})
@XmlRootElement(name = "Database")
public class Database {

    @XmlElement(name = "Tables", required = true)
    protected Database.Tables tables;
    @XmlElement(name = "ForeignKeys", required = true)
    protected Database.ForeignKeys foreignKeys;
    @XmlElement(name = "Domains")
    protected List<Database.Domains> domains;

    /**
     * Ruft den Wert der tables-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Database.Tables }
     *     
     */
    public Database.Tables getTables() {
        return tables;
    }

    /**
     * Legt den Wert der tables-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Database.Tables }
     *     
     */
    public void setTables(Database.Tables value) {
        this.tables = value;
    }

    /**
     * Ruft den Wert der foreignKeys-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Database.ForeignKeys }
     *     
     */
    public Database.ForeignKeys getForeignKeys() {
        return foreignKeys;
    }

    /**
     * Legt den Wert der foreignKeys-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Database.ForeignKeys }
     *     
     */
    public void setForeignKeys(Database.ForeignKeys value) {
        this.foreignKeys = value;
    }

    /**
     * Gets the value of the domains property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the domains property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDomains().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Database.Domains }
     * 
     * 
     */
    public List<Database.Domains> getDomains() {
        if (domains == null) {
            domains = new ArrayList<Database.Domains>();
        }
        return this.domains;
    }


    /**
     * <p>Java-Klasse f�r anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Domain" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="name" use="required" type="{}dbObjectName" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "domain"
    })
    public static class Domains {

        @XmlElement(name = "Domain")
        protected List<Database.Domains.Domain> domain;

        /**
         * Gets the value of the domain property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the domain property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDomain().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Database.Domains.Domain }
         * 
         * 
         */
        public List<Database.Domains.Domain> getDomain() {
            if (domain == null) {
                domain = new ArrayList<Database.Domains.Domain>();
            }
            return this.domain;
        }


        /**
         * <p>Java-Klasse f�r anonymous complex type.
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="name" use="required" type="{}dbObjectName" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Domain {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "name", required = true)
            protected String name;

            /**
             * Ruft den Wert der value-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Legt den Wert der value-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Ruft den Wert der name-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Legt den Wert der name-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

        }

    }


    /**
     * <p>Java-Klasse f�r anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ForeignKey" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Column">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                           &lt;attribute name="src" use="required" type="{}dbColumnName" />
     *                           &lt;attribute name="target" use="required" type="{}dbColumnName" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="name" use="required" type="{}dbObjectName" />
     *                 &lt;attribute name="child" use="required" type="{}dbObjectName" />
     *                 &lt;attribute name="parent" use="required" type="{}dbObjectName" />
     *                 &lt;attribute name="cardinality" use="required" type="{}cardinalityType" />
     *                 &lt;attribute name="masterDetail" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "foreignKey"
    })
    public static class ForeignKeys {

        @XmlElement(name = "ForeignKey")
        protected List<Database.ForeignKeys.ForeignKey> foreignKey;

        /**
         * Gets the value of the foreignKey property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the foreignKey property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getForeignKey().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Database.ForeignKeys.ForeignKey }
         * 
         * 
         */
        public List<Database.ForeignKeys.ForeignKey> getForeignKey() {
            if (foreignKey == null) {
                foreignKey = new ArrayList<Database.ForeignKeys.ForeignKey>();
            }
            return this.foreignKey;
        }


        /**
         * <p>Java-Klasse f�r anonymous complex type.
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="Column">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                 &lt;attribute name="src" use="required" type="{}dbColumnName" />
         *                 &lt;attribute name="target" use="required" type="{}dbColumnName" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="name" use="required" type="{}dbObjectName" />
         *       &lt;attribute name="child" use="required" type="{}dbObjectName" />
         *       &lt;attribute name="parent" use="required" type="{}dbObjectName" />
         *       &lt;attribute name="cardinality" use="required" type="{}cardinalityType" />
         *       &lt;attribute name="masterDetail" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "column"
        })
        public static class ForeignKey {

            @XmlElement(name = "Column", required = true)
            protected Database.ForeignKeys.ForeignKey.Column column;
            @XmlAttribute(name = "name", required = true)
            protected String name;
            @XmlAttribute(name = "child", required = true)
            protected String child;
            @XmlAttribute(name = "parent", required = true)
            protected String parent;
            @XmlAttribute(name = "cardinality", required = true)
            protected CardinalityType cardinality;
            @XmlAttribute(name = "masterDetail")
            protected Boolean masterDetail;

            /**
             * Ruft den Wert der column-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link Database.ForeignKeys.ForeignKey.Column }
             *     
             */
            public Database.ForeignKeys.ForeignKey.Column getColumn() {
                return column;
            }

            /**
             * Legt den Wert der column-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link Database.ForeignKeys.ForeignKey.Column }
             *     
             */
            public void setColumn(Database.ForeignKeys.ForeignKey.Column value) {
                this.column = value;
            }

            /**
             * Ruft den Wert der name-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Legt den Wert der name-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Ruft den Wert der child-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getChild() {
                return child;
            }

            /**
             * Legt den Wert der child-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setChild(String value) {
                this.child = value;
            }

            /**
             * Ruft den Wert der parent-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getParent() {
                return parent;
            }

            /**
             * Legt den Wert der parent-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setParent(String value) {
                this.parent = value;
            }

            /**
             * Ruft den Wert der cardinality-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link CardinalityType }
             *     
             */
            public CardinalityType getCardinality() {
                return cardinality;
            }

            /**
             * Legt den Wert der cardinality-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link CardinalityType }
             *     
             */
            public void setCardinality(CardinalityType value) {
                this.cardinality = value;
            }

            /**
             * Ruft den Wert der masterDetail-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isMasterDetail() {
                return masterDetail;
            }

            /**
             * Legt den Wert der masterDetail-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setMasterDetail(Boolean value) {
                this.masterDetail = value;
            }


            /**
             * <p>Java-Klasse f�r anonymous complex type.
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *       &lt;attribute name="src" use="required" type="{}dbColumnName" />
             *       &lt;attribute name="target" use="required" type="{}dbColumnName" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class Column {

                @XmlValue
                protected String value;
                @XmlAttribute(name = "src", required = true)
                protected String src;
                @XmlAttribute(name = "target", required = true)
                protected String target;

                /**
                 * Ruft den Wert der value-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Legt den Wert der value-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

                /**
                 * Ruft den Wert der src-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getSrc() {
                    return src;
                }

                /**
                 * Legt den Wert der src-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setSrc(String value) {
                    this.src = value;
                }

                /**
                 * Ruft den Wert der target-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTarget() {
                    return target;
                }

                /**
                 * Legt den Wert der target-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTarget(String value) {
                    this.target = value;
                }

            }

        }

    }


    /**
     * <p>Java-Klasse f�r anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Table" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *                   &lt;element name="Columns">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Column" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;simpleContent>
     *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                                     &lt;attribute name="name" use="required" type="{}dbColumnName" />
     *                                     &lt;attribute name="type" use="required" type="{}columnType" />
     *                                     &lt;attribute name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                     &lt;attribute name="domain" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                     &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                     &lt;attribute name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                                   &lt;/extension>
     *                                 &lt;/simpleContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="Index" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Columns">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="Column" maxOccurs="unbounded">
     *                                         &lt;complexType>
     *                                           &lt;simpleContent>
     *                                             &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                                               &lt;attribute name="name" use="required" type="{}dbColumnName" />
     *                                             &lt;/extension>
     *                                           &lt;/simpleContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                           &lt;attribute name="name" use="required" type="{}dbObjectName" />
     *                           &lt;attribute name="primary" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="unique" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="Values" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Value" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;simpleContent>
     *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                                     &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                     &lt;attribute name="core" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                     &lt;attribute name="displayText" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                     &lt;attribute name="sortSequence" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                                   &lt;/extension>
     *                                 &lt;/simpleContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="name" use="required" type="{}dbObjectName" />
     *                 &lt;attribute name="isValueTable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                 &lt;attribute name="deleteLogical" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "table"
    })
    public static class Tables {

        @XmlElement(name = "Table", required = true)
        protected List<Database.Tables.Table> table;

        /**
         * Gets the value of the table property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the table property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTable().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Database.Tables.Table }
         * 
         * 
         */
        public List<Database.Tables.Table> getTable() {
            if (table == null) {
                table = new ArrayList<Database.Tables.Table>();
            }
            return this.table;
        }


        /**
         * <p>Java-Klasse f�r anonymous complex type.
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
         *         &lt;element name="Columns">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Column" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;simpleContent>
         *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                           &lt;attribute name="name" use="required" type="{}dbColumnName" />
         *                           &lt;attribute name="type" use="required" type="{}columnType" />
         *                           &lt;attribute name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="domain" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                         &lt;/extension>
         *                       &lt;/simpleContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="Index" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Columns">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="Column" maxOccurs="unbounded">
         *                               &lt;complexType>
         *                                 &lt;simpleContent>
         *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                                     &lt;attribute name="name" use="required" type="{}dbColumnName" />
         *                                   &lt;/extension>
         *                                 &lt;/simpleContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *                 &lt;attribute name="name" use="required" type="{}dbObjectName" />
         *                 &lt;attribute name="primary" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="unique" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="Values" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Value" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;simpleContent>
         *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                           &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="core" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="displayText" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="sortSequence" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *                         &lt;/extension>
         *                       &lt;/simpleContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="name" use="required" type="{}dbObjectName" />
         *       &lt;attribute name="isValueTable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *       &lt;attribute name="deleteLogical" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "comment",
            "columns",
            "index",
            "values"
        })
        public static class Table {

            @XmlElement(name = "Comment")
            protected List<String> comment;
            @XmlElement(name = "Columns", required = true)
            protected Database.Tables.Table.Columns columns;
            @XmlElement(name = "Index", required = true)
            protected List<Database.Tables.Table.Index> index;
            @XmlElement(name = "Values")
            protected List<Database.Tables.Table.Values> values;
            @XmlAttribute(name = "name", required = true)
            protected String name;
            @XmlAttribute(name = "isValueTable")
            protected Boolean isValueTable;
            @XmlAttribute(name = "deleteLogical")
            protected Boolean deleteLogical;

            /**
             * Gets the value of the comment property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the comment property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getComment().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getComment() {
                if (comment == null) {
                    comment = new ArrayList<String>();
                }
                return this.comment;
            }

            /**
             * Ruft den Wert der columns-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link Database.Tables.Table.Columns }
             *     
             */
            public Database.Tables.Table.Columns getColumns() {
                return columns;
            }

            /**
             * Legt den Wert der columns-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link Database.Tables.Table.Columns }
             *     
             */
            public void setColumns(Database.Tables.Table.Columns value) {
                this.columns = value;
            }

            /**
             * Gets the value of the index property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the index property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getIndex().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Database.Tables.Table.Index }
             * 
             * 
             */
            public List<Database.Tables.Table.Index> getIndex() {
                if (index == null) {
                    index = new ArrayList<Database.Tables.Table.Index>();
                }
                return this.index;
            }

            /**
             * Gets the value of the values property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the values property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getValues().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Database.Tables.Table.Values }
             * 
             * 
             */
            public List<Database.Tables.Table.Values> getValues() {
                if (values == null) {
                    values = new ArrayList<Database.Tables.Table.Values>();
                }
                return this.values;
            }

            /**
             * Ruft den Wert der name-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Legt den Wert der name-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Ruft den Wert der isValueTable-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isIsValueTable() {
                return isValueTable;
            }

            /**
             * Legt den Wert der isValueTable-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setIsValueTable(Boolean value) {
                this.isValueTable = value;
            }

            /**
             * Ruft den Wert der deleteLogical-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDeleteLogical() {
                return deleteLogical;
            }

            /**
             * Legt den Wert der deleteLogical-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDeleteLogical(Boolean value) {
                this.deleteLogical = value;
            }


            /**
             * <p>Java-Klasse f�r anonymous complex type.
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="Column" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;simpleContent>
             *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *                 &lt;attribute name="name" use="required" type="{}dbColumnName" />
             *                 &lt;attribute name="type" use="required" type="{}columnType" />
             *                 &lt;attribute name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="domain" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *               &lt;/extension>
             *             &lt;/simpleContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "column"
            })
            public static class Columns {

                @XmlElement(name = "Column")
                protected List<Database.Tables.Table.Columns.Column> column;

                /**
                 * Gets the value of the column property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the column property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getColumn().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Database.Tables.Table.Columns.Column }
                 * 
                 * 
                 */
                public List<Database.Tables.Table.Columns.Column> getColumn() {
                    if (column == null) {
                        column = new ArrayList<Database.Tables.Table.Columns.Column>();
                    }
                    return this.column;
                }


                /**
                 * <p>Java-Klasse f�r anonymous complex type.
                 * 
                 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;simpleContent>
                 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                 *       &lt;attribute name="name" use="required" type="{}dbColumnName" />
                 *       &lt;attribute name="type" use="required" type="{}columnType" />
                 *       &lt;attribute name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="domain" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" />
                 *     &lt;/extension>
                 *   &lt;/simpleContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "value"
                })
                public static class Column {

                    @XmlValue
                    protected String value;
                    @XmlAttribute(name = "name", required = true)
                    protected String name;
                    @XmlAttribute(name = "type", required = true)
                    protected ColumnType type;
                    @XmlAttribute(name = "defaultValue")
                    protected String defaultValue;
                    @XmlAttribute(name = "domain")
                    protected String domain;
                    @XmlAttribute(name = "length")
                    protected String length;
                    @XmlAttribute(name = "mandatory")
                    protected Boolean mandatory;

                    /**
                     * Ruft den Wert der value-Eigenschaft ab.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getValue() {
                        return value;
                    }

                    /**
                     * Legt den Wert der value-Eigenschaft fest.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setValue(String value) {
                        this.value = value;
                    }

                    /**
                     * Ruft den Wert der name-Eigenschaft ab.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getName() {
                        return name;
                    }

                    /**
                     * Legt den Wert der name-Eigenschaft fest.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setName(String value) {
                        this.name = value;
                    }

                    /**
                     * Ruft den Wert der type-Eigenschaft ab.
                     * 
                     * @return
                     *     possible object is
                     *     {@link ColumnType }
                     *     
                     */
                    public ColumnType getType() {
                        return type;
                    }

                    /**
                     * Legt den Wert der type-Eigenschaft fest.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link ColumnType }
                     *     
                     */
                    public void setType(ColumnType value) {
                        this.type = value;
                    }

                    /**
                     * Ruft den Wert der defaultValue-Eigenschaft ab.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getDefaultValue() {
                        return defaultValue;
                    }

                    /**
                     * Legt den Wert der defaultValue-Eigenschaft fest.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setDefaultValue(String value) {
                        this.defaultValue = value;
                    }

                    /**
                     * Ruft den Wert der domain-Eigenschaft ab.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getDomain() {
                        return domain;
                    }

                    /**
                     * Legt den Wert der domain-Eigenschaft fest.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setDomain(String value) {
                        this.domain = value;
                    }

                    /**
                     * Ruft den Wert der length-Eigenschaft ab.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getLength() {
                        return length;
                    }

                    /**
                     * Legt den Wert der length-Eigenschaft fest.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setLength(String value) {
                        this.length = value;
                    }

                    /**
                     * Ruft den Wert der mandatory-Eigenschaft ab.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Boolean }
                     *     
                     */
                    public Boolean isMandatory() {
                        return mandatory;
                    }

                    /**
                     * Legt den Wert der mandatory-Eigenschaft fest.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Boolean }
                     *     
                     */
                    public void setMandatory(Boolean value) {
                        this.mandatory = value;
                    }

                }

            }


            /**
             * <p>Java-Klasse f�r anonymous complex type.
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="Columns">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="Column" maxOccurs="unbounded">
             *                     &lt;complexType>
             *                       &lt;simpleContent>
             *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *                           &lt;attribute name="name" use="required" type="{}dbColumnName" />
             *                         &lt;/extension>
             *                       &lt;/simpleContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *       &lt;attribute name="name" use="required" type="{}dbObjectName" />
             *       &lt;attribute name="primary" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="unique" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "columns"
            })
            public static class Index {

                @XmlElement(name = "Columns", required = true)
                protected Database.Tables.Table.Index.Columns columns;
                @XmlAttribute(name = "name", required = true)
                protected String name;
                @XmlAttribute(name = "primary")
                protected Boolean primary;
                @XmlAttribute(name = "unique")
                protected Boolean unique;

                /**
                 * Ruft den Wert der columns-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Database.Tables.Table.Index.Columns }
                 *     
                 */
                public Database.Tables.Table.Index.Columns getColumns() {
                    return columns;
                }

                /**
                 * Legt den Wert der columns-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Database.Tables.Table.Index.Columns }
                 *     
                 */
                public void setColumns(Database.Tables.Table.Index.Columns value) {
                    this.columns = value;
                }

                /**
                 * Ruft den Wert der name-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Legt den Wert der name-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

                /**
                 * Ruft den Wert der primary-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Boolean }
                 *     
                 */
                public Boolean isPrimary() {
                    return primary;
                }

                /**
                 * Legt den Wert der primary-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Boolean }
                 *     
                 */
                public void setPrimary(Boolean value) {
                    this.primary = value;
                }

                /**
                 * Ruft den Wert der unique-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Boolean }
                 *     
                 */
                public Boolean isUnique() {
                    return unique;
                }

                /**
                 * Legt den Wert der unique-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Boolean }
                 *     
                 */
                public void setUnique(Boolean value) {
                    this.unique = value;
                }


                /**
                 * <p>Java-Klasse f�r anonymous complex type.
                 * 
                 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="Column" maxOccurs="unbounded">
                 *           &lt;complexType>
                 *             &lt;simpleContent>
                 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                 *                 &lt;attribute name="name" use="required" type="{}dbColumnName" />
                 *               &lt;/extension>
                 *             &lt;/simpleContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *       &lt;/sequence>
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "column"
                })
                public static class Columns {

                    @XmlElement(name = "Column", required = true)
                    protected List<Database.Tables.Table.Index.Columns.Column> column;

                    /**
                     * Gets the value of the column property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the column property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getColumn().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link Database.Tables.Table.Index.Columns.Column }
                     * 
                     * 
                     */
                    public List<Database.Tables.Table.Index.Columns.Column> getColumn() {
                        if (column == null) {
                            column = new ArrayList<Database.Tables.Table.Index.Columns.Column>();
                        }
                        return this.column;
                    }


                    /**
                     * <p>Java-Klasse f�r anonymous complex type.
                     * 
                     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;simpleContent>
                     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                     *       &lt;attribute name="name" use="required" type="{}dbColumnName" />
                     *     &lt;/extension>
                     *   &lt;/simpleContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "value"
                    })
                    public static class Column {

                        @XmlValue
                        protected String value;
                        @XmlAttribute(name = "name", required = true)
                        protected String name;

                        /**
                         * Ruft den Wert der value-Eigenschaft ab.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getValue() {
                            return value;
                        }

                        /**
                         * Legt den Wert der value-Eigenschaft fest.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setValue(String value) {
                            this.value = value;
                        }

                        /**
                         * Ruft den Wert der name-Eigenschaft ab.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getName() {
                            return name;
                        }

                        /**
                         * Legt den Wert der name-Eigenschaft fest.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setName(String value) {
                            this.name = value;
                        }

                    }

                }

            }


            /**
             * <p>Java-Klasse f�r anonymous complex type.
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="Value" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;simpleContent>
             *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *                 &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="core" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="displayText" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="sortSequence" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
             *               &lt;/extension>
             *             &lt;/simpleContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class Values {

                @XmlElement(name = "Value")
                protected List<Database.Tables.Table.Values.Value> value;

                /**
                 * Gets the value of the value property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the value property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getValue().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Database.Tables.Table.Values.Value }
                 * 
                 * 
                 */
                public List<Database.Tables.Table.Values.Value> getValue() {
                    if (value == null) {
                        value = new ArrayList<Database.Tables.Table.Values.Value>();
                    }
                    return this.value;
                }


                /**
                 * <p>Java-Klasse f�r anonymous complex type.
                 * 
                 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;simpleContent>
                 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="core" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="displayText" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="sortSequence" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
                 *     &lt;/extension>
                 *   &lt;/simpleContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "value"
                })
                public static class Value {

                    @XmlValue
                    protected String value;
                    @XmlAttribute(name = "id", required = true)
                    protected String id;
                    @XmlAttribute(name = "core", required = true)
                    protected String code;
                    @XmlAttribute(name = "displayText")
                    protected String displayText;
                    @XmlAttribute(name = "sortSequence", required = true)
                    protected BigInteger sortSequence;

                    /**
                     * Ruft den Wert der value-Eigenschaft ab.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getValue() {
                        return value;
                    }

                    /**
                     * Legt den Wert der value-Eigenschaft fest.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setValue(String value) {
                        this.value = value;
                    }

                    /**
                     * Ruft den Wert der id-Eigenschaft ab.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getId() {
                        return id;
                    }

                    /**
                     * Legt den Wert der id-Eigenschaft fest.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setId(String value) {
                        this.id = value;
                    }

                    /**
                     * Ruft den Wert der core-Eigenschaft ab.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCode() {
                        return code;
                    }

                    /**
                     * Legt den Wert der core-Eigenschaft fest.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCode(String value) {
                        this.code = value;
                    }

                    /**
                     * Ruft den Wert der displayText-Eigenschaft ab.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getDisplayText() {
                        return displayText;
                    }

                    /**
                     * Legt den Wert der displayText-Eigenschaft fest.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setDisplayText(String value) {
                        this.displayText = value;
                    }

                    /**
                     * Ruft den Wert der sortSequence-Eigenschaft ab.
                     * 
                     * @return
                     *     possible object is
                     *     {@link BigInteger }
                     *     
                     */
                    public BigInteger getSortSequence() {
                        return sortSequence;
                    }

                    /**
                     * Legt den Wert der sortSequence-Eigenschaft fest.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link BigInteger }
                     *     
                     */
                    public void setSortSequence(BigInteger value) {
                        this.sortSequence = value;
                    }

                }

            }

        }

    }

}
