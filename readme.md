
# MyRPG

## Live Demo : https://myrpgapp.com

## Dummy Credentials: 

- Username: TestAccount1
- Password: TestAccount1

## Summary

MyRPG is a social media and self-improvement app where users can make an account and share their different experiences. By sharing more experiences, users can increase their stats for the class they chose for their account, and increase their level. The frontend of MyRPG is powered by Angular, the custom backend API is powered by spring-boot. A relational database powered by MySQL stores all user data. The entire project is hosted on the bare-metal Linux home-lab server Ultron.

## Technologies 

- Angular
- Spring-Boot
- MySQL
- Ngnix
- Docker

## Features

- Account Creation and authentication.
- Your Adventurer: you can visit your own account page, view your stats and quests, and see your followers.
- Quest Page: A quest consists of a title, a description, and any number of photos. You can view an individual quest on this page.
- Followed Quests: Here you can view the quests of all the adventurers you follow.
- Gamification: Each adventurer has a class, the class you choose determines your stats. As you upload quests, your stats increase.

## Installation 

- Download source code.

- Run npm install --legacy-peer-deps in the gamestore directory for node modules.

- Run npm run build.

- Move build into backend, rename index.csr.html to index.html.

- Run node server.js in the backend directory.

