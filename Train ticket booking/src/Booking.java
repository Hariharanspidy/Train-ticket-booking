import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Booking {
    private int availableLowerBerth = 1;
    private int availableUpperBerth = 1;
    private int availableMiddleBerth = 1;
    private int availableRACTickets = 1;
    private int waitingList = 1;
    private int passengerID = 0;
    ArrayList<PassengerData> passengerList = new ArrayList<>();
    Queue<PassengerData> passengerRACList = new LinkedList<>();
    Queue<PassengerData> passengerWaitingList = new LinkedList<>();

    public void printAvailableTickets() {
        System.out.println("Tickets available in lower berth:" + availableLowerBerth + "\n" +
                "Tickets available in Middle berth:" + availableMiddleBerth + "\n" +
                "Tickets available in Upper berth:" + availableUpperBerth + "\n" +
                "Tickets available in RAC:" + availableRACTickets + "\n" +
                "Tickets available in waiting list:" + waitingList+"\n");
    }

    public void trainTicketRacUpdating() {
        String seatNo = "";
        int passengerAge = passengerRACList.peek().getPassengerAge();
        String passengerName = passengerRACList.peek().getPassengerName();
        String passengerPreference = passengerRACList.peek().getPassengerPreference();
        int passengerId = passengerRACList.peek().getPassengerID();
        if ((passengerPreference.equals("L") && availableLowerBerth > 0) ||
                (passengerPreference.equals("M") && availableMiddleBerth > 0) ||
                (passengerPreference.equals("U") && availableUpperBerth > 0)) {
            switch (passengerPreference) {
                case "L":
                    seatNo = availableLowerBerth+ "L";
                    --availableLowerBerth;
                    break;
                case "M":
                    seatNo = availableMiddleBerth+ "M";
                    --availableMiddleBerth;
                    break;
                case "U":
                    seatNo = availableUpperBerth + "U";
                    --availableUpperBerth;
                    break;
            }

        } else if (availableUpperBerth > 0) {
            seatNo = availableUpperBerth + "U";
            --availableUpperBerth;
        } else if (availableLowerBerth > 0) {
            seatNo = availableLowerBerth+ "L";
            --availableLowerBerth;
        } else if (availableMiddleBerth > 0) {
            seatNo = availableMiddleBerth + "M";
            --availableMiddleBerth;
        }
        passengerList.add(new PassengerData(passengerAge, passengerName, passengerPreference, seatNo, passengerId));
        --availableRACTickets;
        passengerRACList.poll();
        ++waitingList;
        ticketWaitingListUpdating();
    }

    public void ticketWaitingListUpdating() {
        String seatNo = availableRACTickets+ "RAC";
        int passengerAge = passengerWaitingList.peek().getPassengerAge();
        String passengerName = passengerWaitingList.peek().getPassengerName();
        String passengerPreference = passengerWaitingList.peek().getPassengerPreference();
        int passengerId = passengerWaitingList.peek().getPassengerID();
        passengerList.add(new PassengerData(passengerAge, passengerName, passengerPreference, seatNo, passengerId));
        passengerWaitingList.poll();
    }

    public void trainTicketBooking(int passengerAge, String passengerName, String passengerPreference) {
        String seatNo = "";
        if (waitingList == 0) {
            System.out.println("No Tickets available\n");
        }
        if (availableUpperBerth == 0 && availableMiddleBerth ==0 && availableLowerBerth == 0) {
            if (availableRACTickets > 0) {
                seatNo = availableRACTickets + "RAC";
                --availableRACTickets;
                passengerRACList.add(new PassengerData(passengerAge, passengerName, passengerPreference, seatNo, ++passengerID));
                System.out.println("Ticket Booked Successfully in RAC \n" +
                        "Passenger Id:" + passengerID + "\n" +
                        "Seat Number:" + seatNo+"\n");

            } else if (waitingList > 0) {
                seatNo = "Waiting";
                --waitingList;
                passengerWaitingList.add(new PassengerData(passengerAge, passengerName, passengerPreference, seatNo, ++passengerID));
                System.out.println("Ticket Under Waiting list\n" +
                        "Passenger Id:" + passengerID+"\n");
            }

        } else {
            if ((passengerPreference.equals("L") && availableLowerBerth > 0) ||
                    (passengerPreference.equals("M") && availableMiddleBerth > 0) ||
                    (passengerPreference.equals("U") && availableUpperBerth > 0)) {

                System.out.println("Tickets available in preferred berth");

                switch (passengerPreference) {
                    case "L":
                        seatNo = Integer.toString(availableLowerBerth) + "L";
                        --availableLowerBerth;
                        break;
                    case "M":
                        seatNo = Integer.toString(availableMiddleBerth) + "M";
                        --availableMiddleBerth;
                        break;
                    case "U":
                        seatNo = Integer.toString(availableUpperBerth) + "U";
                        --availableUpperBerth;
                        break;
                }

            } else if (availableUpperBerth > 0) {
                seatNo = Integer.toString(availableUpperBerth) + "U";
                --availableUpperBerth;

            } else if (availableLowerBerth > 0) {
                seatNo = Integer.toString(availableLowerBerth) + "L";
                --availableLowerBerth;

            } else if (availableMiddleBerth > 0) {
                seatNo = Integer.toString(availableMiddleBerth) + "M";
                --availableMiddleBerth;

            }
            passengerList.add(new PassengerData(passengerAge, passengerName, passengerPreference, seatNo, ++passengerID));
            System.out.println("Ticket Booked Successfully \n" +
                    "Passenger Id:" + passengerID + "\n" +
                    "Seat Number:" + seatNo+"\n");
        }

    }

    public void cancelBooking(int id) {
        Iterator<PassengerData> passengerlist = passengerList.iterator();
        Iterator<PassengerData> passengerRac = passengerRACList.iterator();
        Iterator<PassengerData> passengerWaiting = passengerWaitingList.iterator();
        while (passengerlist.hasNext()) {
            PassengerData i = passengerlist.next();
            char berth = i.getPassengerSeatNo().charAt(i.getPassengerSeatNo().length() - 1);
            if (i.getPassengerID() == id) {
                switch (berth) {
                    case 'L':
                        ++availableLowerBerth;
                        break;
                    case 'M':
                        ++availableMiddleBerth;
                        break;
                    case 'U':
                        ++availableUpperBerth;
                        break;
                }
                passengerlist.remove();
                ++availableRACTickets;
                trainTicketRacUpdating();
                System.out.println("Ticket canceled successfully");
                return;
            }
        }

        while (passengerRac.hasNext()) {
            PassengerData j = passengerRac.next();
            if (j.getPassengerID() == id) {
                ++availableRACTickets;
                passengerRac.remove();
                ticketWaitingListUpdating();
                System.out.println("Ticket canceled successfully");
                return;
            }
        }
        while (passengerWaiting.hasNext()) {
            PassengerData k = passengerWaiting.next();
            if (k.getPassengerID() == id){
                ++waitingList;
                passengerWaiting.remove();
                System.out.println("Ticket canceled successfully");
                return;
            }
        }
    }

    public void printBookings(int id) {
        Iterator<PassengerData> passengerlist = passengerList.iterator();
        Iterator<PassengerData> passengerRac = passengerRACList.iterator();
        Iterator<PassengerData> passengerWaiting = passengerWaitingList.iterator();
        while (passengerlist.hasNext()) {
            PassengerData i = passengerlist.next();
            if (i.getPassengerID() == id) {
                System.out.println("Passenger ID:" + i.getPassengerID() + "\n" +
                        "Passenger Name:" + i.getPassengerName() + "\n" +
                        "Passenger Age:" + i.getPassengerAge() + "\n" +
                        "Passenger Seat Number:" + i.getPassengerSeatNo());
                return;
            }

            while (passengerRac.hasNext()) {
                PassengerData j = passengerRac.next();
                if (j.getPassengerID() == id) {
                    System.out.println("Passenger ID:" + j.getPassengerID() + "\n" +
                            "Passenger Name:" + j.getPassengerName() + "\n" +
                            "Passenger Age:" + j.getPassengerAge() + "\n" +
                            "Passenger Seat Number:" + j.getPassengerSeatNo());
                    return;
                }
            }
            while (passengerWaiting.hasNext()) {
                PassengerData k = passengerWaiting.next();
                if (k.getPassengerID() == id) {
                    System.out.println("Passenger ID:" + k.getPassengerID() + "\n" +
                            "Passenger Name:" + k.getPassengerName() + "\n" +
                            "Passenger Age:" + k.getPassengerAge() + "\n" +
                            "Passenger Seat Number:" + k.getPassengerSeatNo());
                    return;
                }
            }
        }
    }
}
