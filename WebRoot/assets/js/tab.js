﻿/**
 * Created by hongye.wei on 2014/12/8.
 */
 index = 0;
center_name_click = '';
$('.test > li').click(function () {
    var tabname = getTabTitle(this);
    navClick(tabname);
});

function addPanel() {
    var OnTags = $('span.tabs-title'), num = 0;
    var no = OnTags.text().indexOf("逻辑监控2");
    if (no < 0) {
        $('#tt').tabs('add', {
            title: '逻辑监控2 ',
            content: '<div style="padding:10px">Content' + index + '</div>',
            closable: true
        });
    }
    else {
        for (var ij = 0; ij < no; ij++) {
            if (OnTags.text().charAt(ij) == ' ')
                num++;
        }
        $('#tt').tabs("select", num);
    }

}
function removePanel() {
    var tab = $('#tt').tabs('getSelected');
    if (tab) {
        var index = $('#tt').tabs('getTabIndex', tab);
        $('#tt').tabs('close', index);
    }
}

function getTabTitle(e){
    return $(e).text();
}
function navClick(tabname,centerName){
    if(centerName == undefined){
        centerName = '';
    }
    center_name_click = centerName;

    var OnTags;
    OnTags = $('span.tabs-title');
    var ontabs = OnTags.text();
    var ontabsStr = ontabs.split(' ');
    var num = ontabsStr.indexOf(tabname);
    var logic0 = $('<div  style="padding:0.2px ;height:100%"><iframe name="logic0" frameborder="0" src="page1.html" height="100%" width="100%"  ></iframe></div>');
    var logic1 = $('<div  style="padding:0.2px ;height:100%"><iframe name="logic1" frameborder="0" src="page2.html" height="100%" width="100%"  ></iframe></div>');
    var logic2 = $('<div  style="padding:0.2px ;height:100%"><iframe name="logic2" frameborder="0" src="log.html" height="100%" width="100%"  ></iframe></div>');
    var selectTab = null;
    if (tabname == "首页") {
        selectTab = logic0;
    }
    else if (tabname == "设备详细信息") {
        selectTab = logic1;
    }
    else if (tabname == "日志信息"){
        selectTab = logic2;
    }
    if(selectTab != null){
        if (num < 0) {
            $('#tt').tabs('add', {
                title: tabname + ' ',
                content: selectTab,
                closable: true
            });
            onload[index] = 1;
        }
        else {
            index = 0;
            for (var ij = 0; ij < num; ij++) {
                if (ontabsStr[ij] == ' ') {
                    index++;
                }
            }
            $('#tt').tabs("select", num);
            if(tabname == '首页'){
                parent.frames["logic0"].window.centerLogicFlesh();
            }
            else if(tabname == '设备详细信息'){
                parent.frames["logic1"].window.centerLogicFlesh();
            }
            else if(tabname == '日志信息'){
                parent.frames["logic2"].window.centerLogicFlesh();
            }
        }
    }

}