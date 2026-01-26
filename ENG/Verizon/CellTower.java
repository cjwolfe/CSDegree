package ENG.Verizon;

public class CellTower {
    String towerID = "T-1000";
    boolean available = false;

    CellPhone activePhone = new CellPhone();

    CellPhone[] phoneList = new CellPhone[10];
    int numPhones = 0;

    // public void search(){
    //     if(!avail()){
    //         available = true;
    //     }
    // }

    public boolean connectPhone(CellPhone phone){
        if(avail()){
            this.activePhone = phone;
            activePhone.connect(this);
            System.out.println("Connected to " + activePhone.getNumber());
            return true;
            // phoneList[numPhones] = phone;
            // numPhones++;

        } else {
            status();
            return avail();
        }
    }

    public void disconnectPhone(){
        if(activePhone != null){
            activePhone.disconnect(this);
            activePhone = null;
            status();
        }

    }

    public boolean avail(){
        return activePhone == null;
    }

    public void status(){

        System.out.println("Tower " + towerID + " is " + (available ? "available": "busy"));
    }

    public void setPhoneNumber(String number){
        if(activePhone!= null){
            activePhone.setNumber(number);
            System.out.println((activePhone.getNumber().equals(number) ? "Successfully" : "Unsuccessfully") + " set phone to " + number);
        }        
        // boolean result = activePhone.getNumber() == number;
        // System.out.println((result ? "Successfully" : "Unsuccessfully") + " set phone to " + number);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Tower " + towerID + " is " + (available ? "available": "busy"));

        sb.append("\nTower ID: " + this.towerID);

        return sb.toString();
    }
}
