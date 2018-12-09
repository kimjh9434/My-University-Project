package UnitList;

public class UnitManager {

    private boolean isLive;//유닛의 생존여부

    public UnitManager() {
        isLive = true;
    }

    //유닛이 살아있는지 물어보는 메소드
    public boolean isLive() {
        return isLive;
    }

    //유닛이 죽었음을 표시하는 메소드
    public void isDie() {
        isLive = false;
    }
}
