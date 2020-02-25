var App=function () {
    var _masterCheckbox;
    var _checkbox;
    var handlerInitCheckbox=function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

         _masterCheckbox=$('input[type="checkbox"].minimal.check_master');
         _checkbox=$('input[type="checkbox"].minimal');

         var handlerCheckboxAll=function () {
             _masterCheckbox.on("ifClicked",function (e) {
                 if(e.target.checked){
                     _checkbox.iCheck("uncheck");
                 }
                 else {
                     _checkbox.iCheck("check");
                 }

             });
         };
    };
    return {
        init:function(){
            handlerInitCheckbox();
            handlerInitCheckbox();
        }
    }
}();
$(document).ready(function () {
    App.init();
});