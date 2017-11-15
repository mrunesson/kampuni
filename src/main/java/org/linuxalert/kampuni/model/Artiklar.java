package org.linuxalert.kampuni.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "skapadTid",
    "info",
    "artikel"
})
@XmlRootElement(name = "artiklar")
public class Artiklar {

    @XmlElement(name = "skapad-tid")
    protected String skapadTid;
    protected List<Info> info;
    protected List<Artikel> artikel;

    public String getSkapadTid() {
        return skapadTid;
    }

    public void setSkapadTid(String value) {
        this.skapadTid = value;
    }

    public List<Info> getInfo() {
        if (info == null) {
            info = new ArrayList<Info>();
        }
        return this.info;
    }

    public List<Artikel> getArtikel() {
        if (artikel == null) {
            artikel = new ArrayList<Artikel>();
        }
        return this.artikel;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nr",
        "artikelid",
        "varnummer",
        "namn",
        "namn2",
        "prisinklmoms",
        "pant",
        "volymiml",
        "prisPerLiter",
        "saljstart",
        "slutlev",
        "varugrupp",
        "forpackning",
        "forslutning",
        "ursprung",
        "ursprunglandnamn",
        "producent",
        "leverantor",
        "argang",
        "provadargang",
        "alkoholhalt",
        "modul",
        "sortiment",
        "ekologisk",
        "koscher",
        "ravarorBeskrivning"
    })
    public static class Artikel {

        protected String nr;
        @XmlElement(name = "Artikelid")
        protected String artikelid;
        @XmlElement(name = "Varnummer")
        protected String varnummer;
        @XmlElement(name = "Namn")
        protected String namn;
        @XmlElement(name = "Namn2")
        protected String namn2;
        @XmlElement(name = "Prisinklmoms")
        protected String prisinklmoms;
        @XmlElement(name = "Pant")
        protected String pant;
        @XmlElement(name = "Volymiml")
        protected String volymiml;
        @XmlElement(name = "PrisPerLiter")
        protected String prisPerLiter;
        @XmlElement(name = "Saljstart")
        protected String saljstart;
        @XmlElement(name = "Slutlev")
        protected String slutlev;
        @XmlElement(name = "Varugrupp")
        protected String varugrupp;
        @XmlElement(name = "Forpackning")
        protected String forpackning;
        @XmlElement(name = "Forslutning")
        protected String forslutning;
        @XmlElement(name = "Ursprung")
        protected String ursprung;
        @XmlElement(name = "Ursprunglandnamn")
        protected String ursprunglandnamn;
        @XmlElement(name = "Producent")
        protected String producent;
        @XmlElement(name = "Leverantor")
        protected String leverantor;
        @XmlElement(name = "Argang")
        protected String argang;
        @XmlElement(name = "Provadargang")
        protected String provadargang;
        @XmlElement(name = "Alkoholhalt")
        protected String alkoholhalt;
        @XmlElement(name = "Modul")
        protected String modul;
        @XmlElement(name = "Sortiment")
        protected String sortiment;
        @XmlElement(name = "Ekologisk")
        protected String ekologisk;
        @XmlElement(name = "Koscher")
        protected String koscher;
        @XmlElement(name = "RavarorBeskrivning")
        protected String ravarorBeskrivning;

        public String getNr() {
            return nr;
        }

        public void setNr(String value) {
            this.nr = value;
        }

        public String getArtikelid() {
            return artikelid;
        }

        public void setArtikelid(String value) {
            this.artikelid = value;
        }

        public String getVarnummer() {
            return varnummer;
        }

        public void setVarnummer(String value) {
            this.varnummer = value;
        }

        public String getNamn() {
            return namn;
        }

        public void setNamn(String value) {
            this.namn = value;
        }

        public String getNamn2() {
            return namn2;
        }

        public void setNamn2(String value) {
            this.namn2 = value;
        }

        public String getPrisinklmoms() {
            return prisinklmoms;
        }

        public void setPrisinklmoms(String value) {
            this.prisinklmoms = value;
        }

        public String getPant() {
            return pant;
        }

        public void setPant(String value) {
            this.pant = value;
        }

        public String getVolymiml() {
            return volymiml;
        }

        public void setVolymiml(String value) {
            this.volymiml = value;
        }

        public String getPrisPerLiter() {
            return prisPerLiter;
        }

        public void setPrisPerLiter(String value) {
            this.prisPerLiter = value;
        }

        public String getSaljstart() {
            return saljstart;
        }

        public void setSaljstart(String value) {
            this.saljstart = value;
        }

        public String getSlutlev() {
            return slutlev;
        }

        public void setSlutlev(String value) {
            this.slutlev = value;
        }

        public String getVarugrupp() {
            return varugrupp;
        }

        public void setVarugrupp(String value) {
            this.varugrupp = value;
        }

        public String getForpackning() {
            return forpackning;
        }

        public void setForpackning(String value) {
            this.forpackning = value;
        }

        public String getForslutning() {
            return forslutning;
        }

        public void setForslutning(String value) {
            this.forslutning = value;
        }

        public String getUrsprung() {
            return ursprung;
        }

        public void setUrsprung(String value) {
            this.ursprung = value;
        }

        public String getUrsprunglandnamn() {
            return ursprunglandnamn;
        }

        public void setUrsprunglandnamn(String value) {
            this.ursprunglandnamn = value;
        }

        public String getProducent() {
            return producent;
        }

        public void setProducent(String value) {
            this.producent = value;
        }

        public String getLeverantor() {
            return leverantor;
        }

        public void setLeverantor(String value) {
            this.leverantor = value;
        }

        public String getArgang() {
            return argang;
        }

        public void setArgang(String value) {
            this.argang = value;
        }

        public String getProvadargang() {
            return provadargang;
        }

        public void setProvadargang(String value) {
            this.provadargang = value;
        }

        public String getAlkoholhalt() {
            return alkoholhalt;
        }

        public void setAlkoholhalt(String value) {
            this.alkoholhalt = value;
        }

        public String getModul() {
            return modul;
        }

        public void setModul(String value) {
            this.modul = value;
        }

        public String getSortiment() {
            return sortiment;
        }

        public void setSortiment(String value) {
            this.sortiment = value;
        }

        public String getEkologisk() {
            return ekologisk;
        }

        public void setEkologisk(String value) {
            this.ekologisk = value;
        }

        public String getKoscher() {
            return koscher;
        }

        public void setKoscher(String value) {
            this.koscher = value;
        }

        public String getRavarorBeskrivning() {
            return ravarorBeskrivning;
        }

        public void setRavarorBeskrivning(String value) {
            this.ravarorBeskrivning = value;
        }

    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "meddelande"
    })
    public static class Info {

        protected String meddelande;

        public String getMeddelande() {
            return meddelande;
        }

        public void setMeddelande(String value) {
            this.meddelande = value;
        }

    }

}
