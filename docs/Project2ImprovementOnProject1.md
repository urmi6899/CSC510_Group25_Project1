<h1> Features </h1>
<h2>OpenID Login</h2>
<h4>What is OpenID?</h4>
OpenID allows you to use an existing account to sign in to multiple websites, without needing to create new passwords.
You may choose to associate information with your OpenID that can be shared with the websites you visit, such as a name or email address. With OpenID, you control how much of that information is shared with the websites you visit.
With OpenID, your password is only given to your identity provider, and that provider then confirms your identity to the websites you visit.  Other than your provider, no website ever sees your password, so you don’t need to worry about an unscrupulous or insecure website compromising your identity.
<h4>How is OpenID functioning in our application?</h4>
Its working using the below 4 principles:

* <b>Core</b>: authentication and use of Claims to communicate End-User information
* <b>Discovery</b>: stipulates how a client can dynamically determine information about OpenID Providers
* <b>Dynamic Registration</b>: dictates how a client can register with a provider
* <b>Session Management</b>: defines how to manage OIDC sessions

<h4>What are the different configurations and dependencies used in our project?</h4>

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-client</artifactId>
    <version>2.2.6.RELEASE</version>
</dependency>
```

Spring-boot configuration in application.yml file:

```
spring:
  security:
    oauth2:
      client:
        registration: 
          google: 
            client-id: <client-id>
            client-secret: <secret>
            .
            .
```

In our application we want implemented OpenID for clients like Google, Facebook and LinkedIn. 
We have successfully implemented for google and the rest are for future scope.

<h2>Updating Profile</h2>

The update profile page allows to change attributes of the user without changing the essential attributes like email and password , You can change the Name of the user , their role and the name of the resturant that belongs to them.


<br>
<h1> Tests </h1>
<h3>Segregation of qty_calc test cases </h3>
<br>
<h3>Addition of Code Coverga Badge </h3>
<p>We added codecov badge in the repo by integrating code cov tool with travis.yml. This badge gives us a good understanding on how well tested our software is. </p>
<h3>Addition of Prettier for code formatting and syntax checker</h3>
<p> We used Prettier an opinionated code formatter to enforce a consistent style by parsing our code and re-printing it with its own rules that take the maximum line length into account, wrapping code when necessary.
</p>



<br>
<h1> Documentation </h1>

<br>
<h1> Bugs </h1>
