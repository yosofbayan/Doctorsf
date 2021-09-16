package com.yb.doctors.model;

public class Sector {
private String nameAr ,nameEn ,nameKu , nameTr ,imageKey , keyCode ,number ;

    public Sector(String nameAr, String nameEn, String nameKu, String nameTr, String imageKey, String keyCode, String number) {
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.nameKu = nameKu;
        this.nameTr = nameTr;
        this.imageKey = imageKey;
        this.keyCode = keyCode;
        this.number = number;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameKu() {
        return nameKu;
    }

    public void setNameKu(String nameKu) {
        this.nameKu = nameKu;
    }

    public String getNameTr() {
        return nameTr;
    }

    public void setNameTr(String nameTr) {
        this.nameTr = nameTr;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
