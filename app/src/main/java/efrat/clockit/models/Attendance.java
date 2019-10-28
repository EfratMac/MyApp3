package efrat.clockit.models;

public class Attendance {

    private String date;
    private String in;
    private String out;

    int id;

    public Attendance(String date, String in, String out) {
        this.date=date;
        this.in = in;
        this.out = out;

    }


    public String getIn() { return in; }
    public String getOut() {  return out; }
    public String getDate() {  return date; }

    
    @Override
    public String toString() {
        return "Attendance{" +
                "date='" + date + '\'' +
                ", in='" + in + '\'' +
                ", out='" + out + '\'' +
                '}';
    }
}
