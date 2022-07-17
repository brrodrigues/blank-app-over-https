## Spring boot Application over SSL

The application was created to show how easy is enabling SSL in your spring boot applications.

To begin with, we need to have a existing keystore in our project. If you don't have. Follow the instruction below: 


### Generating a Self Signed Key

Open the terminal and type

```
keytool -genkeypair -alias app -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore app.p12 -validity 3650
```

Still in the folder, copy the generated .p12 file to your project's resources folder. Ex.: 

```
cp app.p12 ${PROJECT_ROOT}/src/resources/keystore
```

Done!

Now, your app is reading to run over https. 

Dont' forget to replace these keystore properties when you create your own keystore. These information are located at application.yml: 

server.ssl.key-store

server.ssl.key-store-password
