let fs=require("fs");
let cheerio=require("cheerio");
let request=require("request");
let count=0;
// let link ="https://www.espncricinfo.com/series/icc-cricket-world-cup-2019-1144415";
function getMatch(link){
    console.log("sending request",count);
    count++;
    request(link,callback);
}
// request(link,callback);
let leaderBoard=[];

function callback(error,response,html){
    
    if(error){
        console.log(error);
    }
    else if(error==null && response.statusCode==200){
        console.log("receiving request",count);
        count--;
        parseData(html);
        if(count==20){
            console.table(leaderBoard);
        }
    }
    else{
        console.log("inside else");
    }
}
function parseData(html){
    let ch=cheerio.load(html);
    let bothInnings=ch(".card.content-block.match-scorecard-table .Collapsible");
    // console.log(allTag)
    // console.log(bothInnings.length)
    for(let i=0 ;i<bothInnings.length;i++){
        let teamName=ch(bothInnings[i]).find("h5").text();
        teamName=teamName.split("INNINGS")[0].trim();
        // console.log(teamName);

        let allTrs=ch(bothInnings[i]).find(".table.batsman tbody tr");
        for(let j =0;j<allTrs.length-1;j++){
            let allTds=ch(allTrs[j]).find("td");
            if(allTds.length>1){
                let batsmanName=ch(allTds[0]).find("a").text().trim();
                let runs =ch(allTds[2]).text().trim();   
                let ball=ch(allTds[3]).text().trim();
                let fours=ch(allTds[5]).text().trim();
                let sixes=ch(allTds[6]).text().trim();
                let SR=ch(allTds[7]).text().trim();

                // console.log(`Batsman= ${batsmanName} runs=${runs} balls=${balls} fours=${fours} sixes=${sixes} StrikeRate=${SR}`);

                //processDetails(teamName,batsmanName,runs,ball,fours,sixes,SR);
                createLeaderBoard(teamName,batsmanName,runs,ball,fours,sixes);
            }
        }
        // console.log(allTrs.length);
    }
    // console.log("============================================================");
}
function createLeaderBoard(teamName,batsmanName,runs,ball,fours,sixes){
    runs=Number(runs);
    ball=Number(ball);
    fours=Number(fours);
    sixes=Number(sixes);

    for(let i=0;i<leaderBoard.length;i++){
        if(teamName==leaderBoard[i].TeamName && batsmanName==leaderBoard[i].BatsmanName){
            leaderBoard[i].Runs+=runs;
            leaderBoard[i].Balls+=ball;
            leaderBoard[i].Fours+=fours;
            leaderBoard[i].Sixes+=sixes;
            return;
        }
    }
    let entry={
        TeamName:teamName,
        BatsmanName:batsmanName,
        Runs:runs,
        Balls:ball,
        Fours:fours,
        Sixes:sixes
    }
    leaderBoard.push(entry);
}

module.exports=getMatch;