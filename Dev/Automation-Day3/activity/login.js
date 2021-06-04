const { getElementsByTagName } = require("domutils");
const puppeteer=require("puppeteer");
let tab;
let indx;
let globalCode;
let browserOpenPromise=puppeteer.launch({headless:false,defaultViewport:false,args:["--start-maximized"]});
browserOpenPromise.then(function(browser){
    let pagesPromise=browser.pages();
    return pagesPromise;
})
.then(function(pages){
    let page=pages[0];
    tab=page;
    return page;
})
.then(function(page){
    let pageOpenedPromise=page.goto("https://www.hackerrank.com/auth/login?h_l=body_middle_left_button&h_r=login");
    return pageOpenedPromise;
})
.then(function(){
    let idTypedPromise=tab.type("#input-1","gadob83422@relumyx.com");
    return idTypedPromise;
})
.then(function(){
    let passwordTypesPromise=tab.type("#input-2","12345678");
    console.log("id typed");
    return passwordTypesPromise;
})
.then(function(){
    let clickPromise=tab.click(".ui-btn.ui-btn-large.ui-btn-primary.auth-button.ui-btn-styled");
    return clickPromise;
})
.then(function(){
    let waitAndClickPromise=waitAndClick("#base-card-1-link");
    return waitAndClickPromise;
})
.then(function(){
    let waitAndClickPromise=waitAndClick(".ui-btn.ui-btn-normal.playlist-card-btn.ui-btn-primary.ui-btn-link.ui-btn-styled");
    console.log("warmup clicked");
    return waitAndClickPromise;
})
.then(function(){
    let waitPromise=tab.waitForSelector(".js-track-click.challenge-list-item");
    console.log("waiting for selector");
    return waitPromise;
})
.then(function(){
    let AllQuestionsLinks=tab.$$(".js-track-click.challenge-list-item");
    console.log("returnign all links of questions");
    // console.log(AllQuestionsLinks)
    return AllQuestionsLinks;
})
.then(function(allQuestions){
    let allLinkPromise=[];
    console.log(allQuestions.length);
    for(let i=0;i<allQuestions.length;i++){
        let linkPendingPromise=tab.evaluate(function(elem){return elem.getAttribute("href");},allQuestions[i]);
        allLinkPromise.push(linkPendingPromise);
    }
    let allQuestionsPromise=Promise.all(allLinkPromise);
    return allQuestionsPromise;
})
.then(function(allLinkPromise){
    console.log("===================");
    AllLinks=[];
    for(let i=0;i<allLinkPromise.length;i++){
        let completeLink="https://www.hackerrank.com"+allLinkPromise[i];
        AllLinks.push(completeLink);
    }
    console.log(AllLinks);
    let questionSolvePromise=solveQuestion(AllLinks[0]);
    return questionSolvePromise;

})
.then(function(){
    console.log("yes solved");
})
.catch(function(error){
    console.log("fuut gya");
    console.log(error);
})

function waitAndClick(selector){
    return new Promise(function(resolve,reject){
        let waitPromise=tab.waitForSelector(selector);
        waitPromise.then(function(){
            let clickPromise=tab.click(selector);
            return clickPromise;
        })
        .then(function(){
            resolve();
        })
        .catch(function(error){
            reject(error);
        })
    });
}

function solveQuestion(qLink){
    return new Promise(function(resolve,reject){
        let clickPromise=tab.goto(qLink);
        clickPromise.then(function(){
            console.log("going wait and click finction");
            let waitAndClickPromise=waitAndClick('div[data-attr2="Editorial"]');
            console.log(waitAndClickPromise);
            return waitAndClickPromise;
        }).then(function(){
            let codePromise=getCode();
            console.log("Inside Editorial");
            return codePromise;
        })
    })
}

function getCode(){
    return new Promise(function(resolve,reject){
        let waitPromise=tab.waitForSelector(".hackdown-content h3");
        waitPromise.then(function(){
            let allCodeNamesPromise=tab.$$(".hackdown-content h3");
            return allCodeNamesPromise;
        }).then(function(allCodeNames){
            // console.log("paragraph "+allCodeNames.length);
            let allCodeNamesP=[];
            for(let i=0;i<allCodeNames.length;i++){
                let namePromise=tab.evaluate(function(elem){
                    return elem.textContent;
                },allCodeNames[i])
                allCodeNamesP.push(namePromise);
            }
            let promiseAllCodePromise=Promise.all(allCodeNamesP);
            return promiseAllCodePromise;
            }).then(function(allCodePromise){
            // console.log("length is :"+allCodePromise.length);
            // console.log(allCodePromise);
            for(let i=0;i<allCodePromise.length;i++){
                // console.log("lanuage used id - "+allCodePromise[i])
                if(allCodePromise[i]=="C++"){
                    indx=i;
                    break;
                }
            }
            // console.log("index= "+indx);
            let allDivPromise=tab.$$(".hackdown-content .highlight");
            // console.log(allDivPromise)
            return allDivPromise;
        }).then(function(allDiv){
            //[<div></div>,<div></div>,<div></div>]
            // console.log(allDiv[indx]);
            let myDiv=allDiv[indx];
            let codePromise=tab.evaluate(function(elem){return elem.textContent},myDiv)
            return codePromise;
        }).then(function(code){
            globalCode=code;
            resolve();
        }).catch(function(error){
            reject(error);
        })
    })
}