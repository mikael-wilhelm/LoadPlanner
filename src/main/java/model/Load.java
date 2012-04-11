package model;


public class Load {
    private String content;
    private String harbor;



    private int id;
    private boolean reserved = false;



    public Load(String content, String harbor,int id){
        this.content = content;
        this.harbor = harbor;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getReserved(){
        return reserved;
    }

    public void setReserved(boolean  reserved){
        this.reserved = reserved;
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
