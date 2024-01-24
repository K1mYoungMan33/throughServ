{
    // contextMenu2.js 파일

    let myValList = document.currentScript.getAttribute('target') + "";
    let myAction = document.currentScript.getAttribute('action');
    let myEventId = document.currentScript.getAttribute('eventid');
    let myTable = document.currentScript.getAttribute('table');
    let myWhere = document.currentScript.getAttribute('where');
    console.log("myValue", myValList); // "설정값"

    let mySelector = $(myValList).attr("cols").split(/[ ]*,[ ]*/);
    let bUseAll = false;
    if (-1 !== mySelector.findIndex((elem) => elem === "all"))
        bUseAll = true;
    let findList = [];
    let excludeList = [];
    mySelector.forEach((elem) => {
        if (elem.includes("-:"))
            excludeList.push(elem.replace("-:", ""));
        else
            findList.push(elem);
    })
    if (0 === findList.length && 0 < excludeList.length)
        bUseAll = true;

    let template = "<li class='$key$'>$value$</li>";

    gv = [];

    function handleUpdateList(event) {
        // let item = event.response;
        let str = "";
        let data = event.response;
        data.forEach((item) => {
            str += '<ul>';
            for (let key in item) {
                if (excludeList.includes(key))
                    continue;
                if (false === bUseAll && false === findList.includes(key))
                    continue;
                str += template.replace("$key$", key).replace("$value$", item[key]);
            }
            str += '</ul>'; // 리스트 종료
        })
        $(myValList).empty();
        $(myValList).append(str);
    }

    $(document).ready(function () {

        document.addEventListener("update_list_event" + myEventId, handleUpdateList);

        $.ajax({
            url: "/main/process.do",
            type: 'POST',
            data: {
                action: myAction,
                // table : "notice",
                // where : "no<35"
                // where : "content like '%에%' and no < 2"
                table: myTable,
                where: myWhere,
            },
            success: function (response) {
                let myEvent = new Event("update_list_event" + myEventId);
                myEvent.response = response;
                document.dispatchEvent(myEvent);
            },
            error: function (err) {
                console.error("에러 발생");
                console.log(err);
            }
        });
    });


}