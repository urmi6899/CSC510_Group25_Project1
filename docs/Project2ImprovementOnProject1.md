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
We have successfully implemented for google and the rest are for future scope. The handle submit code for login using google is as below:

```
handleSubmit(event) {
        event.preventDefault();   

        const loginRequest = Object.assign({}, this.state);

        login(loginRequest)
        .then(response => {
            localStorage.setItem(ACCESS_TOKEN, response.accessToken);
            Alert.success("You're successfully logged in!");
            this.props.history.push("/");
            window.location.reload();
        }).catch(error => {
            Alert.error((error && error.message) || 'Oops! Something went wrong. Please try again!');
        });
    }
  ```
<h2>Menu Page</h2>

Menu tab allows the user to see all the restaurant items present for his/her restaurant. We implemented Menu tab to get all available menu items from past orders and create menu item which will be added to the database.

Files added:
```
  react-social/src/menu/menu-form.js
  react-social/src/menu/menu-form.test.js
  react-social/src/menu/menu-page.js
  react-social/src/menu/menu-page.test.js
  react-social/src/menu/menu-table.js
  react-social/src/menu/menu-table.test.js
```
Menu tab:
<img width="1200" alt="signup" src="https://github.com/ashakhatri007/CSC510_Group25_Project1/blob/main/images/View%20menu.png">

Add Menu item:
<img width="1200" alt="signup" src="https://github.com/ashakhatri007/CSC510_Group25_Project1/blob/main/images/Add%20Menu%20item.png">


<h2>Updating Profile</h2>

The update profile page allows to change attributes of the user without changing the essential attributes like email and password , You can change the Name of the user , their role and the name of the resturant that belongs to them.

<img width="1200" alt="signup" src="https://github.com/ashakhatri007/CSC510_Group25_Project1/blob/main/images/Update_profile.png">


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
