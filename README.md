# MARAUDER&#39;S MAP
   (Mischief Managed)

## PROBLEM STATEMENT
Marauder&#39;s Map is a security surveillance system which uses image recognition to identify and track unauthorized personnel and stray animals around the college campus. It is built mainly to aid the college administration.

- How is it useful for the students?
  The application aims to make the campus a safer environment for students.
  A small tweak to this application can be used for taking attendance for classes.

- How is it useful for the administration?
1. The application&#39;s purpose is to assist security administration of the college in finding the people who are unauthorized in the campus. It captures images at periodic intervals and identifies those people who are not authorised to be on campus.
2. It divides the campus into quadrants based on the positions of cameras and their coverage areas and numbers them. Image from each quadrant is captured simultaneously and periodically to track people effectively.
3. The application is also used to locate registered students, visitors, faculty and staff on campus.
4. It also aims to detect stray animals and alert the security to take appropriate measures.

- Why is this better than existing/other solutions?  
  Currently, in the college, there is no such known application that offers similar solutions.

## TECHNOLOGY

### Language / Framework
Currently, the prototype uses:  
1. Java Swing for Client-side GUI
2. Python used to implement facial image recognition backend processing
3. Droidcam software used to emulate live feed from cameras
4. File system with a specific naming convention is used to process the images
5. When an unauthorized person is encountered, entry is made in a csv log file.

For the actual application development,  
1. High end GPU systems can be used to process the videos directly instead of the captured images. This is preferred but not implementable as these systems are not at our disposal.
2. Web-based GUI is preferred over Java Swing for the client side due to its ease of access and usage. It does not need any special installation or set up to be done. It can also be run on mobile phones.
3. The languages R or MATLAB would be preferred over Python for facial recognition but are not used due to lack of knowledge.
4. The post processed images are archived for 30 days on the server and then deleted. This is preferred over storing the images in the shared drive for 30 days.
5. Database could be used over csv file to store the unknown people log entries. Subsequently, a log viewing application can be developed which shows the timestamp and the image of the unknown person.

### Storage

1. The Marauder&#39;s Map application does not use any database.
2. The images are captured from the live feeds of the cameras placed around the campus. These images are saved to a shared folder on the security admin system. This shared folder is accessible only by the admin, thus securing its contents.
3. The image recognition algorithm takes the images from this folder, processes it and stores it back there. Whenever any unknown person is identified, an entry of [Timestamp, Location (quadrant number), path of image] is made into a logs csv file.

### Architecture Diagram

![alt text](https://github.com/saigowri/Marauder-Map/blob/master/architecture.png)

Essential components of Marauder&#39;s Map include –  
  1. Set of cameras placed strategically around the college campus  
  2. Monitoring station having the Admin system  
  3. Storage device (Image file server)  
  
The components interact with each other in the following way –  
  Live video is captured by the surveillance cameras and one frame is sent periodically (maybe every 10 seconds) to the shared folder from each camera. The facial recognition algorithm then picks these images and processes them to identify the unauthorized as well as registered people. The images are labelled with the name of the people if recognized else the person&#39;s face is boxed and labelled as &quot;unknown&quot;. These processed images are then put back into the shared folder from where the client interface displays them quadrant-wise. Any quadrant containing unknown people is colored RED to alert the security personnel. Then these images are transferred to the image server to be archived for 30 days. Parallelly, a log entry is made into a csv file (or database) for every unauthorized person where the timestamp, location in terms of quadrant number and image path is stored. Every 30 days, the log file (or database) and the image server are cleared.
