import { environment } from '../../environments/environment';

const BASE_URL : string = environment.BASE_URL;

export type QuestContent = {
  contentUrl : String
  type : String;
  questId: String;
}

export async function createQuestContent(protoQuestContent : QuestContent) {
  console.log(JSON.stringify(protoQuestContent))

  try {
    const questcontentresponse = await fetch (`${BASE_URL}/api/questcontent`,{
      headers : {"content-type" : "application/json"},
      method : "POST",
      body : JSON.stringify(protoQuestContent)
    })

    if (questcontentresponse.ok) {
      console.log("Quest content created successfully")
      const newQuestContent = await questcontentresponse.json();
      return newQuestContent;
    } else {
      return null;
    }
  } catch (err) {
    console.log(err);
    return null;
  }
}

export async function getQuestContentById(questContentId : String) {
    console.log("The questContent id specified is " + questContentId)
  
    try {
      const questContentResponse = await fetch (`${BASE_URL}/api/questcontent/${questContentId}`,{
        headers : {"content-type" : "application/json"},
        method : "GET",
      })
  
      if (questContentResponse.ok) {
        console.log("Quest content retrieved successfully")
        const retrievedQuestContent = await questContentResponse.json();
        return retrievedQuestContent;
      } else {
        return null;
      }
    } catch (err) {
      console.log(err);
      return null;
    }
  }

export async function getQuestContentByQuestId(questId : String) {
  console.log("The quest id specified is " + questId)

  try {
    const questContentResponse = await fetch (`${BASE_URL}/api/questcontent/quest/${questId}`,{
      headers : {"content-type" : "application/json"},
      method : "GET",
    })

    if (questContentResponse.ok) {
      console.log("quest retrieved successfully")
      const listOfQuestContent = await questContentResponse.json();
      return listOfQuestContent;
    } else {
      return null;
    }
  } catch (err) {
    console.log(err);
    return null;
  }
}


