import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { environment } from '../../environments/environment';
import {getStatsByClassId, Stat} from "../Middleware/Stat";
import {getUserById, User} from "../Middleware/User";
import {getCharacterClassById, characterClass} from "../Middleware/Class"
import { getQuestByUserId } from '../Middleware/Quest';
import { getQuestContentByQuestId } from '../Middleware/QuestContent';

@Component ({
    selector: 'app-adventurer',
    imports: [CommonModule, RouterModule],
    standalone: true,
    templateUrl: './Adventurer.component.html',
    styleUrls: ["./Adventurer.component.css"],
})

export class AdventurerComponent {
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


    constructor(private route: ActivatedRoute, private cdRef: ChangeDetectorRef) {}

    async ngOnInit() {
        this.userId = this.route.snapshot.paramMap.get('id'); // Get the parameter
        console.log('User ID:', this.userId);
        if (typeof this.userId === "string")
          this.user = await getUserById(this.userId);
        else
          return;

        this.profilePic = this.user.profilePic;
        this.bannerPic = this.user.bannerPic;
        this.user_name = this.user.username;
        this.level = this.user.level;
        this.toNextLevel = this.user.toNextLevel;

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
}
