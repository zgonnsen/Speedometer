package info.dyndns.gonnsen.speedometer;

/**
 * Created by zgonnsenadm on 10/8/15.
 */


    import android.os.SystemClock;
    import android.text.SpannableString;
    import android.text.style.RelativeSizeSpan;
    import android.util.Log;


public class Data {

    private boolean isRunning;
    private long time;
    private long timeStopped;
    private boolean isFirstTime;

    private double distanceKm;
    private double distanceM;
    private double curSpeed;
    private double maxSpeed;



    private onGpsServiceUpdate onGpsServiceUpdate;

    public interface onGpsServiceUpdate{
        public void update();
    }


    public void setOnGpsServiceUpdate(onGpsServiceUpdate onGpsServiceUpdate){
        this.onGpsServiceUpdate = onGpsServiceUpdate;
    }

    public void update(){
        onGpsServiceUpdate.update();
    }

    public Data() {
        isRunning = false;
        distanceKm = 0;
        distanceM = 0;
        curSpeed = 0;
        maxSpeed = 0;
        timeStopped = 0;
    }

    public Data(onGpsServiceUpdate onGpsServiceUpdate){
        this();
        setOnGpsServiceUpdate(onGpsServiceUpdate);
    }

    public void addDistance(double distance){
        distanceM = distanceM + distance;
        distanceKm = distanceM / 1000f;
    }

    public double getDistance(){
        return distanceM; // units: meters
    }

    public double getMaxSpeed() {
        return maxSpeed; // units: m/s
    }

    public double getAverageSpeed(){
        double average = (distanceM / time); // units: m/s

        if (time > 0){
            return average;

        }else{
            return 0;
        }
    }

    public double getAverageSpeedMotion(){
        double motionTime = time - timeStopped;

        if (motionTime < 0){
            return 0;
        }else{
            return ((distanceM / (time - timeStopped) )) ; // units: m/s

        }
    }

    public void setCurSpeed(double curSpeed) {
        this.curSpeed = curSpeed;
        if (curSpeed > maxSpeed){
            maxSpeed = curSpeed;
        }
    }

    public boolean isFirstTime() {
        return isFirstTime;
    }

    public void setFirstTime(boolean isFirstTime) {
        this.isFirstTime = isFirstTime;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void setTimeStopped(long timeStopped) {
        this.timeStopped += timeStopped;
    }

    public double getCurSpeed() {
        return curSpeed;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
