<h1> ✅ Features </h1>
<h2>💎 OpenID Login</h2>
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
<h2>💎 Menu Page</h2>

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


<h2>💎 Updating Profile</h2>

The update profile page allows to change attributes of the user without changing the essential attributes like email and password. You can change the Name of the user, their role and the name of the resturant that belongs to them.

<img width="1200" alt="signup" src="https://github.com/ashakhatri007/CSC510_Group25_Project1/blob/main/images/Update_profile.png">

<h2>💎 Notifications Page</h2>

The Notifications page informs restaurant administrators about potential events like low quantity of items in the inventory. items that are about to expire and items that have expired.

Previously, this page was not fetching any data but only showing hard coded data. Now, we have implemented the fetching of notifications from the database. To implement this, 3 new APIs were added, which are:

```
  - fetchExpiredInventoryItems(restaurantId)
    // Fetches all expired inventory items for a restaurant

  - fetchLowQuantityInventoryItems(restaurantId, maxQuantity)
    // Fetches all inventory items for a restaurant that have a quantity less than maxQuantity

  - fetchAboutToExpireInventoryItems(restaurantId, days)
    // Fetches all inventory items for a restaurant that are about to expire in the next days
```
Following are the files added:
```
  spring-social/src/main/java/com/example/springsocial/controller/NotificationsController.java
  spring-social/src/main/java/com/example/springsocial/payload/LowInventoryRequest.java
  spring-social/src/main/java/com/example/springsocial/payload/NotificationAboutToExpireRequest.java
  spring-social/src/main/java/com/example/springsocial/payload/NotificationExpiredRequest.java

```

The new payload files define the classes that represent the signature of the API requests for the notification related APIs. The notification controller class is responsible for handling the requests and returning the response. It uses the spring data jpa methods to filter the inventory database appropriately. 

The frontend fetches notification data on page load and renders it using the predefined classes for Box and Card components. 

Additionally, we have added test cases for the notification controller class in accordance with the test design pattern. These tests now run on each push via the GitHub action defined in the root of the project.



<br>
<h1> ✅ Tests and Formatting</h1>
<h3>💎 Segregation of qty_calc test cases </h3>
The Unit tests were all clubbed together making it very difficult to work on feature-specific test cases. We modularised the unit tests to map them to features and thus made the UT infra easily extensible for future group to add -on more test cases as and when new features develop.

Files added:
```
  qty_calc/src/test/java/IngredientTests.java
  qty_calc/src/test/java/ItemTests.java
  qty_calc/src/test/java/MockDBTests.java
  qty_calc/src/test/java/OrderTests.java
  qty_calc/src/test/java/OrderTrackerTests.java
  qty_calc/src/test/java/RecipeTests.java
  qty_calc/src/test/java/UnitConverterTests.java
  qty_calc/src/test/java/VolumeConverterTests.java

```

We used maven sure-fire plugin to run all above added test files:
```
  <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-surefire-plugin</artifactId>
                  <version>3.0.0-M4</version>
                  <configuration>
                      <includes>
                          <include>UnitConverterTests.java</include>
                          <include>VolumeConverterTests.java</include>
                          <include>OrderTrackerTests.java</include>
                          <include>IngredientTests.java</include>
                          <include>ItemTests.java</include>
                          <include>RecipeTests.java</include>
                          <include>OrderTests.java</include>
                          <include>MockDBTests.java</include>
                      </includes>
                  </configuration>
  </plugin>

```
 
<br>
<h3>💎 Addition of Code Coverga Badge </h3>
We added codecov badge in the repo by integrating code cov tool with travis.yml. This badge gives us a good understanding on how well tested our software is. 
<br>
<br>
<h3>💎 Addition of Prettier for code formatting and syntax checker</h3>
We used Prettier an opinionated code formatter to enforce a consistent style by parsing our code and re-printing it with its own rules that take the maximum line length into account, wrapping code when necessary.

```
  {
    "trailingComma": "es5",
    "tabWidth": 2,
    "semi": true,
    "singleQuote": false
  }
```
<h3> 💎 Addition of maven plugin for java formatting and styling </h3>
We added below plugin for java style checking:

```
   <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-checkstyle-plugin</artifactId>
                <version>3.0.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>checkstyle</goal>
                        </goals>
                    </execution>
                </executions>
    </plugin>

```
<h1> ✅ Documentation </h1>
Improved the Readme and code documentation where the instructions were not complete or intuitive.
<br>
<br>
<h1> ✅ Bug fixes </h1>

<h3> 🐞 Bug - Hardcoded ids in Inventory page </h3>

There was a bug in application wherein the ids for Inventory table were hard-coded thus leading to just Patch request been sent evry second time. We fixed the bug to generate IDs randomly and update the react-state accordingly. <br>
Sample code is below:

```
 generateRandomItemID() {
    var characters = 'abcdefghijklmnopqrstuvwxyz0123456789';
    var result = ""
    var chaactersLength = characters.length;

    for (var i = 0; i < 3; i++) {
      result += characters.charAt(Math.floor(Math.random() * chaactersLength));
    }
    this.setState({ itemID: result.toString() });
  }

```

Added random ids as part of react-state, and used the same while making server requests:

```
body: JSON.stringify({
        restaurantName: this.props.currentUser.restaurantName,
        restaurantID: this.props.currentUser.restaurant_id,
        itemID: this.state.itemID,
        itemName: this.state.itemName,
        batchID: this.state.batchID,
        batchQty: this.state.batchQty,
        costPerItem: this.state.costPerItem,
        dateBought: this.state.dateBought,
        dateExpired: this.state.dateExpired
      })

```
<h3> 🐞 Bug - Refresh required for reflecting inventory and menu items </h3>
