# LatestNewsRepository 
This application basically works to display current happenings around the world. 
Working:
The main page displays top highlighted news.

- When you click any news, you will get all information about that news.

# Feature
It works on 2 modes:
1. Online mode
2. Offline mode

In the online mode we get the information from the server. 

Suppose, If we get 10 record from the server these record, we save this into our database for 2 hours. 
After 2 hours we load refresh data load from server and delete old data.
Application work on principle of pagingnation in this we only display first 10 records. 
After scrolling we display next 10 records
