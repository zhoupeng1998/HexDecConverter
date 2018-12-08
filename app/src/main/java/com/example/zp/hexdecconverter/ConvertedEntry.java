package com.example.zp.hexdecconverter;

public class ConvertedEntry {

    private String strDec;
    private String strHex;
    private String id;

    public ConvertedEntry () {
        this.strDec = "NaN";
        this.strHex = "NaN";
    }

    public ConvertedEntry (String dec, String hex, String id) {
        this.strDec = dec;
        this.strHex = hex;
        this.id = id;
    }


    public ConvertedEntry (String dec, String hex) {
        this.strDec = dec;
        this.strHex = hex;
    }

    public String getDec() {
        return strDec;
    }

    public String getHex() {
        return strHex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setdec(String strDec) {this.strDec = strDec; }

    public void sethex(String strHex) {this.strHex = strHex; }

    public String toString() {
        return strDec + ": " + strHex;
    }
}
