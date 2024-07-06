public class PassengerData {
    private int passengerID;
    private String passengerName;
    private int passengerAge;
    private String passengerPreference;
    private String passengerSeatNo;
    public  PassengerData(int passengerAge,String passengerName,String passengerPreference,String passengerSeatNo,int passengerID){
        this.passengerAge=passengerAge;
        this.passengerName=passengerName;
        this.passengerPreference=passengerPreference;
        this.passengerSeatNo=passengerSeatNo;
        this.passengerID=passengerID;
    }
    public int getPassengerID(){
        return passengerID;
    }
    public String getPassengerName(){
        return passengerName;
    }
    public String getPassengerPreference(){
        return passengerPreference;
    }
    public String getPassengerSeatNo(){
        return passengerSeatNo;
    }
    public int getPassengerAge(){
        return passengerAge;
    }
}
