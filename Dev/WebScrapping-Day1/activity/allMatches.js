let fs=require("fs");
let cheerio=require("cheerio");
let request=require("request");
const getMatch = require("./match");
// let link ="https://www.espncricinfo.com/series/icc-cricket-world-cup-2019-1144415";
function getAllMatches(link){
    request(link,callback);
}
// request(link,callback);
function callback(error,response,html){
    if(error==null && response.statusCode==200){
        parseData(html);
    }
    else if(response.statusCode==404){
        console.log("Page not found");
    }
    else{
        console.log(error);
    }
}
function parseData(html){
    let ch=cheerio.load(html);
    let allTag=ch('a[data-hover="Scorecard"]');
    // console.log(allTag)
    console.log(allTag.length)
    for(let i=0 ;i<allTag.length;i++){
        let completeLink="https://www.espncricinfo.com"+ch(allTag[i]).attr("href");
        console.log(completeLink);
        getMatch(completeLink);
    }
}

module.exports=getAllMatches;