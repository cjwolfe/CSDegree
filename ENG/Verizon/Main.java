package ENG.Verizon;

public class Main{

    public static CellTower tower = new CellTower();
    public static CellPhone phone = new CellPhone();


    // public void Verizon(){
    //     this.tower = new CellTower();
    //     this.phone = new CellPhone();
    // }

    public static String status(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nSTATUS REPORT: ");
        sb.append("\nStatus of phone: ");
        sb.append(phone.toString());

        sb.append("\nStatus of Tower: ");
        sb.append(tower.toString() + "\n");

        return sb.toString();
    }
//TODO: Fix so that this runs intelligently if no input is given

    public static void main(String[] args){

        System.out.println(status());

        System.out.println("Connecting Phone");

        tower.search();
        phone.connect(tower);

        System.out.println("Disconnecting Phone");

        tower.disconnectPhone();

        System.out.println(status());

        System.out.println("Change phone number");
        tower.setPhoneNumber("01189998819991197253");
        
        
    }
}