class PortHeader extends HTMLElement {
    connectedCallback() {
        this.innerHTML = '<header>\n' +
            '    <div class="inner">\n' +
            '      <a href="/" class="logo">\n' +
            '        <img src="/img/favicon.png" alt="PSA">\n' +
            '      </a>\n' +
            '      <div class="sub-menu1">\n' +
            '        <ul class="menu">\n' +
            '          <li>\n' +
            '            <a href="/Member/M_login.html">로그인</a>\n' +
            '          </li>\n' +
            '          <li>\n' +
            '            <a href="/Member/M_joining.html">회원가입</a>\n' +
            '          </li>\n' +
            '        </ul>\n' +
            '      </div>\n' +
            '      <nav role="navigation">\n' +
            '        <div class="main">\n' +
            '          <ul id="main-menu">\n' +
            '            <li><a href="#">공통코드<br>관리</a>\n' +
            '              <ul id="sub-menu">\n' +
            '                <div class="line"></div>\n' +
            '                <li><a href="/Code/representativeCode_list" aria-label="submenu">대표코드 관리</a></li>\n' +
            '                <li><a href="/Code/detailedCode_list" aria-label="submenu">상세코드 관리</a></li>\n' +
            '              </ul>\n' +
            '            </li>\n' +
            '            <li><a href="#">안전사고<br>정보 관리</a>\n' +
            '              <ul id="sub-menu">\n' +
            '                <div class="line2"></div>\n' +
            '                <li><a href="/SafetyAccident/SA_check" aria-label="submenu">안전사고 정보 조회</a></li>\n' +
            '                <li><a href="/SafetyAccident/SA_registration_Page" aria-label="submenu">안전사고 정보 등록</a></li>\n' +
            '              </ul>\n' +
            '            </li>\n' +
            '            <li><a href="#">안전사고 대응훈련<br>시나리오 관리</a>\n' +
            '              <ul id="sub-menu">\n' +
            '                <div class="line35"></div>\n' +
            '                <li><a href="/TrainingScenarios/TS_Check" aria-label="submenu">대응훈련 시나리오 조회</a></li>\n' +
            '                <li><a href="/TrainingScenarios/TS_Register_Page" aria-label="submenu">대응훈련 시나리오 등록</a></li>\n' +
            '              </ul>\n' +
            '            </li>\n' +
            '            <li><a href="#">안전사고 대응훈련<br>시나리오 평가 관리</a>\n' +
            '              <ul id="sub-menu">\n' +
            '                <div class="line4"></div>\n' +
            '                <li><a href="/TS_Assessment/TSA_Check" aria-label="submenu">대응훈련 시나리오 평가 조회</a></li>\n' +
            '                <li><a href="/TS_Assessment/TSA_Register_Page" aria-label="submenu">대응훈련 시나리오 평가 등록</a></li>\n' +
            '              </ul>\n' +
            '            </li>\n' +
            '            <li><a href="#">안전사고 대응훈련<br>결과 관리</a>\n' +
            '              <ul id="sub-menu">\n' +
            '                <div class="line35"></div>\n' +
            '                <li><a href="/TrainingResult/trainingResult_list" aria-label="submenu">대응훈련 결과 조회</a></li>\n' +
            '                <li><a href="/TrainingResult/trainingResult_registerPage" aria-label="submenu">대응훈련 결과 등록</a></li>\n' +
            '                <li><a href="/TrainingResult/trainingResult_daysPage" aria-label="submenu">대응훈련 일자별<br>수행 결과 등록</a></li>\n' +
            '              </ul>\n' +
            '            </li>\n' +
            '            <li><a href="#">비상연락망<br>관리</a>\n' +
            '              <ul id="sub-menu">\n' +
            '                <div class="line6"></div>\n' +
            '                <li><a href="/EmergencyContact/EC_Check" aria-label="submenu">비상연락망 조회</a></li>\n' +
            '                <li><a href="/EmergencyContact/EC_Register_Page" aria-label="submenu">비상연락망 등록</a></li>\n' +
            '              </ul>\n' +
            '            </li>\n' +
            '            <li><a href="/help/stateaccident" style="position:relative; left:800px; top:-57.5px;">항만 안전사고<br>현황</a>\n' +
            '            </li>\n' +
            '          </ul>\n' +
            '        </div>\n' +
            '      </nav>\n' +
            '  </header>'
    }
}

customElements.define('port-header', PortHeader)