package test.adonis.weatherapp.model;

/**
 * Created by Paola on 09/06/2017.
 */

public class Weather {


    private int id;
    private String country;
    private String cityName;
    private int weatherId;
    private String weatherMain;
    private String weatherDescription;
    private String weatherIcon;

    private String mainTemp;
    private String mainPressure;
    private String mainHumidity;
    private String mainTempMax;
    private String mainTempMin;
    private String mainSeaLevel;
    private String mainGrndLevel;

    private String windSpeed;
    private String windDeg;

    private String clouds;
    private String rain;
    private String snow;

    private String sunset;
    private String sunrise;

    private String visibility;

    private String tempDay;
    private String tempNight;

    private String date;

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getMainTemp() {
        return mainTemp;
    }

    public void setMainTemp(String mainTemp) {
        this.mainTemp = mainTemp;
    }

    public String getMainPressure() {
        return mainPressure;
    }

    public void setMainPressure(String mainPressure) {
        this.mainPressure = mainPressure;
    }

    public String getMainHumidity() {
        return mainHumidity;
    }

    public void setMainHumidity(String mainHumidity) {
        this.mainHumidity = mainHumidity;
    }

    public String getMainTempMax() {
        return mainTempMax;
    }

    public void setMainTempMax(String mainTempMax) {
        this.mainTempMax = mainTempMax;
    }

    public String getMainTempMin() {
        return mainTempMin;
    }

    public void setMainTempMin(String mainTempMin) {
        this.mainTempMin = mainTempMin;
    }

    public String getMainSeaLevel() {
        return mainSeaLevel;
    }

    public void setMainSeaLevel(String mainSeaLevel) {
        this.mainSeaLevel = mainSeaLevel;
    }

    public String getMainGrndLevel() {
        return mainGrndLevel;
    }

    public void setMainGrndLevel(String mainGrndLevel) {
        this.mainGrndLevel = mainGrndLevel;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(String windDeg) {
        this.windDeg = windDeg;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getSnow() {
        return snow;
    }

    public void setSnow(String snow) {
        this.snow = snow;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getTempDay() {
        return tempDay;
    }

    public void setTempDay(String tempDay) {
        this.tempDay = tempDay;
    }

    public String getTempNight() {
        return tempNight;
    }

    public void setTempNight(String tempNight) {
        this.tempNight = tempNight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

