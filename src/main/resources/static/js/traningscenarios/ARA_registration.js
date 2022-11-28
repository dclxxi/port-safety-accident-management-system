let saveScenario = {
    init: function () {
        $("#ARA_Registration_button").on("click", () => {
            this.save();
        })
    },
    save: function () {
        if ($("#comment").val().length == 0) {
            alert('사고대응활동 내용을 입력해주세요.');
        } else if ($("#manager").val().length == 0) {
            alert('담당자를 입력해주세요.');
        } else if ($("#completePlaningTime").val().length == 0) {
            alert('완료계획시간을 선택해주세요.');
        }

        let data = {
            comment: $("#comment").val(),
            manager: $("#manager").val(),
            completePlaningDate: $("#completePlaningTime").val(),
            scenarioId: $("#scenarioId").val()
        };

        console.log(JSON.stringify(data));

        $.ajax({
            method: "POST",
            url: "/TrainingScenarios/ARA_Register",
            data: JSON.stringify(data),
            contentType: "application/json"
        }).done(function (resp) {
            alert('사고대응활동이 저장되었습니다.');
            location.href = "/TrainingScenarios/TS_Detail/" + $("#scenarioId").val();
            console.log(JSON.stringify(data));
        }).fail(function (error) {
            console.log(error);
        });
    }
}

saveScenario.init();