$(document).ready(function(){ 
	$( window ).resize(function() {
		var windowWidth = $( window ).width();
		if(windowWidth >= 978) {
			$("#subNavMenu").hide();
		}
	});
	
	$("#clMenu").click(function(){
		$("#subNavMenu").toggle();
	});
	
	$("#conWrite").click(function(){
		location.href = "insertBoard.jsp";
	});
	
	$("#conDel").click(function(){
		let con = confirm("정말로 삭제하시겠습니까?");
		if(con == true){
			let v = document.fm.seq.value;
			location.href = "deleteBoard.do?seq="+v;
		}else{
		  	return false;
		}
	});
	$("#delAll").click(function(){
		let con = confirm("정말로 삭제하시겠습니까?");
		if(con == true){
			let v = document.fm;
			location.href = "deleteAll.do";
		}else{
		  	return false;
		}
	});
	
	$("#conList").click(function(){
		location.href = "getBoardList.do";
	});
});

function selTr(val){
	location.href = "getBoard.do?seq="+val;
}

function autoDel(val){
	location.href="autoDelete.do?auto=" +val;
}

function autoIns(val){
	location.href="autoInsert.do?auto=" +val;
}

function delAll(){
	location.href="deleteAll.do";
}
function board(val){
	alert(val);
	if(val == "a"){
		location.href="Aboard.do";
	}else if(val == "b"){
		location.href="Bboard.do";
		
	}else if (val == "c"){
		location.href="Cboard.do";
	}else {
		location.href="Myboard.do"
		
	}
	
	
}


	