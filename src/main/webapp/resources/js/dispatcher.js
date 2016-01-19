var getRowData = function(id){
    var employeeObject = document.getElementsByName('employeeObject'+id);
    if (employeeObject.length > 0){
        try {
            employeeObject = $.parseJSON(employeeObject[0].value.replace(/'/g,'\"'));

            var input = $("<input>")
                .attr("type", "hidden")
                .attr("name", "employeeId").val(employeeObject.id);
            $('#rowForm'+id).append($(input));
            $('#rowForm'+id).submit();
        } catch(e){
            alert("Error format data");
        }

    } else {
        alert('Can not find element by id: ' + id);
    }
};

var changeRowData = function(elem, id){
    var employeeObject = document.getElementsByName('employeeObject'+id);
    if (elem.name = 'edit_save'){
        if (elem.value = 'edit'){
            document.getElementsByName('employeeObject'+id);
        }
    }

    if (employeeObject.length > 0){
        try {
            employeeObject = $.parseJSON(employeeObject[0].value.replace(/'/g,'\"'));

            var input = $("<input>")
                .attr("type", "hidden")
                .attr("name", "employeeId").val(employeeObject.id);
            $('#rowForm'+id).append($(input));
            $('#rowForm'+id).submit();
        } catch(e){
            alert("Error format data");
        }

    } else {
        alert('Can not find element by id: ' + id);
    }
};
