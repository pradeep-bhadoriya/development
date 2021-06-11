// document
const $ = require("jquery");

$("document").ready(function(){
    console.log("document is loaded");
    // a click event is get attcahed on element with .cell class
    $(".cell").on("click",function(){
        console.log(this);
        console.log("cell is clicked");
        let rowID=Number($(this).attr("rid"))+1;
        let colID=Number($(this).attr("cid"));
        let cellAddress=String.fromCharCode(65+colID)+rowID;
        console.log(cellAddress);
        $("#address").val(cellAddress);
    })
})