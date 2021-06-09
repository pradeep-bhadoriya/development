const puppeteer=require("puppeteer");
(async function(){
try{
    let browser=await puppeteer.launch({ headless: false, defaultViewport: false, args: ["--start-maximized"] });
    let pages=await browser.pages();
    let page=pages[0];
    await page.goto("https://www.hackerrank.com/auth/login?h_l=body_middle_left_button&h_r=login");
    await page.type("#input-1", "gadob83422@relumyx.com");
    await page.type("#input-2", "12345678");
    await page.click(".ui-btn.ui-btn-large.ui-btn-primary.auth-button.ui-btn-styled");
    
}
catch(error){
    console.log(error);
}
})();