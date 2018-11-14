# Friendly Pizza SDK

An SDK that could be easily integrated into any app and would offers pizza delivery

## Getting Started

These instructions will get you a copy of the SDK up and running in your project

### Prerequisites

Add the jitpack.io repository to your root build.gradle at the end of repositories:

```
allprojects {
 repositories {
    jcenter()
    maven { url "https://jitpack.io" }
 }
}
```

Now add the PizzaSDK dependency to your app level build.gradle file:

```
implementation 'com.github.davidcurtismintah:PizzaSDK:1.0'
```

### Usage

To start flavor selection, add this line in a context such as an activity or fragment:

```
startActivity(PizzaSdkActivity.getStartIntent(this))

```

Follow the prompts that follow from flavor selection to checking costs.


You may see the sample app included in this repository for an example
