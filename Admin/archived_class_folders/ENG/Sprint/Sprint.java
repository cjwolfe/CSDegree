package ENG.Sprint;

import ENG.Sprint.CellPhone;
import ENG.Sprint.CellTower;


/**
 * @author cwolfe
 */

public class Sprint{

    // public static CellTower tower = new CellTower();
    public static CellPhone phone = new CellPhone();
    public static Network sprint;


    public static String advertised(){
        String[] lol = {
            "PIZZA HUT",
            "DOMINOS",
            "CHOLULA",            
            "BEST BUY",
            "YOUTUBE",
            "MA'S CHICKEN",            
            "SCOOBY DOO",
            "MICROSLOP",
            "REDTUBE",
            "BING.COM",
            "LINUX FOUNDATION"
      
        };
        int spicy = (int) (Math.random()*11);
        // System.out.println("\n"+spicy);
        return lol[spicy];
    }
    // public void Verizon(){
    //     this.tower = new CellTower();
    //     this.phone = new CellPhone();
    // }

    public static String status(){
        StringBuilder sb = new StringBuilder();
        CellTower active = sprint.getActiveTower();
        sb.append("\nSTATUS REPORT BROUGHT TO YOU \n"+
        "BY THE "+ advertised() + " PLAY OF THE GAME: ");
        
        sb.append("\nStatus of phone: ");
        if (active.activePhone != null) {
            sb.append(active.activePhone.toString());
        } else {
            sb.append("No phone connected");
        }

        sb.append("\nStatus of Tower: ");
        sb.append(active.toString() + "\n");
        return sb.toString();
    }
//TODO: Fix so that this runs intelligently if no input is given

    public static void main(String[] args){
        sprint = new Network();

        // Print the null status
        System.out.println(status());

        //Activate the network, which has it's own towers.

        System.out.println("Connecting Phone");
        CellTower tower = sprint.getAvailTower();
        if(tower != null){
            phone.connect(tower);
        } else {
            System.out.println("No towers available in range");
        }
        // phone.connect(sprint.getAvailTower());

        // System.out.println("Disconnecting Phone");

        // tower.disconnectPhone();


        System.out.println("Change phone number");
        sprint.getActiveTower().setPhoneNumber("01189998819991197253");
        


        sprint.getActiveTower().setPhoneNumber("8675309");

        CellPhone phone2 = new CellPhone();

        CellTower tower2 = sprint.getAvailTower();
                if(tower2 != null){
            phone2.connect(tower2);
        } else {
            System.out.println("No towers available in range");
        }
        sprint.getActiveTower().setPhoneNumber("91890987234509");

        System.out.println(status());

        System.out.println("Adding more functionality like stepping through available towers \nwould require more work than I wish to do at 8pm on a thursday");

        
    }
}