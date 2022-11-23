let saveStaff = {
    init: function () {
        $("#TSA_Modify_button").on("click", () => {
            this.save();
        })
    },
    save: function () {
        let data = {
            id: $("#id").val(),
            name: $("#name").val(),
            developmentStandard1: $("#occurrence1:checked").val(),
            developmentStandard2: $("#occurrence2:checked").val(),
            developmentStandard3: $("#occurrence3:checked").val(),
            possibleStandard1: $("#appropriate1:checked").val(),
            possibleStandard2: $("#appropriate2:checked").val(),
            possibleStandard3: $("#appropriate3:checked").val(),
            completeStandard1: $("#completeness1:checked").val(),
            completeStandard2: $("#completeness2:checked").val(),
            completeStandard3: $("#completeness3:checked").val()
        };

        console.log(JSON.stringify(data));

        $.ajax({
            method: "POST",
            url: "/TS_Assessment/TSA_Modify",
            data: JSON.stringify(data),
            contentType: "application/json"
        }).done(function (resp) {
            alert('대응훈련 시나리오 평가가 수정되었습니다.');
            location.href = "/TS_Assessment/TSA_Check"
            console.log(JSON.stringify(data));
        }).fail(function (xhr, status, error) {
            console.log(error);
        });
    }
}

saveStaff.init();