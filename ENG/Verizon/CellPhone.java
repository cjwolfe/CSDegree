package ENG.Verizon;

public class CellPhone {
    
    private boolean connected = false;
    private String number = "1234567890";
    private CellTower tower;
    public void connect(CellTower tower){
        if(tower.avail()){
            this.tower = tower;
            if(this.tower.equals(tower)){
                connected = true;
                System.out.println("Cell phone " + number + " is connected to " + tower.towerID);
            }
        } else {
            System.out.println("No Tower Connection Available");
        }
    }

    public void disconnect(CellTower tower){
        this.tower = null;
    }

    public String getNumber(){
        return this.number;
    }

    public boolean setNumber(String newNumber){
        this.number = newNumber;
        return this.number.equals(newNumber);
    }

    public boolean isConnected(){
        return connected;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Phone connection " + isConnected());
        sb.append("\nPhone number " + getNumber());

        return sb.toString();
    }


}
