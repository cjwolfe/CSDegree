package ENG.Verizon;

public class CellTower {
    String towerID = "asdf";
    boolean available = false;

    CellPhone[] phonesConnected = new CellPhone[10];
    int numPhones = 0;

    public void search(){
        if(!avail()){
            available = true;
        }
    }

    public boolean connectPhone(CellPhone phone){
        if(avail()){
            System.out.println("Connected to " + phone.getNumber());
            phone.connect(this);
            phonesConnected[numPhones] = phone;
            numPhones++;
        } else {
            status();
        }
        // not correct
        return phone.isConnected();
    }
    public void disconnectPhone(){
        available = false;
        for(int i = phonesConnected.length-1; i > 0; i--){
            try{
            phonesConnected[i].disconnect(this);
            } catch(NullPointerException e){
                System.out.println(e + " FUCK! unable to disconnect");
            }
        }
        status();
    }

    public boolean avail(){
        return available && numPhones < phonesConnected.length;
    }

    public void status(){

        System.out.println("Tower " + towerID + " is " + (available ? "available": "busy"));
    }

    public void setPhoneNumber(String number){
        String newNumber = number;
        boolean result = phonesConnected[0].setNumber(newNumber);
        System.out.println((result ? "Successfully" : "Unsuccessfully") + " set phone to " + newNumber);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Tower " + towerID + " is " + (available ? "available": "busy"));

        sb.append("\nTower ID: " + this.towerID);

        return sb.toString();
    }
}
