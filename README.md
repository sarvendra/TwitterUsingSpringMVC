# TwitterUsingSpringMVC

Curl commands to test APIs. Remove escape character from json \ if using platforms other than windows.
User registration: 
curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"sar\", \"email\":\"sarvednra@gmail.com\",
\"password\":\"test\"}" http://localhost:8080/register

User login:
curl -X GET -H "X-Username: test@gmail.com" -H "X-Password: test" http://localhost:8080/login

logout:
curl -X GET -H "X-Auth-Token: authtoken" http://localhost:8080/logout

posttweet:
curl -X POST -H "X-Auth-Token: authtoken" -H "Content-Type: application/json" -d 
"{\"message\":\"your message\"}" http://localhost:8080/posttweet

tweets:
curl -X GET -H "X-Auth-Token: authtoken"  http://localhost:8080/tweets

tweets by userid:
curl -X GET -H "X-Auth-Token: authtoken"  http://localhost:8080/tweets/{userid}

addFollowing:
curl -X GET -H "X-Auth-Token: authtoken" http://localhost:8080/addfollowing/{followingid}

get followers:
curl -X GET -H "X-Auth-Token: authtoken" http://localhost:8080/followers

get followers by userid:
curl -X GET -H "X-Auth-Token: authtoken" http://localhost:8080/followers/{userid}

get following:
curl -X GET -H "X-Auth-Token: authtoken" http://localhost:8080/following

get following list by userid:
curl -X GET -H "X-Auth-Token: authtoken" http://localhost:8080/following/{userid}

