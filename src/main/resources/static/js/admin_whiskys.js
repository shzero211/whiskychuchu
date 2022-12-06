/*<![CDATA[*/
var admin_whiskys={
    init : function(){
        var _this=this;
        $('#btn-update').on('click',function(){
            _this.move_update();
        });
    },
    move_update : function(){
        var data={
            id: /*[[${whisky.id}]]*/
        };
        $.ajax({
            type: 'GET',
            url: '/admin/whisky',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('업데이트 눌림');
        }).fail(function(){
            alert('업데이트 눌림 실패');
        });
    }
};
admin_whiskys.init();
/*]]>*/
