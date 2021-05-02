package find.ride.kiwi.models;

public class Kiwi {
    private double latitude;
    private double longitude;

    public Kiwi(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Kiwi{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
