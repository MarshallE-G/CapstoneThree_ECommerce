# Capstone 3: EasyShop
For this project, I added new features to and fixed bugs in a pre-existing API that's being used as the back-end of 
a website called "EasyShop".

***During the creation of my application...***
- Something I struggled with was figuring out how everything in the API is connected to one another.
    - I figured out that the easiest way around it was to study the API prior to adding any new code.


***Something I enjoyed while creating my application...***
- Something I enjoyed while creating my application was the problem-solving.

## Screenshots of command line

------------
### User NOT logged in
![Part1.PNG](Part1.PNG)

------------
### User logged in
![Part2.PNG](Part2.PNG)

------------
### Category & Minimum price adjusted
![Part3.PNG](Part3.PNG)

------------
### Category & Maximum price adjusted
![Part4.PNG](Part4.PNG)

------------
### Category, Minimum & Maximum price adjusted
![Part5.PNG](Part5.PNG)

------------
### Color adjusted
![Part6.PNG](Part6.PNG)

------------
### User Profile
![Part7.PNG](Part7.PNG)

------------
### Shopping cart with items
![Part8.PNG](Part8.PNG)

------------
### Shopping cart after clearing cart
![part9ClearCart.PNG](part9ClearCart.PNG)

------------
***If I was given more time, I would've liked to have worked more on...***
- I would've liked to have worked more on implementing the Checkout feature.

### One interesting piece of code
```java
for(Map.Entry<Integer, ShoppingCartItem> set :
                    shoppingCart.getItems().entrySet())
```
- This was my first time iterating through/over a HashMap, and it worked in unexpected ways.