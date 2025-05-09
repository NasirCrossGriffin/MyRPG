import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { environment } from '../../environments/environment';
import {getStatsByClassId, Stat} from "../Middleware/Stat";
import {checkLoggedIn, getUserById, User} from "../Middleware/User";
import {getCharacterClassById, characterClass} from "../Middleware/Class"
import { getQuestByUserId } from '../Middleware/Quest';
import { getQuestContentByQuestId } from '../Middleware/QuestContent';
import { countFollowers, createFollow, getFollowBridge, unfollow } from '../Middleware/Follows';

@Component ({
    selector: 'app-adventurer',
    imports: [CommonModule, RouterModule],
    standalone: true,
    templateUrl: './Adventurer.component.html',
    styleUrls: ["./Adventurer.component.css"],
})

export class AdventurerComponent {
    loggedIn : any = null;
    loadingimage : string = "/jokerrunning.gif"
    loaded : boolean = false;
    className: string = "";
    userId: string | null = '';
    profilePic : string = "";
    bannerPic : string = "";
    user_name : string = "";
    level : string = "";
    toNextLevel : string = "";
    user : any;
    characterClass : any;
    stats : any;
    listOfQuests : any = [];
    numberOfFollowers : number = 0;
    following : boolean = false;


    constructor(private route: ActivatedRoute, private cdRef: ChangeDetectorRef) {}

    async ngOnInit() {
        this.userId = this.route.snapshot.paramMap.get('id'); // Get the parameter
        console.log(this.userId);
        console.log('User ID:', this.userId);
        if (typeof this.userId === "string")
          this.user = await getUserById(this.userId);
        else
          return;

        this.loggedIn = await checkLoggedIn();

        if (this.loggedIn !== null) {
          const followBridge = await getFollowBridge({userAccountId : this.userId, followerAccountId : this.loggedIn.id})

          if (followBridge !== null) {
            this.following = true;
          } else {
            this.following = false;
          }

          console.log(this.following)
          
          console.log(this.loggedIn.id)
        }

        
        this.profilePic = this.user.profilePic;
        this.bannerPic = this.user.bannerPic;
        this.user_name = this.user.username;
        this.level = this.user.level;
        this.toNextLevel = this.user.toNextLevel;
        this.numberOfFollowers = await countFollowers(Number(this.userId));

        this.characterClass = await getCharacterClassById(this.user.classId);

        if (this.characterClass === null) {
          console.log("Could not retrieve character class")
          return
        }

        this.className = this.characterClass.name;

        this.stats = await getStatsByClassId(this.characterClass.id)

        console.log(this.stats);

        if (this.stats === null) {
          console.log("Could not retrieve character class stats")
          return
        }

        //Populate the quest list

        var questObjects = await getQuestByUserId(this.userId);

        console.log(questObjects);

        for (let quest of questObjects) {
          var questContent = await getQuestContentByQuestId(quest.id)
          this.listOfQuests.push({
            questId : quest.id,
            questTitle : quest.name,
            questPreview : questContent[0].contentUrl,
            questPreviewType : questContent[0].type
          })
        }

        console.log(this.listOfQuests)

        this.loaded = true;

        this.cdRef.detectChanges();
    }

    async followButton() {
      if (this.following === false) {
        const newFollow = await createFollow({userAccountId : this.userId, followerAccountId : this.loggedIn.id})

        if (newFollow != null) {
          this.following = true;
          this.numberOfFollowers = await countFollowers(Number(this.userId));
        }
      } else {
        const unfollowStatus = await unfollow({userAccountId : this.userId, followerAccountId : this.loggedIn.id})

        if (unfollowStatus != null && unfollowStatus !== false) {
          this.following = false;
          this.numberOfFollowers = await countFollowers(Number(this.userId));
        }
      }
    }
}
