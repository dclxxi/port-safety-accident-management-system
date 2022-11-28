function ChangeValue() {
    const value_str = document.getElementById('incidentType');
    const chose = value_str.options[value_str.selectedIndex].value;
    if (chose == "INCIDENT" || chose == "사고") {
        const el1 = document.getElementById('ch1');
        el1.innerHTML = '사고 수준';
        const el2 = document.getElementById('ch2');
        el2.innerHTML = '사고 유형';
        const el3 = document.getElementById('ch3');
        el3.innerHTML = '사고 영향';
        const el4 = document.getElementById('ch4');
        el4.innerHTML = '사고항만구역';
        const el5 = document.getElementById('ch5');
        el5.innerHTML = '사고항만설비';

        const sel1 = document.getElementById("sel1");
        sel1.innerHTML = "추락";
        const sel2 = document.getElementById("sel2");
        sel2.innerHTML = "넘어짐";
        const sel3 = document.getElementById("sel3");
        sel3.innerHTML = "부딪힘";
        document.getElementById("sel4").disabled = false;
        document.getElementById("sel5").disabled = false;
        const sel4 = document.getElementById("sel4");
        sel4.innerHTML = "끼임";
        const sel5 = document.getElementById("sel5");
        sel5.innerHTML = "맞음";

        var select = document.getElementById("incidentDetailTypeSelect");
        select[1].value = "DROP";
        select[2].value = "FALL";
        select[3].value = "BUMP";
        select[4].value = "TRAP";
        select[5].value = "HIT";
    }
    if (chose == "DISASTER" || chose == "재난") {
        const el1 = document.getElementById('ch1');
        el1.innerHTML = '재난 수준';
        const el2 = document.getElementById('ch2');
        el2.innerHTML = '재난 유형';
        const el3 = document.getElementById('ch3');
        el3.innerHTML = '재난 영향';
        const el4 = document.getElementById('ch4');
        el4.innerHTML = '재난항만구역';
        const el5 = document.getElementById('ch5');
        el5.innerHTML = '재난항만설비';

        const sel1 = document.getElementById("sel1");
        sel1.innerHTML = "지진";
        const sel2 = document.getElementById("sel2");
        sel2.innerHTML = "태풍";
        const sel3 = document.getElementById("sel3");
        sel3.innerHTML = "선박 사고";
        document.getElementById("sel4").disabled = true;
        document.getElementById("sel5").disabled = true;
        const sel4 = document.getElementById("sel4");
        sel4.innerHTML = "x";
        const sel5 = document.getElementById("sel5");
        sel5.innerHTML = "x";

        var select = document.getElementById("incidentDetailTypeSelect");
        select[1].value = "EARTHQUAKE";
        select[2].value = "TYPHOON";
        select[3].value = "SHIPACCIDENT";
        select[4].value = null;
        select[5].value = null;
    }
}