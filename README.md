![Logo]
# Find Ride Kiwi app

By using this app you can find and see kiwis near you.

## Supported Languages
* English
* Turkish
* Ukranian


## Instalation

```bash
$ git clone https://github.com/mecitdeniz/FindRideKiwi.git
```


## GoogleMaps Sdk

* Get Key : https://developers.google.com/maps/documentation/android/start#get-key
* Add-key : Add your key to

``` 
/res 
    |
    /values
      |
      google_maps_api.xml
```
* 


## Update Kiwi Data

* Go visit : https://geojson.io/
* Select point from map and get lat long in geojson format
* Add to file : 
``` 
/assets 
    |
    data.json
```
* Example of a point in geojson Format : 
```
{
  "type": "Feature",
    "properties": {},
      "geometry": {
        "type": "Point",
        "coordinates": [
          32.4931,
          37.8748
        ]
       }
 }
```

### ScreenShots
<p float="left">
  <img src="https://github.com/mecitdeniz/FindRideKiwi/blob/main/docs/s1.png" alt="alt text" width="240">
  <img src="https://github.com/mecitdeniz/FindRideKiwi/blob/main/docs/s2.png" alt="alt text" width="240"> 
</p>
<br>
<p float="left">
  <img src="https://github.com/mecitdeniz/FindRideKiwi/blob/main/docs/s3.png" alt="alt text" width="240">
  <img src="https://github.com/mecitdeniz/FindRideKiwi/blob/main/docs/s4.png" alt="alt text" width="240">
</p>
<p float="left">
  <img src="https://github.com/mecitdeniz/FindRideKiwi/blob/main/docs/s5.png" alt="alt text" width="240">
</p>


[Logo]: https://github.com/mecitdeniz/FindRideKiwi/blob/main/docs/kiwilogo.png
