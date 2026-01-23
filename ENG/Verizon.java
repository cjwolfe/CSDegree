package ENG;
public class Verizon{

    CellTower tower = new CellTower();
    CellPhone phone = new CellPhone();


    public void Verizon(){
        this.tower = new CellTower();
        this.phone = new CellPhone();
    }

    public String printConnections(){
        StringBuilder sb = new StringBuilder();
        sb.append(phone.getName());
        sb.append(" phone is connected to tower ");
        sb.append(tower.getName());

        return sb.toString();
    }
//TODO: Fix so that this runs intelligently if no input is given

    public static void main(String[] args){
        Verizon vzw = new Verizon();
        System.out.println(vzw.printConnections());
        
    }
}