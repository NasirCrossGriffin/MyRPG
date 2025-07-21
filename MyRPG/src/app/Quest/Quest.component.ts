import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { createQuest, getQuestById, Quest } from '../Middleware/Quest';
import { createQuestContent, getQuestContentByQuestId, QuestContent } from '../Middleware/QuestContent';
import { uploadAsset } from '../Middleware/Assets';
import { getUserById } from '../Middleware/User';
import { HttpClientModule, HttpClient } from '@angular/common/http';

@Component ({
  selector: 'app-quest',
      standalone: true,
      imports: [CommonModule, HttpClientModule, RouterModule],
      templateUrl: './Quest.component.html',
      styleUrls: ["./Quest.component.css"],
})

export class QuestComponent {
  loaded : boolean = false;
  loadingimage : string = "/jokerrunning.gif";
  quest : any;
  questContent : any = [];
  user : any;
  userId : string = ""
  questId : string = ""
  questContentIndex : number = 0;
  mediaType: 'image' | 'video' | 'unknown' = 'unknown';
  datetime : Date = new Date();

  constructor(
    private route: ActivatedRoute,
    private cdRef: ChangeDetectorRef,
    private http: HttpClient
  ) {}

  async ngOnInit() {
    this.questId = this.route.snapshot.paramMap.get('id') ?? '';
    this.quest = await getQuestById(this.questId)
    this.reformatDates()
    this.userId = this.quest.userId;
    this.user = await getUserById(this.userId);
    this.datetime = new Date()
    this.questContent = await getQuestContentByQuestId(this.quest.id);
    if (this.questContent.length > 0) {
      this.checkMediaType(this.questContent[0].contentUrl);
    }

    console.log(this.questId);

    console.log(this.quest);

    console.log(this.userId);

    console.log(this.user);

    console.log(this.questContent);

    if (this.questId !== null && this.quest !== null && this.userId !== null && this.user !== null && this.questContent.length > 0) {
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
    this.checkMediaType(this.questContent[newIndex].contentUrl);
    this.questContentIndex = parseInt(newIndex, 0);
  }

  reformatDates() {
        if (this.quest.datetime !== null) {
            var datetime = new Date(this.quest.datetime);
            
            const month = (datetime.getMonth() + 1).toString().padStart(2, '0'); // months are 0-indexed
            const day = datetime.getDate().toString().padStart(2, '0');
            const year = datetime.getFullYear();

            const formattedDate = `${month}/${day}/${year}`;

            this.quest.datetime = formattedDate;
        }
  }
}
