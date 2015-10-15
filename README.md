# sss.test.ecommerce
Silicon Straits AdsBox is selling like hot cakes. The number of ordering coming in is exceeding the throughput of a manual system so Silicon Straits is planning to build an ecommerce platform​to meet the need of market.

# Decisions
Seperate the project e-commerce into 2 main components: User Interface (UI), and Application Programming Interface (API).

# UI: Backbone.js, JQuery
UI is very simple project using html pages:
  + index: List of all items, and basket feature. Items are loaded using Backbone.js, and of course via our API calls.
  + paypalSucess: this is the landing page when Paypal successfully handle user order. This page will behind the scene call another API to our system to complete the order

# API: Sping Framework.
There are 3 separate modules:
  + common:  provide common objects and services which might be used in other places (e.g api). Therefore, this is a good place for Data Access Object (DAO), POJO model, and paypal service.
  + api:     Will use `common` project above as an internal dependency. It will use common's object, service, spring contexts, and configuration.
             There are also different Api controllers, each only concern in a particular topic: users, items, and orders. The logic implementation for each controller is given to a lower level called `service`.
             This `service` is where we communicated with objects and the lowest layers (such as payment method, or DAO)
  + admin:   This module is structured differently, it doesn't have a seperate (UI). UI is also implemented inside the code base, html pages is generated by FreeMarker library.
             Requests from UI side is still handled by API (not the API from `api` module, but API provided by `admin`). Admin will take care of the request and generate pages from the result.

# Why Spring? 
+ Don’t need to extend classes from any particular Spring framework component to get the framework features.
+ Spring provides IOC  ( Inversion of Control) and DI ( Dependency Injection ) Capabilities. In other words, classes communicate to another via `interface`. `concrete` classes are constructed in spring context (xml files), and highy reusable accross the code base.

NOTE: The project has not been tested via Unit Test, and Integration Test.