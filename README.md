# Assignment 2 - Movie Recommender.

Name: Kacper Morawski 20067214
## Overview.
...... A short statement of the concept and objectives ........


## Functionality
 . . . . . List of the functional features you implemented from the specification . . . .

 + Get top ten movies
 + Feature 2
 + Feature 3
 + etc
 + etc

## Extra features

 In this assigment we implemented the cliche menu, the login in as an admin or user 
## Installation requirements
. . . .  List of software, libraries and tools used (hint: everything on your build path libraries ) . . . . . . .
+ Java JRE v1.8
+ Guava v18.0
+ asg.cliche
+ xstream-1.4.10

## Getting started
Once its being downloaded/cloned from git repository run the program in Eclipse or any other suitable programm and run the class "main"

>The project comes with data in CSV format that can be used to prime the application with initial data.
- In the program select Main and run as Java Application:
```
The Cliche Movie Shell
Enter ?l to list available commands.
Films> ?list
abbrev	name	params
li	log-in	(user name, password)
p	prime	()
Films> p
Films>
```
- Log in as the administrator user (Kacper Morawski)
Type in : "li 6 Morawski"

You are logged in as Kacper
Admin
Films/Kacper>

or
Type in : "li 5 Parker"
You are logged in as Jenna
Default
Films/Parker>

## Data Model Design.

Describe the program's data model (see example below) AND/OR a sample of the data used (XML, JSON or equivalent).

![][image1]

Use meaningful sample data from your program.

## Examples

. . . . . Examples of program's user interface (e.g. CLI)  (see example below) with appropriate captions (user regeneration and login views, if implemented, can be omitted) . . . . . . .
/* User */
save	s	0	Save 
get-user	gu	1	Get a Users detail

add-rating	ar	3	Add an rating

add-film	af	4	Add Location to an activity

/* Admin * /
save	s	0	Save
get-film-by-Title	gfbt	1	Get Film by id
get-user	gu	1	Get a User by first Name
create-user	cu	6	Create a user
delete-user	du	1	Delete a User
get-all-films	gaf	0	Get all Films
get-all-users	gau	0	Get all Users
add-film	af	4	Add Movie to an rating
add-ratings	ar	3	Add Rating


## Independent learning.
I learned how to use a cliche properly. I also learned more about loading the file in and saving it to a file too.
I also learned that we can use multiple librarys to help us.
I also got a better knowledge of an project structure. 


[image1]: https://imgur.com/a/P5ByF
