//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
        $.validator.setDefaults({
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                element.closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            errorElement: "span",
            errorPlacement: function (error, element) {
                if (element.is(":radio") || element.is(":checkbox")) {
                    error.appendTo(element.parent().parent().parent());
                } else {
                    error.appendTo(element.parent());
                }
            },
            errorClass: "help-block m-b-none",
            validClass: "help-block m-b-none"


        });

        //以下为官方示例
        $().ready(function () {
        		//外部js调用
            laydate({
                elem: '#birthDay', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
                event: 'focus' //响应事件。如果没有传入event，则按照默认的click
            });
        	
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm").validate({
                rules: {
                    userName: {
                        required: true,
                        minlength: 2
                    },
                    phone: {
                        required: true,
                        minlength: 11,
                        maxlength:11,
                    },
                    idCard: {
                        required: true,
                        minlength: 18,
                        maxlength:18,
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    birthDay: {
                        required: true
                    },
                    agree: "required"
                },
                messages: {
                    userName: {
                        required: icon + "请输入您的用户名",
                        minlength: icon + "用户名必须两个字符以上"
                    },
                    phone: {
                        required: icon + "请输入您的电话",
                        minlength: icon + "电话必须11个字符",
                        maxlength: icon + "电话必须11个字符",
                    },
                    idCard: {
                        required: icon + "请输入您的身份证",
                        minlength: icon + "身份证必须18个字符",
                        maxlength: icon + "身份证必须18个字符"
                    },
                    email: icon + "请输入您的E-mail",
                    birthDay :{
                    		required: icon + "请选择您的生日",
                    }                    
                }
            });
        });
