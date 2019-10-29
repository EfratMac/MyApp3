package efrat.clockit.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Attendance implements Parcelable {

    private String date;
    private String in;
    private String out;
    private String total;

    int id;

    public Attendance(String date, String in, String out) {
        this.date=date;
        this.in = in;
        this.out = out;

    }

    public Attendance(String date, String in, String out,String total) {
        this.date=date;
        this.in = in;
        this.out = out;
        this.total=total;

    }


    protected Attendance(Parcel parcel) {
        date = parcel.readString();
        in = parcel.readString();
        out = parcel.readString();
        id = parcel.readInt();
    }

    public static final Creator<Attendance> CREATOR = new Creator<Attendance>() {
        @Override
        public Attendance createFromParcel(Parcel in) {
            return new Attendance(in);
        }

        @Override
        public Attendance[] newArray(int size) {
            return new Attendance[size];
        }
    };

    public String getIn() { return in; }
    public String getOut() {  return out; }
    public String getDate() {  return date; }
    public String getTotal() {  return total; }

    
    @Override
    public String toString() {
        return "Attendance{" +
                "date='" + date + '\'' +
                ", in='" + in + '\'' +
                ", out='" + out + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(in);
        dest.writeString(out);
        dest.writeInt(id);
    }
}
