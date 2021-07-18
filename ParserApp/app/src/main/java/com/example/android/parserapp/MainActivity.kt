package com.example.android.parserapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.json.JSONObject
import java.io.IOException
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var parseXMLButton: Button;
    private lateinit var parseJSONButton: Button;

    private lateinit var datatype: TextView;
    private lateinit var cityname: TextView;
    private lateinit var latitude: TextView;
    private lateinit var longitude: TextView;
    private lateinit var temp: TextView;
    private lateinit var humidity: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parseXMLButton = findViewById(R.id.parsexmlbtn);
        parseJSONButton = findViewById(R.id.parsejsonbtn);
        datatype = findViewById(R.id.dataType);
        cityname = findViewById(R.id.cityName);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longetude);
        temp = findViewById(R.id.temp);
        humidity = findViewById(R.id.humidity);
        parseXMLButton.setOnClickListener { parseXML() }
        parseJSONButton.setOnClickListener { parseJson() }
    }
    @SuppressLint()
    private fun parseXML() {
        datatype.text="Xml Data";
        try {
            val iStream = assets.open("myxml.xml");
            val builderFactory= DocumentBuilderFactory.newInstance();
            var docBuilder= builderFactory.newDocumentBuilder();
            var doc=docBuilder.parse(iStream);
            cityname.text="City Name:"+doc.getElementsByTagName("cityname").item(0).getFirstChild().getNodeValue();
            longitude.text="Longitude:"+doc.getElementsByTagName("longitude").item(0).getFirstChild().getNodeValue();
            latitude.text="Latitude:"+doc.getElementsByTagName("latitude").item(0).getFirstChild().getNodeValue();
            temp.text="Temperature:"+doc.getElementsByTagName("temperature").item(0).getFirstChild().getNodeValue();
            humidity.text="Humidity:"+doc.getElementsByTagName("humidity").item(0).getFirstChild().getNodeValue();
        } catch(ex: IOException){}
    }

    @SuppressLint()
    private fun parseJson() {
        datatype.text="Json Data";
        val obj = JSONObject(loadJSONFromAsset());
        cityname.text="City Name:"+obj.getString("cityname");
        longitude.text="Longitude:"+obj.getString("longitude");
        latitude.text="Latitude:"+obj.getString("latitude");
        temp.text="Temperature:"+obj.getString("temperature");
        humidity.text="Humidity:"+obj.getString("humidity");
    }



    private fun loadJSONFromAsset(): String
    {
        val json:String?
        try {
            val inputStream= assets.open("myjson.json")
            val size= inputStream.available()
            val buffer= ByteArray(size)
            val charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json= String(buffer,charset);
        } catch(ex: IOException){
            ex.printStackTrace()
            return "";
        }
        return json;
    }
}
