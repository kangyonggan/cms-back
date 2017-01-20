$(function () {
    updateState("user/profile");

    var $form = $('#form');
    var $btn = $("#submit");

    $("#remove").click(function () {
        $("#avatar-div").empty().html('<input type="file" name="largeAvatar"/>');

        initAvatar();
        return false;
    });

    initAvatar();
    
    function initAvatar() {
        var file_input = $form.find('input[type=file]');

        file_input.ace_file_input({
            style: 'well',
            btn_choose: '点击这里添加头像',
            btn_change: null,
            no_icon: 'ace-icon fa fa-picture-o',
            droppable: false,
            allowExt: ["jpeg", "jpg", "png", "gif"],
            allowMime: ["image/jpeg", "image/jpg", "image/png", "image/gif"],
            maxSize: 2097152,//bytes
            thumbnail: 'fit'
        });

        file_input.on('file.error.ace', function (event, info) {
            if (info.error_count['size']) Message.warning('超出最大上传限制。');
            if (info.error_count['ext'] || info.error_count['mime']) Message.warning('不合法的文件类型。');
            event.preventDefault();
        });
    }

});

