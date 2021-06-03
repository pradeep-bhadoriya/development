let fs=require("fs");
let html=fs.readFileSync("./index.html", "utf-8");
console.log(html);
let cheerio=require("cheerio");
cheerio.load(html);
let heading=$["h1"];
console.log(heading)
