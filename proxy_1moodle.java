interface Image {
    void show();
}

class RealImage implements Image {
    String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    void loadFromDisk() {
        System.out.println("--> [SLOW] Reading " + fileName + " from hard drive...");
    }

    public void show() {
        System.out.println("Displaying " + fileName + " on screen!");
    }
}

class ProxyImage implements Image {
    RealImage realImage;
    String fileName;
    String userRole;




    public ProxyImage(String fileName , String userRole) {
        this.fileName = fileName;
    }

    public void show() {
        if (userRole != "ADMIN") {
            System.out.println("ACCESS DENIED] You lack permission to view: <fileName>");
            return;

        }
        if (userRole == "ADMIN") {
            realImage.show();

        }
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.show();
    }
}

public class Main {
    public static void main(String[] args) {
        Image guest = new ProxyImage("salary_sheet.png", "GUEST");
        Image admin = new ProxyImage("salary_sheet.png", "ADMIN");

        guest.show();
        admin.show();
    }
}
