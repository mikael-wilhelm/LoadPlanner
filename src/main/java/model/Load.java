package model;


public class Load {
    private String content;
    private String harbor;
    private int id;



    public Load(String content, String harbor){
        this.content = content;
        this.harbor = harbor;
    }

    public String getContent() {
        return content;
    }

    public String getHarbor() {
        return harbor;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHarbor(String harbor) {
        this.harbor = harbor;
    }
}
