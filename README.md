Supported languages:  <img src="showcase/brazilian_flag.png" width=20> <img src="showcase/uk_flag.png" width=20>


# Summary
1. [Introduction](#introduction)
   * [Overview](#overview)
   * [Requirements](#requirements)
   * [Showcase](#showcase)
   * [QR Code Samples](#qrcode-examples)
2. [Architecture](#architecture)
   * [Clean Architecture](#clean-architecture)
   * [Design Patterns](#design-patterns)
   * [Quality Assurance](#tests)
   * [Frameworks](#frameworks)
   * [End Points](#end-points)
3. [Application Setup](#setup)   
4. [License](#license)


<a name="introduction" />

# Introduction

<a name="overview" />

## Overview
Create an application capable to read urls from Star Wars API, displaying these information in an application of type master-details. The requirements raised for this assignment shall test concepts of:
 1. Architectural design for Android Applications;
 2. Ability to communicate with complex end-points, parsing JSON responses and update UI;
 3. Data persistence and database manipulation;
 4. Sensor manipulation (camera);
 5. Permission requirement lifecycle;
 6. other.

<a name="requirements" />

<table>
  <tr>
    <td colspan="4" align="center"><b>Requirements</b></td>
  </tr>
  <tr>
  <td align="center"><b>ID</b></td>
  <td align="center"><b>Name</b></td>
  <td align="center"><b>Description</b></td>
  </tr>
  <tr>
    <td>REQ001</td>
    <td align="justify">Read QR Code</td>
    <td align="justify">Application must be able to read an URL from a QR Code image, storing the collected data locally</td>
  </tr>
  <tr>
    <td>REQ002</td>
    <td align="justify">Display people list</td>
    <td align="justify">Display to the person a list with all read people containing, at least, person’s name and URL</td>
  </tr>
  <tr>
    <td>REQ003</td>
    <td align="justify">Display user details</td>
    <td align="justify">When click on a list item, application must display person’s details including a list of movies that that character was part of. Among the details;</td>
  </tr>
  <tr>
    <td>REQ004</td>
    <td align="justify">Allow information caching</td>
    <td align="justify">All information once loaded from web into app must be persisted locally and accessible offline</td>
  </tr>
  <tr>
    <td>REQ005</td>
     <td align="justify">Allow URL typing to gather character information</td>
     <td align="justify">In order to enable emulator use and automation testing app must provide another way to collect character information that doesn't make use of the camera sensor</td>
  </tr>
  <tr>
    <td>REQ006</td>
     <td align="justify">Internationalize the application</td>
     <td align="justify">Add support for portuguese language</td>
  </tr>
</table>

<a name="showcase" />

## Showcase

<p align="center">
  <img src="showcase/showcase_1.png" align="center" width=150>
  <img src="showcase/showcase_2.png" align="center" width=150>
  <img src="showcase/showcase_3.png" align="center" width=150>
<br /><br />
<img src="showcase/showcase_4.png" align="center" width=150>
<img src="showcase/showcase_5.png" align="center" width=150>
  <img src="showcase/showcase_6.png" align="center" width=150>
</p>

<a name="qrcode-examples" />

## QR Code Samples

<p align="center">
  <img src="qrcodes/sample_01_luck_skywalker.jpg" align="center" width=200>
  <img src="qrcodes/sample_01_c3po.jpg" align="center" width=200>
  <br />
  <b>Figure 01:</b> sample qr codes generated on: http://br.qr-code-generator.com/
</p>

_Individual images can be found [here](https://github.com/edsilfer/star-wars-wiki/tree/master/qrcodes)_

<a name="architecture" />

# Architecture

<a name="clean-architecture" />
-----------------
<img src="architecture/clean_architecture_1.png" align="center">

Architectural approach
-----------------
<img src="architecture/clean_architecture_layers.png" align="center">

Architectural reactive approach
-----------------
<img src="architecture/clean_architecture_layers_details.png" align="center">


<a name="design-patterns" />

## Design Patterns
 - **Singleton**: this pattern is used mainly to offer a single instance of presenters for their respective views. Its implementation happens thanks to Dagger2 @Singleton annotation;
 - **Observer**: this pattern is largely used in order to issue network events that trigger database actions or UI updates. The variant employed is observed based on bus, being all call routed to a class that holds reference to all subscribers;
 - **Delegate**: this pattern is used in more than one place in the Application, for instance, with Postman and Router classes. The idea is to decouple classes that play distinct roles in the app - as the ones responsible for network communication for instance. That way, if any maintenance is required on them it is not passed beyond the class that gather all calls;
 - **Factory**: the application uses Factory Pattern on default Dagger2 implementation as well as to acquire End Points references for instance;

<a name="tests" />

## Quality Assurance

<p align="center">
  <img src="test/test.png" align="center">
</p>

One of the most importants gains that the MVP pattern brings to the project is the fact that all business logic gets isolated on Presenter Layer, it means, all code worthy to be tested is well encapsulated on classes with low dependency on OS components. This pattern alied with a Dependency Injection Container and Mock Frameworks (such as [Mockito](http://site.mockito.org/) and [PowerMockito](https://github.com/powermock/powermock/wiki/mockitousage)) allows the developer to take full advantage of Unit Tests.   

Despite the good coverage - over the classes that are worthy testing - it is always a good idea to include automation tests on the project. Currently, the offical adviced automation tool is [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/) that counts with a faboulous tool embbeded on Android Studio that lets the developer record automation steps while manipulation the app as an user. In order to develop this concept, the following story was created:

<table>
  <tr>
    <td colspan="2" align="center"><b>Test Scenarios</b></td>
  </tr>
  <tr>
  <td align="center"><b>ID</b></td>
  <td align="center"><b>Description</b></td>
  </tr>
  <tr>
    <td>S001</td>
    <td align="justify">As an user I should be able to:<br />  - start the application;<br /> - click on left corner menu to manually insert a character's URL;<br /> - Type the URL and hit okay;<br /> - See that the application has loaded the information correponding the typed URL on the screen;<br /> - Check that the name is correct;<br /></td>
  </tr>
</table>
<br />
<p align="center">
  <img src="showcase/showcase_7.gif" align="center"><br /><br />
    <b>Figure 02:</b> sample automation test generated using Espresso
 </p>

<a name="frameworks" />

## Frameworks
 - [**Requery to database**](https://realm.io/products/realm-mobile-database/): _“(...) A light but powerful object mapping and SQL generator for Java/Kotlin/Android with RxJava and Java 8 support. Easily map to or create databases, perform queries and updates from any platform that uses Java.”_
 - [**Retrofit**](https://square.github.io/retrofit/): _"(...) A type-safe HTTP client for Android and Java;
     - [**OkHttp**](http://square.github.io/okhttp/): “(...) HTTP is the way modern applications network. It’s how we exchange data & media. Doing HTTP efficiently makes your stuff load faster and saves bandwidth.
       OkHttp is an HTTP client that’s efficient by default:
          - HTTP/2 support allows all requests to the same host to share a socket.
          - Connection pooling reduces request latency (if HTTP/2 isn’t available).
          - Transparent GZIP shrinks download sizes.
          - Response caching avoids the network completely for repeat requests.

        OkHttp perseveres when the network is troublesome: it will silently recover from common connection problems. If your service has multiple IP addresses OkHttp will attempt alternate addresses if the first connect fails. This is necessary for IPv4+IPv6 and for services hosted in redundant data centers. OkHttp initiates new connections with modern TLS features (SNI, ALPN), and falls back to TLS 1.0 if the handshake fails.
        Using OkHttp is easy. Its request/response API is designed with fluent builders and immutability. It supports both synchronous blocking calls and async calls with callbacks. OkHttp supports Android 2.3 and above. For Java, the minimum requirement is 1.7…”_
     - [**Gson**](https://github.com/google/gson): _“(...) A Java serialization/deserialization library that can convert Java Objects into JSON and back…”_
     - [**RxAndroid**](https://github.com/ReactiveX/RxAndroid): _“(...) ReactiveX is a combination of the best ideas from the Observer pattern, the Iterator pattern, and functional programming...”_
 - [**Dagger 2**](https://google.github.io/dagger/): a dependency Injector for Android and Java, used to grant one of the S.O.L.I.D. principles for OO programming (Dependency Inversion Principle). Besides allowing the high level class to not depend upon low level ones, it makes Unit Test easier to perform with the help of a mocking framework i.e. Mockito;
 - **Other**:
     - [**MaterialBarcodeScanner**](https://github.com/EdwardvanRaak/MaterialBarcodeScanner): _"(...) Easy to use barcode reader for your Android Project (Uses Google Mobile Vision API)..."_

<a name="end-points" />

## End points
 - [**Star Wars API**](https://swapi.co/): _“(...) The Star Wars API, or "swapi" (Swah-pee) is the world's first quanitified and programmatically-accessible data source for all the data from the Star Wars canon universe! We've taken all the rich contextual stuff from the universe and formatted into something easier to consume with software. Then we went and stuck an API on the front so you can access it all!...”_

<a name="setup" />

# Application Setup
In order to make this application work, follow the steps below:

1. Have a Android Studio 3.0
1. Clone the repository to your local machine;
2. Build the project.

<a name="license" />

## License
Copyright 2017 Uzias Santos Ferreira

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
