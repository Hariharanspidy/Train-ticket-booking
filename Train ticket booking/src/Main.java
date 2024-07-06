import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Booking b=new Booking();
        while (true){
            System.out.println("1.Ticket Booking\n2.Cancel Booking\n3.Print Booking\n4.Available Booking\n5.Exit\n");
            int input=sc.nextInt();
            switch (input){
                case 1:
                    System.out.println("Enter your Name:");
                    String name= sc.next();
                    System.out.println("Enter your Age:");
                    int age=sc.nextInt();
                    System.out.println("Enter your Preference:");
                    String preference= sc.next();
                    b.trainTicketBooking(age,name,preference);
                    break;
                case 2:
                    System.out.println("Enter the passenger ID:");
                    int id=sc.nextInt();
                    b.cancelBooking(id);
                    break;
                case 3:
                    System.out.println("Enter the passenger ID:");
                    int id1=sc.nextInt();
                    b.printBookings(id1);
                    break;
                case 4:
                    b.printAvailableTickets();
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
}