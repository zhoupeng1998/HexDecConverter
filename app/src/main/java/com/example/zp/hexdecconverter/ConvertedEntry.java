package com.example.zp.hexdecconverter;

public class ConvertedEntry {

    private String strDec;
    private String strHex;
    private String id;

    public ConvertedEntry () {
        this.strDec = "NA";
        this.strHex = "NA";
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
}
