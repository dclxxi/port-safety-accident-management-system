let saveStaff = {
    init: function () {
        $("#EC_Registration_button").on("click", () => {
            this.save();
        })
    },
    save: function () {
        if ($("#name").val().length == 0) {
            alert('이름을 입력해주세요.');
        } else if ($("#corporation").val().length == 0) {
            alert('소속회사를 입력해주세요.');
        } else if ($("#group").val().length == 0) {
            alert('조직을 입력해주세요.');
        } else if ($("#position").val().length == 0) {
            alert('직급을 입력해주세요.');
        } else if ($("#phoneNumber").val().length == 0) {
            alert('연락처를 입력해주세요.');
        } else if ($("#email").val().length == 0) {
            alert('이메일을 입력해주세요.');
        }

        let data = {
            name: $("#name").val(),
            group: $("#group").val(),
            email: $("#email").val(),
            phoneNumber: $("#phoneNumber").val(),
            corporation: $("#corporation").val(),
            position: $("#position").val()
        };

        $.ajax({
            method: "POST",
            url: "/EmergencyContact/EC_Register",
            data: JSON.stringify(data),
            contentType: "application/json"
        }).done(function (resp) {
            alert('비상연락망이 저장되었습니다.');
            location.href = "/EmergencyContact/EC_Check"
            console.log(JSON.stringify(data));
        }).fail(function (xhr, status, error) {
            console.log(error);
        });
    }
}

saveStaff.init();