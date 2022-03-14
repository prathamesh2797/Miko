# Miko
An Android application developed in Kotlin language that consists of three Fragments. Main Fragment, Camera Fragment, Microphone Fragment. 
This app uses Fragment Nav Controller for navigating between fragments.

##Summary:
This app uses the following features:
1. NavController
2. Fragments
3. Dexter Library for permissions
4. Camera 
5. Microphone
6. Timer to track the app usage

## Working Of Application
**Splash Screen:** User launches the app > Splash screen will be loaded.


**Main Activity:**  Main Fragment is attached to Main Activity. Here User will be able to see two buttons **Camera** and **Microphone**.

**Camera Fragment:** User clicks on Camera button and navigates to Camera . On click of camera, Users will be asked for required permissions. If all required permissions are granted then user can open Camera Module.
User captures a picture and this will be displayed in and image view in same Fragment. User can navigate to Main Fragment by clicking on back button.

**Microphone Fragment:** Here user can record audio for 10 seconds duration. On navigating to this fragment user can view two buttons, Start and Play. 
On Click of Start Button Audio recording begins and Play button is disabled during this time frame(10 seconds). Once 10 seconds are completed, Play button is enabled and user can Play this recorded Audio.
This audio file is placed on the internal storage of the device and uses External and Record Audio Permissions


