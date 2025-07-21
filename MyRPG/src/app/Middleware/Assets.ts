import { environment } from '../../environments/environment';

const BASE_URL : string = environment.BASE_URL;

export async function uploadAsset(objectFile : File) {
  var formdata = new FormData;
  formdata.append("file", objectFile)
  try {
    const bucketresponse = await fetch (`${BASE_URL}/api/assets/insert`,{
      method : "POST",
      body : formdata
    })

    if (bucketresponse.ok) {
      const newObjectURL = await bucketresponse.text();
      return newObjectURL;
    } else {
      return "";
    }
  } catch (err) {
    console.log(err);
    return "";
  }
}

