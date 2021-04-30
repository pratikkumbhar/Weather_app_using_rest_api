package com.example.myapplication;

public class WeatherReportModel {

    private String weather_state_name;
    private String weather_state_addr;
    private String wind_direction_compass;
    private String created;
    private String applicable_date;
    private float min_temp;
    private float max_temp;
    private float wind_speed;
    private float wind_direction;
    private float air_pressure;
    private int humidity;
    private float visibility;
    private int predictablity;
    private long id;

    public WeatherReportModel(String weather_state_name, String weather_state_addr, String wind_direction_compass, String created, String applicable_date, float min_temp, float max_temp, float wind_speed, float wind_direction, float air_pressure, int humidity, float visibility, int predictablity,long id) {
        this.weather_state_name = weather_state_name;
        this.weather_state_addr = weather_state_addr;
        this.wind_direction_compass = wind_direction_compass;
        this.created = created;
        this.applicable_date = applicable_date;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.wind_speed = wind_speed;
        this.wind_direction = wind_direction;
        this.air_pressure = air_pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.predictablity = predictablity;
        this.id = id;
    }
    public WeatherReportModel()
    {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public void setWeather_state_name(String weather_state_name) {
        this.weather_state_name = weather_state_name;
    }

    public String getWeather_state_addr() {
        return weather_state_addr;
    }

    public void setWeather_state_addr(String weather_state_addr) {
        this.weather_state_addr = weather_state_addr;
    }

    public String getWind_direction_compass() {
        return wind_direction_compass;
    }

    public void setWind_direction_compass(String wind_direction_compass) {
        this.wind_direction_compass = wind_direction_compass;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getApplicable_date() {
        return applicable_date;
    }

    public void setApplicable_date(String applicable_date) {
        this.applicable_date = applicable_date;
    }

    public float getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(float min_temp) {
        this.min_temp = min_temp;
    }

    public float getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(float max_temp) {
        this.max_temp = max_temp;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public float getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(float wind_direction) {
        this.wind_direction = wind_direction;
    }

    public float getAir_pressure() {
        return air_pressure;
    }

    public void setAir_pressure(float air_pressure) {
        this.air_pressure = air_pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    public int getPredictablity() {
        return predictablity;
    }

    public void setPredictablity(int predictablity) {
        this.predictablity = predictablity;
    }

    @Override
    public String toString() {
        return
                "weather : " +weather_state_name +

                "Date :" + applicable_date +
                "Min :" + min_temp +
                "Max :" + max_temp +
                "Wind :" + wind_speed
                ;
    }
}