package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    public static final String HTTPS_WWW_METAWEATHER_COM_API_LOCATION_SEARCH_QUERY = "https://www.metaweather.com/api/location/search/?query=";
    public static final String URI = "https://www.metaweather.com/api/location/";
    Context context;
String cityId;
    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListner{
        void onError(String message);

        void onResponse(String cityId);

    }
    public void getCityId(String CityName, VolleyResponseListner responseListner)
    {
        String url = HTTPS_WWW_METAWEATHER_COM_API_LOCATION_SEARCH_QUERY +CityName;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               cityId = "";
                try {
                    JSONObject cityInfo= response.getJSONObject(0);
                    cityId = cityInfo.getString("woeid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                responseListner.onResponse(cityId);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Err1or", Toast.LENGTH_SHORT).show();
                responseListner.onError("SomthingWrong");
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);

    }

    public interface ForcastById{
        void onError(String message);

        void onResponse(List<WeatherReportModel> model);

    }
   public void getCityForecasetByID(String cityId, ForcastById forcastById) {
       List<WeatherReportModel> report = new ArrayList<>();
       String url = URI + cityId;
       JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {

               try {
                   JSONArray consolodated_weather = response.getJSONArray("consolidated_weather");


                   for (int i = 0; i < consolodated_weather.length(); i++) {
                       WeatherReportModel firstDay = new WeatherReportModel();
                       JSONObject firstDayAoi = (JSONObject) consolodated_weather.get(i);
                       firstDay.setId(firstDayAoi.getLong("id"));
                       firstDay.setWeather_state_name(firstDayAoi.getString("weather_state_name"));
                       firstDay.setWeather_state_addr(firstDayAoi.getString("weather_state_abbr"));
                       firstDay.setWind_direction_compass(firstDayAoi.getString("wind_direction_compass"));
                       firstDay.setCreated(firstDayAoi.getString("created"));
                       firstDay.setApplicable_date(firstDayAoi.getString("applicable_date"));
                       firstDay.setMin_temp((float) firstDayAoi.getDouble("min_temp"));
                       firstDay.setMax_temp((float) firstDayAoi.getDouble("max_temp"));
                       firstDay.setWind_speed((float) firstDayAoi.getDouble("wind_speed"));
                       firstDay.setWind_direction((float) firstDayAoi.getDouble("wind_direction"));
                       firstDay.setAir_pressure((float) firstDayAoi.getDouble("air_pressure"));
                       firstDay.setHumidity(firstDayAoi.getInt("humidity"));
                       firstDay.setVisibility((float) firstDayAoi.getDouble("visibility"));
                       firstDay.setPredictablity(firstDayAoi.getInt("predictability"));
                       report.add(firstDay);
                   }
                   forcastById.onResponse(report);


               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       });
       MySingleton.getInstance(context).addToRequestQueue(request);

   }

   public interface getCityForcastbyNameCallback
   {
       void OnError(String message);
       void onResponse(List<WeatherReportModel> weatherReportModels);
   }
    public void getCityForcastbyName(String cityname, getCityForcastbyNameCallback callback)
    {
            getCityId(cityname, new VolleyResponseListner() {
                @Override
                public void onError(String message) {

                }

                @Override
                public void onResponse(String cityId) {
                    getCityForecasetByID(cityId, new ForcastById() {
                        @Override
                        public void onError(String message) {

                        }

                        @Override
                        public void onResponse(List<WeatherReportModel> model) {
                                callback.onResponse(model);
                        }
                    });
                }
            });
    }


   }
