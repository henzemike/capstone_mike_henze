# capstone_mike_henze

Project Description:

This capstone covered automation testing on an ecommerce website, test cases were created for registering users, login functionality, product search, cart functionality, and checkout / payment processes.

Project Challenges:

Testing this website in a blackbox testing phase was a challenge due to having to create multiple wait periods in the automation to solve captcha challenges instead of having this website in a testing phase without the captcha.

Testing the registration multiple times had a challenge of having to manually change the register email, I applied a method to create a random number to the email to take away the manual process of having to change the entire email. The other challenge with this us not having access to the database to test against already registered emails, I will have to go back and apply a conditional if registration is unsuccessful to apply the method again until a unique email is created.

Testing the payment page created a challenge due to each card input field being in its own iframe, the iframe id contained text that was constant;y changing. This process was overcome by using an xpath with partial text of the id that never changes.

