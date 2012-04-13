package model;


public class Load {
    private String content;
    private String harbor;



    private int id;
    private boolean reserved = false;
    private String destination;


    public Load(String content, String harbor,int id){
        this.content = content;
        this.harbor = harbor;
        this.id = id;
    }

    public Load(String content, String harbor,String destination, int id){
        this.content = content;
        this.harbor = harbor;
        this.id = id;
        this.destination = destination;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Load)) return false;

        Load load = (Load) o;

        if (id != load.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
