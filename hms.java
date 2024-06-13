import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.Console;
import java.util.HashMap;
class Hotel{
    HashMap<Integer, Integer> d = new HashMap<Integer, Integer>();
    HashMap<String, String> pswd = new HashMap<String,String>();
    HashMap<String, Integer> foodd = new HashMap<String,Integer>();
    static ArrayList<String> na = new ArrayList<String>();
    static ArrayList<String> ph = new ArrayList<String>();
    static ArrayList<Integer> floor = new ArrayList<Integer>();
    static ArrayList<String> room = new ArrayList<String>();
    static ArrayList<String> indate = new ArrayList<String>();
    static ArrayList<String> outdate = new ArrayList<String>();
    static ArrayList<Integer> rbill = new ArrayList<Integer>();
    static ArrayList<Integer> fbil = new ArrayList<Integer>();
    static ArrayList<Integer> totb = new ArrayList<Integer>();
    static ArrayList<Integer> dayss = new ArrayList<Integer>();
    static ArrayList<Integer> bii = new ArrayList<Integer>();
    void roomalloc(){
        d.put(1,15);
        d.put(2,15);
        d.put(3,15);
        d.put(4,15);
        d.put(5,15);
    }
    void rooms(){
        
        for (int i : d.keySet()) {
            System.out.println("Floor " + i + ": " + d.get(i) + " rooms available");
        }
    }
    public static int getDifference(LocalDate date1, LocalDate date2) {
        return (int) java.time.temporal.ChronoUnit.DAYS.between(date1, date2);
    }
    String psch(){
        Scanner scanner = new Scanner(System.in);
        String str1 = "";
        String str2 = "";

        do {
            System.out.print("Enter password: ");
            str1 = scanner.nextLine();

            System.out.print("Re-Enter the password: ");
            str2 = scanner.nextLine();

            if (!str1.equals(str2)) {
                System.out.println("The passwords are not matching.Please try again.\n");
            }
            else{
                return(str1);
            }

        } while (!str1.equals(str2));

        System.out.println("The strings are the same. Exiting program.");
        return("Yes");
    }
    void details(){
        System.out.println("--------------------------BOOKING OF ROOMS--------------------------------------");
        System.out.println("Enter your name");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        na.add(name);
        System.out.println("Enter your mobile number:");
        String phn = sc.nextLine();
        while (phn.length()!=10){
        System.out.println("Invalid moblie number!!!\nPlease enter again:");
        phn= sc.nextLine();
        }
        ph.add(phn);
        System.out.println("Enter which floor you wanna book:");
        int fl= sc.nextInt();
        floor.add(fl);
        for (int key : d.keySet()) {
            if (key == fl) {
                d.put(fl, d.get(fl) - 1);
            }
        }
        System.out.println("1.Deluxe----------Rs.5000\n2.Semi Deluxe------Rs.4000\n3.Regular--------Rs.3000");
        System.out.println("\nEnter your choice:");
        int roo= sc.nextInt();
        int bi;
        if (roo==1){
            bi=5000;
            bii.add(bi);
            room.add("Deluxe");
        }
        else if (roo==2){
            bi=4000;
            bii.add(bi);
            room.add("Semi Deluxe");
        }
        else{
            bi=3000;
            bii.add(bi);
            room.add("Regular");
        }

        System.out.print("Enter check in date: ");
        int d1 = sc.nextInt();
        System.out.print("Enter check in month: ");
        int m1 = sc.nextInt();
        System.out.print("Enter check in year: ");
        int y1 = sc.nextInt();
        indate.add(d1 + "/" + m1 + "/" + y1);

        System.out.print("Enter check out date: ");
        int d2 = sc.nextInt();
        System.out.print("Enter check out month: ");
        int m2 = sc.nextInt();
        System.out.print("Enter check out year: ");
        int y2 = sc.nextInt();
        outdate.add(d2 + "/" + m2 + "/" + y2);

        LocalDate da1 = LocalDate.of(y1, m1, d1);
        LocalDate da2 = LocalDate.of(y2, m2, d2);
        int days = getDifference(da1, da2);
        dayss.add(days);
        rbill.add(bi*days);
        String password=psch();
        pswd.put(name,password);
        foodd.put(name,0);
        System.out.println(pswd);
        System.out.println("\nROOM BOOKED SUCCESSFULLY\n");
    }
    int check(String nam){
        int e=0;
            while (e<na.size()){
                if (nam.equals(na.get(e))){
                    return(e);
                }
                e=e+1;
            }
            return-1;
    }

     void existcus(){
            Scanner sc = new Scanner(System.in);
            System.out.println("\nEnter your name:");
            String nam=sc.nextLine();
            int u=check(nam);
            if (!pswd.containsKey(nam)) {
                System.out.println("Customer doesnt exist!!!");
                return;
            }
            System.out.println("\nEnter your password:");
            String psw=sc.nextLine();
            System.out.println(pswd.get(nam));
            String pss=pswd.get(nam);
            if (!pss.equals(psw)){
                System.out.println("\nIncorrect Password!!!");
                return;
            }
            int p=0;
        while (p<1){
            System.out.println("1.Food order\n2.Calculate bill\n3.Exit");
            System.out.println("\nEnter your choice:");
            int q = sc.nextInt();
            if (q==1) {
                System.out.println("\n1.Breakfast------Rs.150\n2.Lunch----------Rs.300\n3.Dinner---------Rs.300\n4.Snacks---------Rs.100\n5.Beverages------Rs.200\n");
                System.out.println("\nEnter your choice:");
                int f = sc.nextInt();
                if (f==1) {
                    foodd.put(nam,foodd.get(nam)+150);
                }
                else if (f==2) {
                    foodd.put(nam,foodd.get(nam)+300);
                }
                else if (f==3){
                    foodd.put(nam,foodd.get(nam)+300);
                }
                else if (f==4){
                    foodd.put(nam,foodd.get(nam)+100);
                }
                else if (f==5){
                    foodd.put(nam,foodd.get(nam)+200);
                }
            }
            else if (q==2) {
                System.out.println("\nChecking out!!!!\n\nYour food bill is:"+(foodd.get(nam)));
                fbil.add(foodd.get(nam));
                int tb=(bii.get(u)*dayss.get(u))+foodd.get(nam);
                totb.add(tb);
                System.out.println("Your total bill is :"+(tb)+"\n");
                int fl=floor.get(u);
                for (int key : d.keySet()) {
                    if (key == fl) {
                        d.put(fl, d.get(fl) + 1);
                    }
                }
                p=p+1;
            }
            else {
                return;
            }
        }
    }
    void display(){
        int i=0;
        System.out.println("-------------------------------------------RECORD OF CUSTOMERS--------------------------------");
        while (i<na.size()) {
            System.out.println("Name:"+na.get(i));
            System.out.println("Phone number:"+(ph.get(i)));
            System.out.println("Floor booked:"+(floor.get(i)));
            System.out.println("Room reserved:"+room.get(i));
            System.out.println("Check in date:"+indate.get(i));
            System.out.println("Check out date:"+outdate.get(i));
            System.out.println("Room bill:"+(rbill.get(i)));
            System.out.println("Food bill:"+(fbil.get(i)));
            System.out.println("TOTAL BILL:"+(totb.get(i)));
            System.out.println("\n");
            i=i+1;
        }
    }
}
class hms{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Hotel c=new Hotel();
        c.roomalloc();
        System.out.println("------------------------------HOTEL MANAGEMENT SYSTEM----------------------");
        while (true){
            System.out.println("\n1.If you are a new customer\n2.If you are a existing customer");
            System.out.println("3.Record of customers\n");
            System.out.println("Enter your choice:");
            int n = sc.nextInt();
            if (n==1) {
                int m=0;
                while (m<1) {
                    System.out.println("\n1.Avalaibility of rooms");
                    System.out.println("2.Booking of rooms");
                    System.out.println("\nEnter your choice:");
                    int n1 = sc.nextInt();
                    if (n1==1) {
                        System.out.println("--------------------------ROOMS AVAILABLE ARE----------------------------------");
                        c.rooms();
                    }
                    else{
                        c.details();
                        m=m+1;
                    }
                }
            }
            else if (n==2){
                c.existcus();
            }
            else{
                c.display();
            }
        }
    }
}