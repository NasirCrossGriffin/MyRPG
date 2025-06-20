import {Component} from '@angular/core'
import { checkLoggedIn, patchUser } from '../Middleware/User';
import { getStatsByClassId, patchStat } from '../Middleware/Stat';
import { getCharacterClassById } from '../Middleware/Class';
import { Router } from '@angular/router';
import { createQuest, getQuestById, Quest } from '../Middleware/Quest';
import { createQuestContent, QuestContent } from '../Middleware/QuestContent';
import { createBucketObject } from '../Middleware/Bucket';
import { CommonModule } from '@angular/common';

@Component ({
    selector : "app-newquest",
    imports : [CommonModule],
    standalone: true,
    templateUrl : 'NewQuest.component.html' ,
    styleUrls : ['NewQuest.component.css']
})

export class NewQuestComponent {
  user : any = null;
  stats : any = [];
  statsToChange : any = [];
  statsToChangeNames : any = [];
  name : string = "";
  description : string = "";
  questContent : any = [];
  questContentEntries : any;
  loading : boolean = true;
  submitted : boolean = false;
  leveledUp : boolean = false;
  screenSize : number = window.innerWidth;
  newQuest : any = null;
  constructor(private router : Router) {};

  async ngOnInit() {
      this.user = await checkLoggedIn();
      if (this.user === null) {
          this.router.navigate(['/adventurer', this.user.id]); // Navigates to /user/:id
      }
      console.log(this.user);
      console.log(this.user.toNextLevel)
      this.stats = await getStatsByClassId(this.user.classId)
      if (this.stats === null) {
        console.log("no stats")
    }

    for (var stat of this.stats) {
      var statObj = {name : stat.name, enabled : false};
      this.statsToChange.push(statObj);
    }
  }

  updateName(e: Event) {
      console.log("Update name accessed")
          const input =  (e.target as HTMLInputElement).value;
          this.name = input;
  }

  updateDescription(e: Event) {
      console.log("Update name accessed")
          const input =  (e.target as HTMLInputElement).value;
          this.description = input;
  }

  uploadQuestContent(e: Event) {
      const input = (e.target as HTMLInputElement);
      if (input.files && input.files.length > 0) {
          const content = input.files[0];
          var type = "";
          if (content === null)
              return;

          if (content.type.startsWith('image/')) {
              type = "image";
          } else if (content.type.startsWith('video/')) {
              type = "video";
          } else {
              console.log(content.type)
              return;
          }

          this.questContent.push({file : content, url : URL.createObjectURL(content), type : type })
          this.questContentEntries = Object.entries(this.questContent);
      } else {
          return;
      }
  }

  updateStatsToChange(e : Event) {
    var statToChange = (e.target as HTMLInputElement).id
    for (var stat of this.statsToChange) {
      if (stat.name === statToChange) {
        stat.enabled = !(stat.enabled)
      }
    }

    if (!(this.statsToChangeNames.includes(statToChange))) {
      this.statsToChangeNames.push(statToChange)
    } else {
      this.statsToChangeNames = this.statsToChangeNames.filter((stat : string) => stat !== statToChange)
    }
  }

  async submitHandler() {
    this.submitted = true;

    this.loading = true;

    const protoQuest : Quest = {
        name : this.name,
        description : this.description,
        datetime : (new Date()).toISOString(),
        userId : this.user.id
    };

    console.log(protoQuest);

    const newQuest = await createQuest(protoQuest)

    if (newQuest) {

      for (var content of this.questContent) {
          var contentUrl = await createBucketObject(content.file);
          if (contentUrl === "")
              return;

          var protoQuestContent : QuestContent = {
              contentUrl : contentUrl,
              type : content.type,
              questId : newQuest.id
          };

          console.log(protoQuestContent);

          var newQuestContent = await createQuestContent(protoQuestContent);

          if (newQuestContent === null)
              return
      }

      this.newQuest = newQuest;

    } else {
        return
    }

    for (var stat of this.stats) {

      for (var statToChange of this.statsToChange) {
        if (stat.name === statToChange.name) {
          if (statToChange.enabled === true ) {
            var newStatValue = (stat.value + 5);

            var protoStat = {
              id : stat.id,
              name : stat.name,
              value : newStatValue,
              classId : stat.classId
            }

            var patchedStat = await patchStat(protoStat);

          }
        }
      }

    }

    var toNextLevel = (this.user.toNextLevel - 200)

    if (toNextLevel > 0) {

      var protoUser = {
        id : this.user.id,
        username : this.user.username,
        password : this.user.password,
        email : this.user.email,
        level : this.user.level,
        toNextLevel : toNextLevel,
        classId : this.user.classId,
        profilePic : this.user.profilePic,
        bannerPic : this.user.bannerPic
      }

    } else {

      var protoUser = {
        id : this.user.id,
        username : this.user.username,
        password : this.user.password,
        email : this.user.email,
        level : this.user.level + 1,
        toNextLevel : 1000 + toNextLevel,
        classId : this.user.classId,
        profilePic : this.user.profilePic,
        bannerPic : this.user.bannerPic
      }

      this.leveledUp = true

    }

    console.log(protoUser)

    var patchedUser = await patchUser(protoUser)

    this.user = patchedUser;

    this.stats = await getStatsByClassId(this.user.classId)

    this.loading = false;
  }

   navigateToQuest() {
    if (this.newQuest) {
      this.router.navigate(['/quest', this.newQuest.id]); // Navigates to /user/:id
    }
  }
}
