let fs=require("fs");
let cheerio=require("cheerio");
let request=require("request");

// let link ="https://www.espncricinfo.com/series/icc-cricket-world-cup-2019-1144415";
function getMatch(link){
    request(link,callback);
}
// request(link,callback);
function callback(error,response,html){
    
    if(error){
        console.log(error);
    }
    else if(error==null && response.statusCode==200){
        parseData(html);
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

                processDetails(teamName,batsmanName,runs,ball,fours,sixes,SR);
            }
        }
        // console.log(allTrs.length);
    }
    // console.log("============================================================");
}

function checkTeamFolder(teamName){
    //./India=India
    return fs.existsSync(teamName);
}
function createTeamFolder(teamName){
    //teamname =India
    fs.mkdirSync(teamName);
}
function checkBatsmanFolder(teamName,batsmanName){
    //teamName =India batsman=MSDhoni
    let batsmanPath=`${teamName}/${batsmanName}.json`;
    return fs.existsSync(batsmanPath);
}
function createBatsmanFile(teamName,batsmanName,runs,ball,fours,sixes,SR){
    let batsmanPath=`${teamName}/${batsmanName}.json`;
    let batsmanFile=[];
    let inning={
        BatsmanName:batsmanName,
        Runs:runs,
        Balls:ball,
        Fours:fours,
        Sixes:sixes,
        StrikeRate:SR
    }
    batsmanFile.push(inning);
    batsmanFile=JSON.stringify(batsmanFile);
    fs.writeFileSync(batsmanPath,batsmanFile);
}
function updateBatsmanFile(teamName,batsmanName,runs,ball,fours,sixes,SR){
    let batsmanPath=`${teamName}/${batsmanName}.json`;
    let batsmanFile=fs.readFileSync(batsmanPath);
    batsmanFile=JSON.parse(batsmanFile);
    let inning={
        BatsmanName:batsmanName,
        Runs:runs,
        Balls:ball,
        Fours:fours,
        Sixes:sixes,
        StrikeRate:SR
    }
    batsmanFile.push(inning);
    batsmanFile=JSON.stringify(batsmanFile);
    fs.writeFileSync(batsmanPath,batsmanFile);
}

function processDetails(teamName,batsmanName,runs,ball,fours,sixes,SR){
    //check if team folder exist
    let isTeamFoler=checkTeamFolder(teamName);
    if(isTeamFoler){
        let isBatsman=checkBatsmanFolder(teamName,batsmanName);
        if(isBatsman){
            updateBatsmanFile(teamName,batsmanName,runs,ball,fours,sixes,SR);
        }
        else{
            createBatsmanFile(teamName,batsmanName,runs,ball,fours,sixes,SR);
        }
    }
    else{
        createTeamFolder(teamName);
        createBatsmanFile(teamName,batsmanName,runs,ball,fours,sixes,SR);
    }
}

module.exports=getMatch;