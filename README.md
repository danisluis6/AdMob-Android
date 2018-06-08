# AdMob Native Advanced Ads in an Android Feed [![Build Status](https://travis-ci.org/nomensa/jquery.hide-show.svg)](https://travis-ci.org/nomensa/jquery.hide-show.svg?branch=master)
  
## Here we go

1. Navigate this link to get GoogleJson: https://firebase.google.com/
![alt text](https://github.com/danisluis6/AdMob-Android/blob/admob_lv_2/mmo/1.png)

2. Done setting Firebase in Application

3. Setup Dagger 2 and Butterknife

4. Setup app_id with google admob
```xml
<string name="admob_app_id">ca-app-pub-1527512419709948~5470857332</string>
```
5. Initialize mobile Admob SDK
```java
    private void initializeGoogle() {
        // Initialize the Google Mobile Ads SDK
        MobileAds.initialize(getApplicationContext(),
                getString(R.string.admob_app_id));
    }
```
6. 
## Reference
- Link: https://codelabs.developers.google.com/codelabs/admob-native-advanced-feed-android/index.html#0
