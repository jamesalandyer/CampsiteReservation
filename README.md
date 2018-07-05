# CampsiteReservation
A command line campsite reservation program that allows you to reserve campsites at national parks.

This is a command line Java application that allows you to reserve campsites. It allows you to select from a couple of parks and see the details about them. You then can view the campgrounds and select one to reserve. It then asks you to enter an arrival and departure date to reserve the campsite. It will show the results or send you back if there are no campsites to show. If there are available sites you can choose one and enter the name to reserve it under. Once it makes a reservation it gives you back the reservation id.

# Running The Project #
You can download the project and in the command line run: $ java -jar CampsiteReservation.jar.
Before you run it, you have to have the database setup. To do this please make sure that you have postgres installed and running. Then run the following commands:
$ createdb -U postgres campground
$ psql -U postgres -d campground -f database/campground.sql

# Screenshots #
![alt tag](https://github.com/jamesalandyer/CampsiteReservation/blob/master/campsitereservation-1.png)
![alt tag](https://github.com/jamesalandyer/CampsiteReservation/blob/master/campsitereservation-2.png)
![alt tag](https://github.com/jamesalandyer/CampsiteReservation/blob/master/campsitereservation-3.png)
![alt tag](https://github.com/jamesalandyer/CampsiteReservation/blob/master/campsitereservation-4.png)
