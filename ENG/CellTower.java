package ENG;

public class CellTower {
    String name = "acelltower";
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        if (!name.isEmpty()){
            this.name = name;
        }
    }

    public void disconnect(){

    }
}
