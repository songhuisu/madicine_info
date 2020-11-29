package com.example.android_medicine_info;

public class MedicineVO {
    private String item_name;
    private String entp_name;
    private String item_seq;
    private String img_regist_ts;
    private String pageNo;
    private String numOfRows;
    private String edi_code;

    public String getItem_name() {
        return item_name;
    }

    public MedicineVO(String item_name, String entp_name, String item_seq, String img_regist_ts, String pageNo, String numOfRows, String edi_code) {
        this.item_name = item_name;
        this.entp_name = entp_name;
        this.item_seq = item_seq;
        this.img_regist_ts = img_regist_ts;
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
        this.edi_code = edi_code;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getEntp_name() {
        return entp_name;
    }

    public void setEntp_name(String entp_name) {
        this.entp_name = entp_name;
    }

    public String getItem_seq() {
        return item_seq;
    }

    public void setItem_seq(String item_seq) {
        this.item_seq = item_seq;
    }

    public String getImg_regist_ts() {
        return img_regist_ts;
    }

    public void setImg_regist_ts(String img_regist_ts) {
        this.img_regist_ts = img_regist_ts;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(String numOfRows) {
        this.numOfRows = numOfRows;
    }

    public String getEdi_code() {
        return edi_code;
    }

    public void setEdi_code(String edi_code) {
        this.edi_code = edi_code;
    }
}
