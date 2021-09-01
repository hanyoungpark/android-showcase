# Android Showcase
## How to Build
This project is a very straightforward project.

1. Open the project directory on the Android Studio
2. Replace Giphy API Key on the build.gradle of the app.
3. Start build

```gradle
buildTypes {
    release {
        minifyEnabled false
        proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
    }
    all {
#       buildConfigField("String", "API_KEY", '"{REPLACE_YOUR_GIPHY_API_KEY_HERE}"') # This part
        buildConfigField("String", "API_KEY", '"Lxadfwqkhlasdf23fadf"') # Replace like this
        
    }
}
```