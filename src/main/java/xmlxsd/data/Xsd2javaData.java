//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.12.02 时间 08:25:24 PM CST 
//
//XJC生成

package xmlxsd.data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Type">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="childNumber">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="childs" maxOccurs="100">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="childID">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                         &lt;minInclusive value="1"/>
 *                         &lt;maxInclusive value="90000000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="childName" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="80"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="childSex" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="80"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="childHobby">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="80"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="childHeight">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                         &lt;minInclusive value="1"/>
 *                         &lt;maxInclusive value="90000000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="childWidth">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                         &lt;minInclusive value="1"/>
 *                         &lt;maxInclusive value="90000000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="childColor" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="80"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="childBornTime">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *                         &lt;minInclusive value="1"/>
 *                         &lt;maxInclusive value="9999999999999"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="childDeadTime">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *                         &lt;minInclusive value="1"/>
 *                         &lt;maxInclusive value="9999999999999"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
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
    "id",
    "name",
    "type",
    "childNumber",
    "childs"
})
@XmlRootElement(name = "example")
public class Xsd2javaData {

    @XmlElement(name = "ID")
    protected int id;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Type", required = true)
    protected String type;
    protected int childNumber;
    @XmlElement(required = true)
    protected List<Xsd2javaData.Childs> childs;

    /**
     * 获取id属性的值。
     * 
     */
    public int getID() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     */
    public void setID(int value) {
        this.id = value;
    }

    /**
     * 获取name属性的值。
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
     * 设置name属性的值。
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
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * 获取childNumber属性的值。
     * 
     */
    public int getChildNumber() {
        return childNumber;
    }

    /**
     * 设置childNumber属性的值。
     * 
     */
    public void setChildNumber(int value) {
        this.childNumber = value;
    }

    /**
     * Gets the value of the childs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the childs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChilds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Xsd2javaData.Childs }
     * 
     * 
     */
    public List<Xsd2javaData.Childs> getChilds() {
        if (childs == null) {
            childs = new ArrayList<Xsd2javaData.Childs>();
        }
        return this.childs;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="childID">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *               &lt;minInclusive value="1"/>
     *               &lt;maxInclusive value="90000000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="childName" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="80"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="childSex" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="80"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="childHobby">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="80"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="childHeight">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *               &lt;minInclusive value="1"/>
     *               &lt;maxInclusive value="90000000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="childWidth">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *               &lt;minInclusive value="1"/>
     *               &lt;maxInclusive value="90000000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="childColor" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="80"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="childBornTime">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
     *               &lt;minInclusive value="1"/>
     *               &lt;maxInclusive value="9999999999999"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="childDeadTime">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
     *               &lt;minInclusive value="1"/>
     *               &lt;maxInclusive value="9999999999999"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
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
        "childID",
        "childName",
        "childSex",
        "childHobby",
        "childHeight",
        "childWidth",
        "childColor",
        "childBornTime",
        "childDeadTime"
    })
    public static class Childs {

        protected int childID;
        protected String childName;
        protected String childSex;
        @XmlElement(required = true)
        protected String childHobby;
        protected int childHeight;
        protected int childWidth;
        protected String childColor;
        protected long childBornTime;
        protected long childDeadTime;

        /**
         * 获取childID属性的值。
         * 
         */
        public int getChildID() {
            return childID;
        }

        /**
         * 设置childID属性的值。
         * 
         */
        public void setChildID(int value) {
            this.childID = value;
        }

        /**
         * 获取childName属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getChildName() {
            return childName;
        }

        /**
         * 设置childName属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setChildName(String value) {
            this.childName = value;
        }

        /**
         * 获取childSex属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getChildSex() {
            return childSex;
        }

        /**
         * 设置childSex属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setChildSex(String value) {
            this.childSex = value;
        }

        /**
         * 获取childHobby属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getChildHobby() {
            return childHobby;
        }

        /**
         * 设置childHobby属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setChildHobby(String value) {
            this.childHobby = value;
        }

        /**
         * 获取childHeight属性的值。
         * 
         */
        public int getChildHeight() {
            return childHeight;
        }

        /**
         * 设置childHeight属性的值。
         * 
         */
        public void setChildHeight(int value) {
            this.childHeight = value;
        }

        /**
         * 获取childWidth属性的值。
         * 
         */
        public int getChildWidth() {
            return childWidth;
        }

        /**
         * 设置childWidth属性的值。
         * 
         */
        public void setChildWidth(int value) {
            this.childWidth = value;
        }

        /**
         * 获取childColor属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getChildColor() {
            return childColor;
        }

        /**
         * 设置childColor属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setChildColor(String value) {
            this.childColor = value;
        }

        /**
         * 获取childBornTime属性的值。
         * 
         */
        public long getChildBornTime() {
            return childBornTime;
        }

        /**
         * 设置childBornTime属性的值。
         * 
         */
        public void setChildBornTime(long value) {
            this.childBornTime = value;
        }

        /**
         * 获取childDeadTime属性的值。
         * 
         */
        public long getChildDeadTime() {
            return childDeadTime;
        }

        /**
         * 设置childDeadTime属性的值。
         * 
         */
        public void setChildDeadTime(long value) {
            this.childDeadTime = value;
        }

    }

}
