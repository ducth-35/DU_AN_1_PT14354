package company.huynhduc.model;

import java.io.Serializable;



public class LoaiMonAn implements Serializable{
    private String TenLoai;
    private String LinkURL;
    public LoaiMonAn() { }
    public LoaiMonAn(String tenLoai, String LinkURL) {
        this.LinkURL=LinkURL;
        TenLoai = tenLoai;
    }
    public String getTenLoai() {
        return TenLoai;
    }
    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }
    public String getLinkURL() {
        return LinkURL;
    }
    public void setLinkURL(String linkURL) {
        LinkURL = linkURL;
    }
}
