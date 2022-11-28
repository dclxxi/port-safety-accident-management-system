let saveScenario = {
    init: function () {
        $("#TS_Modify_button").on("click", () => {
            this.save();
        })
    },
    save: function () {
        let accidentPortFacilityList = [];
        $("input:checkbox[name=accidentPortFacilityList]:checked").each(function (i) {
            accidentPortFacilityList.push($(this).val());
        });

        if ($("#name").val().length == 0) {
            alert('시나리오명를 입력해주세요.');
        } else if (accidentPortFacilityList.length == 0) {
            alert('사고항만설비를 선택해주세요.');
        }

        for (let i = 1; i <= $("#size").val(); i++) {
            if ($('#comment' + i).val().length == 0) {
                alert('사고대응활동 내용을 입력해주세요.');
                break;
            } else if ($('#manager' + i).val().length == 0) {
                alert('담당자를 입력해주세요.');
                break;
            } else if ($('#completePlaningTime' + i).val().length == 0) {
                alert('완료계획시간을 선택해주세요.');
                break;
            }
        }

        let accidentResponseActivityList = [];
        for (let i = 1; i <= $("#size").val(); i++) {
            let data = {
                id: $('#accidentResponseActivityId' + i).val(),
                comment: $('#comment' + i).val(),
                manager: $('#manager' + i).val(),
                completePlaningDate: $('#completePlaningTime' + i).val(),
                scenarioId: $("#scenarioId").val()
            };
            accidentResponseActivityList.push(data);
        }

        let data = {
            id: $("#scenarioId").val(),
            incidentType: $("#incidentType option:selected").val(),
            name: $("#name").val(),
            incidentDetailType: $("#incidentDetailType option:selected").val(),
            incidentLevel: $("#incidentLevel:checked").val(),
            incidentImpact: $("#incidentImpact option:selected").val(),
            portArea: $("#portArea").val(),
            accidentPortFacilityList: accidentPortFacilityList,
            accidentResponseActivityList: accidentResponseActivityList
        };

        $.ajax({
            method: "POST",
            url: "/TrainingScenarios/TS_Modify",
            data: JSON.stringify(data),
            contentType: "application/json"
        }).done(function (resp) {
            alert('시나리오가 수정되었습니다.');
            location.href = "/TrainingScenarios/TS_Detail/" + $("#scenarioId").val();
            console.log(JSON.stringify(data));
        }).fail(function (error) {
            console.log(error);
        });
    }
}

saveScenario.init();