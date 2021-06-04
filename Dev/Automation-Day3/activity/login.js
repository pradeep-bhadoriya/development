const puppeteer=require("puppeteer");
let tab;
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
    console.log("pradeep");
})
.catch(function(error){
    console.log(error);
})