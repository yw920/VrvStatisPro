$(document).ready(function(){
	/* ---------- Datable ---------- */
	$('.datatable').dataTable({
		"sDom": "<'fenye'ifp>",
		//"sDom": "<'row'<'col-lg-6'l><'col-lg-6'f>r>t<'row'<'col-lg-12'i><'col-lg-12 center'p>>",
		"sPaginationType": "bootstrap",
		"oLanguage": {
			"sLengthMenu": "每页显示 _MENU_ 条记录",
			"sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
			"bScrollInfinite": true,
			"iDisplayLength": 7
		}
	});
});