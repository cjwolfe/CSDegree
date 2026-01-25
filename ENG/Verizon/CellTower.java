package ENG.Verizon;

public class CellTower {
    String towerID = "";
    boolean available = false;

    // String[] phonesConnected = new String[10];

    public boolean connectPhone(CellPhone phone){
        if(avail()){
            System.out.println("Connected to " + phone.getNumber());
            phone.connect(this);
        } else {
            status();
        }
        // not correct
        return phone.isConnected();
    }
    public void disconnectPhone(){
        available = false;
        status();
    }

    public boolean avail(){
        return available;
    }

    public void status(){

        System.out.println("Tower " + towerID + " is " + (available ? "available": "busy"));
    }
}
