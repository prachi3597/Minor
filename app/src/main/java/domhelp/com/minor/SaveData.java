package domhelp.com.minor;

public class SaveData {
    String UID,Name,Month,Work;
    int Date,Year;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getWork() {
        return Work;
    }

    public void setWork(String work) {
        Work = work;
    }

    public int getDate() {
        return Date;
    }

    public void setDate(int date) {
        Date = date;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public SaveData(String UID, String name, int date, String month, int year, String work ) {

        Name = name;
        Month = month;
        Work = work;
        UID = UID;
        Date = date;
        Year = year;
    }
}
