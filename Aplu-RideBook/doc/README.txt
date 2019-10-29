Notes on each Class listed in the UML Diagram:

RideProfile:

- The Custom Object representing the Profile's distinct attributes to construct, get or set:
-- Date
-- Time
-- Speed
-- distance
-- Cadence
-- note

RideArray:

The Custom array that contains RideProfile Objects to be displayed in mainactivity, editted, or deleted. Added objects are appended

-- Contains RideProfile Objects to be counted, editted, etc.


RideAdapter:

Responsible for updating the listview display in MainActivity to accommadate the newest changes, deletions, or adds to the RideArray List

-- updates main activity listview with the newest up-to-date list


MainActivity:

Responsible for the main navigation using listview objects and edit, delete, and add buttons. Upon listview item click, main enables the use of edit and delete (add is always displayed). 

-Edit button creates the Editprofile activity and Add creates the Profile Activity. Delete will delete from the Ridearray directly and automatically update with RideAdapter


ProfileActivity:

Upon pressing the add button, creates an empty profile with fields to fill. Once filled, the user presses add and the text elements are taken and parsed, which then changes the RideProfile object in the RideArray.

- Goes back to MAinActivity

EditProfileActivity:

Profile index within the RideArray list is sent to this activity. Upon create, the set is set to the previously-parsed values. Once the user is finished editting, the new fields are parsed and then change the attributes of the rideprofile at the index in the RideArray list.

- goes back to MainActivity






*IMPORTANT*

APK FILE IS LOCATED HERE WITHIN THE ANDROID STUDIO PROJECT FILE:

app/build/outputs/apk/debug