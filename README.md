# Miko
An Android application developed in Kotlin language that consists of three Fragments. Main Fragment, Camera Fragment, Microphone Fragment. 
This app uses Fragment Nav Controller for navigating between fragments.

## Summary:
This app uses the following features:
1. NavController
2. Fragments
3. Dexter Library for permissions
4. Camera 
5. Microphone
6. Timer to track the app usage

## Working Of Application
**Splash Screen:** User launches the app > Splash screen will be loaded.

![SpalshScreen](https://user-images.githubusercontent.com/84138868/158325966-cc0fb851-4e58-4a76-baa9-44e326801858.jpg)


**Main Activity:**  Main Fragment is attached to Main Activity. Here User will be able to see two buttons **Camera** and **Microphone**.

![Main Fragment](https://user-images.githubusercontent.com/84138868/158325903-3aca09f9-6606-496f-8e91-184611fca479.jpg)


**Camera Fragment:** User clicks on Camera button and navigates to Camera . On click of camera, Users will be asked for required permissions.

![Camera permission One](https://user-images.githubusercontent.com/84138868/158327295-6eadc351-2b54-4b63-b383-1d00bd428a33.jpg) ![Camera permission Two](https://user-images.githubusercontent.com/84138868/158327338-88db159a-bece-490b-b7ba-973f907e96c3.jpg) ![Camera Dont Ask Again](https://user-images.githubusercontent.com/84138868/158327389-91ecc651-1234-4cb4-b8bd-da66d371d9ca.jpg) ![Camera Settings](https://user-images.githubusercontent.com/84138868/158327436-521ab353-e136-45b0-9216-e38096a1b9a0.jpg)

If all required permissions are granted then user can open Camera Module.

![CameraFragment](https://user-images.githubusercontent.com/84138868/158327515-ce826c8b-1d9d-4c7e-8c2f-ea2ca9736e7f.jpg) ![cameraOne](https://user-images.githubusercontent.com/84138868/158327623-9a75e055-9c11-47e7-bda7-b34b4a3ee1d4.jpg) ![cameraTwo](https://user-images.githubusercontent.com/84138868/158327686-d14d2018-42d6-4255-a27a-5bc79f36ef28.jpg)


User captures a picture and this will be displayed in and image view in same Fragment. User can navigate to Main Fragment by clicking on back button.

![cameraThree](https://user-images.githubusercontent.com/84138868/158327827-10cb9a57-3c59-4c8b-a1e9-8a11825c63aa.jpg)


**Microphone Fragment:** Here user can record audio for 10 seconds duration. On navigating to this fragment user can view two buttons, Start and Play. 

![micFragment](https://user-images.githubusercontent.com/84138868/158327882-92270c57-be06-4fab-9f06-66bb872b0cf7.jpg)

On Click of Start Button Audio recording begins and Play button is disabled during this time frame(10 seconds). Once 10 seconds are completed, Play button is enabled and user can Play this recorded Audio.

This audio file is saved on the internal storage of the device and hence requires External and Record Audio Permissions from user.

![Record Permission One](https://user-images.githubusercontent.com/84138868/158327985-bc291460-ed8c-4e88-8687-2739ece11333.jpg) ![Record Permission Two](https://user-images.githubusercontent.com/84138868/158328009-7f74638f-245a-43cc-a6b1-66e3257f26dd.jpg)


**Count Up Timer:** This count up timer will keep on running until the user is still using the application and will be reset when user closes this app.

![mainFragmentTwo](https://user-images.githubusercontent.com/84138868/158328053-b55c1ba7-3b17-4f15-b78a-1d88a0b9d0d7.jpg)



