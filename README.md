# User Directory
User Directory API

## Set up the project

### Database SQL Scripts
For this project I used MySQL Workbench.

These are the few steps you need to follow to set up your database:

  - Open the "create-user.sql" script, you can find this file in the "root directory of the project" -> "sql_scripts"
<img width="273" alt="image" src="https://user-images.githubusercontent.com/60506191/145543177-4d267fe1-ec29-4a99-b7e3-f8366c9c194b.png">

<img width="358" alt="image" src="https://user-images.githubusercontent.com/60506191/145543600-bd7d0930-ad26-47a7-94c6-e885f64ad6eb.png">

<img width="502" alt="image" src="https://user-images.githubusercontent.com/60506191/145543707-1d01a068-372a-4018-b62e-e7d53a3cfea3.png">

And click on the execute button.

  - Once the user is created, set up a new connection 

<img width="590" alt="image" src="https://user-images.githubusercontent.com/60506191/145544136-4de91014-ce34-490f-a4c2-8780e176e668.png">

  - Then log in

  - Once you're logged in, execute the "create_table.sql" script which is in the same directory.

<img width="654" alt="image" src="https://user-images.githubusercontent.com/60506191/145545227-907983ab-ef9f-442d-af1b-29e7f5cf0075.png">

And that's it, your database is ready.


### Import project to Eclipse

  - On the Eclipse interface: File -> Import -> Existing Maven Projects --> Choose the Root directory --> And hit the finish button

<img width="335" alt="image" src="https://user-images.githubusercontent.com/60506191/145545896-4561a56d-d8f5-4e4c-a403-8336ee38b959.png">

<img width="445" alt="image" src="https://user-images.githubusercontent.com/60506191/145546190-9ab62bfa-83ea-4aa0-bf20-ffcf19b4b826.png">

<img width="918" alt="image" src="https://user-images.githubusercontent.com/60506191/145546313-b37da058-6b6f-4ad1-bb5c-bcf702da3ba6.png">

## API Documentation

### Find User By Username

Returns json data about the username.

  * URL

/api/users/:username

  * Method
 
`GET`
  
  * URL Params

**Required:**
 
 `username=[String]`

  * Data Params

  None

  * Success Response:

    * **Code:** 200 <br />
      **Content:** `{ "username" : "aymenhamzaoui", "gender" : "M", "birthDate" : "1998-12-31", "country" : "MAR", "phone" : "0669291891" }`
 
  * **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ "status" : 404, "message" : "Username - philcollins - not found!", "timeStamp" : 1639127462842 }`
      
    
### Add a New User

Returns json data about the user just created.

  * URL

/api/users

  * Method
 
`POST`
  
  * URL Params

None

  * Data Params
 
**Required:**
 
 `username=[String]` : must be at least 6 characters long.
 
 `birthDate=[Date]` : must respect the following format [yyyy-MM-dd], and must be a past date.
 
 `country=[String]` : must be: Country name, ISO 3166-1 alpha-2 code or ISO 3166-1 alpha-3 code.
 
**Optional:**
  
 `gender=[String]` : must be one of the following values: [FEMALE, M, F, MALE] , or null.
 
 `phone=[String]` : must be 10 numeric characters long, or null.

  * Success Response:

    * **Code:** 200 <br />
      **Content:** `{ "username" : "imaginedragons", "gender" : "M", "birthDate" : "1973-10-04", "country" : "FRA", "phone" : "0365428459" }`
 
  * **Error Response:**

    * **Code:** 400 BAD REQUEST <br />
      **Possible Content 1:** `{ "status" : 400, "message" : "The 'username' field is required, and must be at least 6 characters long.", "timeStamp" : 1639128249719 }`
      
      **Possible Content 2:** `{ "status" : 400, "message" : "The 'gender' field must be one of the following values: [FEMALE, M, F, MALE] , or null.", "timeStamp" : 1639128374660 }`
      
      **Possible Content 3:** `{ "status" : 400, "message" : "The 'birthDate' field is required, must respect the following format [yyyy-MM-dd], and must be a past date.", "timeStamp" : 1639128394950 }`
      
      **Possible Content 4:** `{ "status" : 400, "message" : "The 'country' field is required and must be: Country name, ISO 3166-1 alpha-2 code or ISO 3166-1 alpha-3 code", "timeStamp" : 1639128421327 }`
      
      **Possible Content 5:** `{ "status" : 400, "message" : "The 'phone' field must be 10 numeric characters long, or null. Don't forget the double quotes!", "timeStamp" : 1639128436323 }`
      
      **Possible Content 6:** `{ "status" : 400, "message" : "The user you're trying to add is not an adult. We cannot proceed with this operation", "timeStamp" : 1639128499841 }`
      
      **Possible Content 7:** `{ "status" : 400, "message" : "The user you're trying to add is not a French resident. We cannot proceed with this operation.", "timeStamp" : 1639128555235 }`
      
    * **Code:** 409 CONFLICT <br />
      **Content:** `{ "status" : 409, "message" : "Username - aymenhamzaoui - already exists!", "timeStamp" : 1639128655473 }`     




















































