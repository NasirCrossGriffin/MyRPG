import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { createQuest, getFollowedUserQuests, getQuestById, Quest } from '../Middleware/Quest';
import { createQuestContent, getQuestContentByQuestId, QuestContent } from '../Middleware/QuestContent';
import { createBucketObject } from '../Middleware/Bucket';
import { checkLoggedIn, getUserById } from '../Middleware/User';
import { HttpClientModule, HttpClient } from '@angular/common/http';

@Component ({
  selector: 'app-followedquests',
      standalone: true,
      imports: [CommonModule, HttpClientModule, RouterModule],
      templateUrl: './FollowedQuests.component.html',
      styleUrls: ["./FollowedQuests.component.css"],
})

export class FollowedQuestsComponent {
  loggedIn : any = null;
  loaded : boolean = false;
  loadingimage : string = "/jokerrunning.gif";
  quests : any;
  questContent : any = [];
  user : any;
  userId : string = ""
  questContentIndex : number = 0;
  mediaType: 'image' | 'video' | 'unknown' = 'unknown';
  datetime : Date = new Date();

  constructor(
    private route: ActivatedRoute,
    private cdRef: ChangeDetectorRef,
    private http: HttpClient
  ) {}

  async ngOnInit() {
    this.loggedIn = await checkLoggedIn();
    this.quests = await getFollowedUserQuests(this.loggedIn.id)
    await this.getQuestContent();
    await this.getQuestUsers();
    console.log(this.loggedIn);
    this.reformatDates()
    this.user = await getUserById(this.userId);
    this.datetime = new Date()

    console.log(this.loggedIn);

    console.log(this.quests);

    if (this.loggedIn !== null && this.quests !== null) {
      this.loaded = true;
    } else {
      console.log("Some data wasn't loaded")
    }

    console.log(this.loaded)
  }

  checkMediaType(url: string) {
    this.http.head(url, { observe: 'response' }).subscribe(response => {
      const contentType = response.headers.get('Content-Type');
      if (contentType?.startsWith('image/')) {
        this.mediaType = 'image';
      } else if (contentType?.startsWith('video/')) {
        this.mediaType = 'video';
      } else {
        this.mediaType = 'unknown';
      }
    });
  }

  changeIndex(e : Event) {
    var newIndex = (e.target as HTMLElement).id;
    var navigatorElem = (e.target as HTMLElement).parentElement;
    var questIndex = 0;
    if (navigatorElem) {
      questIndex = Number(navigatorElem.id);
    }
    this.checkMediaType(this.quests[questIndex].questContent[newIndex].contentUrl);
    this.quests[questIndex].questContentIndex = parseInt(newIndex, 0);
  }

  reformatDates() {
    for (let quest of this.quests ) {
      if (quest.datetime !== null) {
          var datetime = new Date(quest.datetime);
          
          const month = (datetime.getMonth() + 1).toString().padStart(2, '0'); // months are 0-indexed
          const day = datetime.getDate().toString().padStart(2, '0');
          const year = datetime.getFullYear();

          const formattedDate = `${month}/${day}/${year}`;

          quest.datetime = formattedDate;
      }
    }
  }

  async getQuestContent() {
    for (let quest of this.quests ) {
      quest.questContent = await getQuestContentByQuestId(quest.id);
      quest.questContentIndex = 0;
      console.log(quest.questContent)
    }
  }

  async getQuestUsers() {
    for (let quest of this.quests ) {
      quest.user = await getUserById(quest.userId);
      console.log(quest.user)
    }
  }
}
