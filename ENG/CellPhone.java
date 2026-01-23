package ENG;

public class CellPhone {
    String name = "acellphone";
    int number = 1234567890;
    public void connect(CellTower tower){
        //connect to a tower
        boolean success = false;
        if(success){
            System.out.println("Connected to " + tower.getName());
        }

    }
    public void disconnect(CellTower tower){
        //disconnect from a tower
        //should connect to next available tower?
    }

    public String getName(){
        return this.name;
    }

    public void disconnect(){
        
    }

    // @Override
    // public String toString(){
    //     StringBuilder sb = new StringBuilder();
    //     for(int i = 0; i < number.length; i++){
    //         sb.append(number.i);
    //     }

    //     return sb.toString();
    // }


}
