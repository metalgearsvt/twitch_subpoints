# Twitch Sub Points Calculator
This program will dump out your calculated sub points to a text file to be displayed with something like OBS.

### Setup
- You need Java installed.
- Edit the settings file to your liking. You must include your broadcaster ID, client ID, and oAuth code. 
- You can generate your oAuth using: https://twitchapps.com/tokengen/
 
   Follow the directions on their site, and add the client ID and oAuth to the settings file.
 
   They do not get your oAuth token (and neither do I for that matter), it makes the call in your browser only.

### Running
If you build it yourself, run it with:
  `java -jar subPointsCalculator.jar`
  
Otherwise if you use a release, there are runnables for Windows.
