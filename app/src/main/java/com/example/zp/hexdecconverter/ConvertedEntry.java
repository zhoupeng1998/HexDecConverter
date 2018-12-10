//Author: Linchuan Chen
//Converted entry object class

package com.example.zp.hexdecconverter;

public class ConvertedEntry {

    private String dec;
    private String hex;
    private String id;

    public ConvertedEntry () {
        this.dec = "NaN";
        this.hex = "NaN";
    }

    public ConvertedEntry (String dec, String hex, String id) {
        this.dec = dec;
        this.hex = hex;
        this.id = id;
    }


    public ConvertedEntry (String dec, String hex) {
        this.dec = dec;
        this.hex = hex;
    }

    public String getDec() {
        return dec;
    }

    public String getHex() {
        return hex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return dec + ": " + hex;
    }
}
