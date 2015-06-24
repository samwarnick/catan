# Settlers of Catan CS 340 Project
## Using Git and GitHub

First of all, you will need to create a GitHub account. Probably the easiest way to use Git is with GitHub's client. It can be found [here for Windows](https://windows.github.com "GitHub Client for Windows") and [here for Mac](https://mac.github.com "GitHub Client for Mac"). The help pages do a pretty good job of helping you set it up. Basically, you will fork this project and then clone it onto your machine. Clone it to where you want your Eclipse project to be. Do this before creating an Eclipse project. If you want to be brave and use Git by command line, be my guest.

When you create your Eclipse project, uncheck "Use default location" and browse to the location of the cloned folder. This will import everything automatically for you. Nice.

## ANT

Run the demo server using `ant server`.  Then navigate to localhost:8081/ in 
order to read the documentation.  The "Java docs" link takes you to documentation
for the Java GUI code that you will use to build your Catan client.  The
"Interactive Server API" link will take you to the server's Swagger page, which
allows you to interactively call the server's web API methods, and documents
the inputs and outputs of the server's methods.  The other links on the server's
documentation page are not relevant to the version of the project you are doing.

Run the demo client using `ant client`.

Run your server using `ant our-server`.

Run your client using `ant our-client`.

## JSON
There are example JSON files under the [sample](../sample) folder.  These are examples 
of the JSON objects used to make requests to the server, and to return the 
game state (i.e., client model) to the client.  You can also see the syntax
for the server's JSON inputs and outputs on the server's Swagger page,
with additional details being provide in the document titled "Server Web API 
Documentation".


